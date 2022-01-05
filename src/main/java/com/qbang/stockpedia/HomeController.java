package com.qbang.stockpedia;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.qbang.stockpedia.persistence.CommunityDAOJPA;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.qbang.stockpedia.domain.Board;
import com.qbang.stockpedia.domain.CommentTier;
import com.qbang.stockpedia.domain.Stock;
import com.qbang.stockpedia.impl.CommunityService;
import com.qbang.stockpedia.impl.MemberService;
import com.qbang.stockpedia.impl.ProcessStockService;
import com.qbang.stockpedia.impl.RequestStockService;
import com.qbang.stockpedia.impl.TierService;

@EnableScheduling
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private RequestStockService requestStockService;
	
	@Autowired
	private ProcessStockService processStockService;
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TierService tierService;

	@Autowired
	private CommunityDAOJPA communityDAOJPA;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest req){
		HttpSession session = req.getSession();
		//세션에서 닉네임 가져오기
		Object unick = session.getAttribute("unick");
		if(unick == null) {
			model.addAttribute("unick", "게스트");
		}else {
			model.addAttribute("unick", unick.toString());
		}
		
		//오늘 날짜의 주식정보 가져오기
		List<Stock> stock = processStockService.searchTodayStock();
		
		if(stock == null || stock.size() == 0) {
			stock = processStockService.searchPastStock();
		}
		
		//가져온 정보에서 개수만 빼주고 모델에 넣기
		ArrayList<HashMap<String, Integer>>[] arr = processStockService.classifyItemInfo(stock);
		for(int i=0; i<arr.length; i++) {
			String mapName = "map"+(i+1);
			model.addAttribute(mapName, arr[i].size());
		}
		
		//인기글 가져오기
		Optional<List<Board>> board = communityDAOJPA.selectContentTopList();
		if(board.isPresent()) { // re: null일 때 콘텐츠를 어떻게 보여줄건지 처리 필요
			model.addAttribute("list", board.get());
		}
		
		return "main";
	}

	@RequestMapping(value = "/login")
	public String login(HttpMethod httpMethod, HttpServletRequest request) throws UnsupportedEncodingException {
		if (httpMethod.matches("GET")) {
			return "login";
		} else if (httpMethod.matches("POST")) {
			request.setCharacterEncoding("UTF-8");

			String uid = request.getParameter("uid");
			String upw = request.getParameter("upw");
			String unick = memberService.checkUser(uid, upw);

			if (unick.equals("")) {	//로그인 실패
				return "redirect:/login";
			} else { //로그인 성공
				HttpSession session = request.getSession();
				session.setAttribute("unick", unick);
				session.setAttribute("uid", uid);

				logger.info("# login id = " + uid + " at "+ simpleDateFormat.format(System.currentTimeMillis()));
			}
		}
		return "redirect:/";
	}
	
	// 회원가입 페이지 요청 
	@RequestMapping(value = "/reqRegister", method = RequestMethod.GET)
	public String reqRegister(Locale locale, Model model) {
		return "register";
	}
	
	// 회원가입
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public String doRegister(Locale locale, Model model, HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		
		String uid = req.getParameter("uid");
		String upw = req.getParameter("upw");
		String unick = req.getParameter("unick");
		
		memberService.registerUser(uid, upw, unick);
		
		HttpSession session = req.getSession();
		session.setAttribute("unick", unick);
		session.setAttribute("uid", uid);
		
		tierService.updateTier();
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/community", method = RequestMethod.GET)
	public String community(Locale locale, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("uid") == null) {
			return "redirect:/login";
		}
		// 글 리스트 가져오기 
		List<Board> list = communityService.getContentList();
		model.addAttribute("list", list);

		return "community";
	}
	
	@RequestMapping(value = "/reqWrite", method = RequestMethod.GET)
	public String reqWrite(Locale locale, Model model) {
		return "write";
	}	
	
	@RequestMapping(value = "/doWrite", method = RequestMethod.POST)
	public String doWrite(Locale locale, Model model, HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		String uid = (String) session.getAttribute("uid");
		int num = memberService.getUserNum(uid);
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		// 게시글 등록 
		communityService.registerContent(title, content, num);
		
		return "redirect:/community";
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String content(Locale locale, Model model, HttpServletRequest req, @RequestParam int board_num) {
		HttpSession session = req.getSession();

		if(session.getAttribute("uid") == null) {
			return "redirect:/login";
		}
		//클릭 게시글 내용 가져오기 
		Board board = communityService.getSingleContent(board_num);
		
		//게시글 로드 과정에서 오류가 나지 않았을 때 
		if(board != null) {
			//댓글 리스트 가져오기 
			List<CommentTier> comment = communityService.getCommentList(board_num);
			
			model.addAttribute("content", board);
			model.addAttribute("comment", comment);
		}else {
			return "redirect:/community";
		}
		
		//현재 이용자가 등록한 좋아요가 있는지 검사 
		int member_num = memberService.getUserNum((String) session.getAttribute("uid"));
		boolean check = communityService.checkExistLike(board_num, member_num);

		model.addAttribute("like", check? true : false);
	
		return "post";
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public String comment(Locale locale, Model model, HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		String uid = (String) session.getAttribute("uid");
		int member_num = memberService.getUserNum(uid);
		int board_num = Integer.parseInt(req.getParameter("board_num"));
		String comment = req.getParameter("comment");
		//댓글 등록 
		communityService.registerComment(comment, board_num, member_num);
		
		return "redirect:/post?board_num="+board_num;
	}
	
	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public String like(Locale locale, Model model, HttpServletRequest req, @RequestParam int board_num) {
		HttpSession session = req.getSession();
		
		String uid = (String) session.getAttribute("uid");
		int member_num = memberService.getUserNum(uid);
		//좋아요 등록
		communityService.registerLike(board_num, member_num);
		
		return "redirect:/post?board_num="+board_num;
	}
	
	@RequestMapping(value="/stock", method = RequestMethod.GET)
	public String stock(Locale locale, Model model, @RequestParam int idx) throws ParseException {
		//금액별 주식 리스트 조회 
		List<Stock> list = processStockService.searchIdxStock(idx);
		model.addAttribute("list", list);
		
		return "stock";
	}
	
	//1시간 마다 실행
	@Scheduled(cron="0 0 0/1 * * *")
	@Async
	public void batchForStock() throws IOException, ParseException {
		List<Stock> stock = processStockService.searchTodayStock();

		if (stock == null || stock.size() == 0) {
			HashSet<String> codeSet = requestStockService.getItemCode();
			JSONArray ret = requestStockService.getItemInfo(codeSet);
			// 종목명이랑 가격만 빼주고 DB에 넣어주기
			HashMap<String, Integer> map = processStockService.parseItemInfo(ret);
			processStockService.registerStock(map);
		}
	}
	
	//매주 월요일 0시에 실행
	@Scheduled(cron="0 0 0 * * MON")
	@Async
	public void batchForTier() {
		tierService.updateTier();
	}
}
