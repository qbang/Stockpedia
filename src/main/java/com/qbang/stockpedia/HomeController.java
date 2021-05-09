package com.qbang.stockpedia;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbang.stockpedia.domain.Board;
import com.qbang.stockpedia.domain.Comment;
import com.qbang.stockpedia.domain.Stock;
import com.qbang.stockpedia.impl.CommunityService;
import com.qbang.stockpedia.impl.MemberService;
import com.qbang.stockpedia.impl.ProcessStockService;
import com.qbang.stockpedia.impl.RequestStockService;
import com.sun.tools.sjavac.Log;

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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model){
		return "login";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Locale locale, Model model,  HttpServletRequest req) {
		HttpSession session = req.getSession();
		session = req.getSession();
		
		//세션에서 닉네임 가져오기
		Object unick = session.getAttribute("unick");
		if(unick == null) {
			model.addAttribute("unick", "게스트");
		}else {
			model.addAttribute("unick", unick.toString());
		}
		
		//오늘 날짜의 주식정보 가져오기
		List<Stock> stock = processStockService.searchTodayStock();
		//가져온 정보에서 개수만 빼주고 모델에 넣기
		if(stock != null) {
			ArrayList<HashMap<String, Integer>>[] arr = processStockService.classifyItemInfo(stock);
			for(int i=0; i<arr.length; i++) {
				String mapName = "map"+(i+1);
				model.addAttribute(mapName, arr[i].size());
			}
		}
		
		//인기글 가져오기
		List<Board> board = communityService.getTopContentList();
		if(board != null) {
			model.addAttribute("list", board);
		}
		
		return "detail";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		
		String uid = req.getParameter("uid");
		String upw = req.getParameter("upw");
		
		String unick = memberService.checkUser(uid, upw);
		
		if(unick.equals("")) {	//로그인 실패
			return "redirect:/";
		}else { //로그인 성공
			HttpSession session = req.getSession();
			session = req.getSession();
			session.setAttribute("unick", unick);
			session.setAttribute("uid", uid);
			
			return "redirect:/detail";
		}
	}
	
	@RequestMapping(value = "/reqRegister", method = RequestMethod.GET)
	public String reqRegister(Locale locale, Model model) {
		return "register";
	}
	
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public String doRegister(Locale locale, Model model, HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		
		String uid = req.getParameter("uid");
		String upw = req.getParameter("upw");
		String unick = req.getParameter("unick");
		
		memberService.registerUser(uid, upw, unick);
		
		HttpSession session = req.getSession();
		session = req.getSession();
		session.setAttribute("unick", unick);
		session.setAttribute("uid", uid);
		
		return "redirect:/detail";
	}
	
	@RequestMapping(value = "/community", method = RequestMethod.GET)
	public String community(Locale locale, Model model) {
		// 글 리스트 가져와야 함
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
		session = req.getSession();
		
		String uid = (String) session.getAttribute("uid");
		int num = memberService.getUserNum(uid);
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		communityService.registerContent(title, content, num);
		
		return "redirect:/community";
	}
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(Locale locale, Model model, HttpServletRequest req, @RequestParam int board_num) {
		Board board = communityService.getSingleContent(board_num);
		List<Comment> comment = communityService.getCommentList(board_num);
		
		if(board != null) {
			model.addAttribute("content", board);
			model.addAttribute("comment", comment);
		}else {
			return "redirect:/community";
		}
		
		HttpSession session = req.getSession();
		session = req.getSession();
		
		String uid = (String) session.getAttribute("uid");
		int member_num = memberService.getUserNum(uid);
		
		boolean check = communityService.checkExistLike(board_num, member_num);
		if(check) {
			model.addAttribute("like", true);
		}else {
			model.addAttribute("like", false);
		}
		return "content";
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public String comment(Locale locale, Model model, HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		session = req.getSession();
		
		String uid = (String) session.getAttribute("uid");
		int member_num = memberService.getUserNum(uid);
		int board_num = Integer.parseInt(req.getParameter("board_num"));
		String comment = req.getParameter("comment");
		
		communityService.registerComment(comment, board_num, member_num);
		
		return "redirect:/content?board_num="+board_num;
	}
	
	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public String like(Locale locale, Model model, HttpServletRequest req, @RequestParam int board_num) {
		HttpSession session = req.getSession();
		session = req.getSession();
		
		String uid = (String) session.getAttribute("uid");
		int member_num = memberService.getUserNum(uid);
		
		communityService.registerLike(board_num, member_num);
		
		return "redirect:/content?board_num="+board_num;
	}
	
	@RequestMapping(value="/stock", method = RequestMethod.GET)
	public String stock(Locale locale, Model model, @RequestParam int idx) {
		List<Stock> list = processStockService.searchIdxStock(idx);
		model.addAttribute("list", list);
		
		return "stock";
	}
	
	//3초마다:*/3 * * * * * 매일 오전 6시마다: 0 0 6 * * *
	@Scheduled(cron="0 0 0/1 * * *")
	@Async
	public void checkForBatch(){
		List<Stock> stock = processStockService.searchTodayStock();
		//등록된 오늘 날짜의 주식이 없으면 주식 정보 요청
		if(stock == null) {
			JSONArray ret = requestStockService.getItemInfo();
			// 종목명이랑 가격만 빼주고 DB에 넣어주기
			HashMap<String, Integer> map = processStockService.parseItemInfo(ret);
			processStockService.registerStock(map);
		}
	}
}
