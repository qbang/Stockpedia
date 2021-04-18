package com.qbang.stockpedia;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qbang.stockpedia.impl.MemberService;
import com.qbang.stockpedia.impl.StockInfoService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private StockInfoService stockInfoService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model){
		return "login";
//		return "home";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Locale locale, Model model,  HttpServletRequest req) {
		HttpSession session = req.getSession();
		session = req.getSession();
		
		Object unick = session.getAttribute("unick");
		if(unick == null) {
			model.addAttribute("unick", "게스트");
		}else {
			model.addAttribute("unick", unick.toString());
		}
		return "detail";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		
		String uid = req.getParameter("uid");
		String upw = req.getParameter("upw");
		
		if(memberService.checkUser(uid, upw)) {	//로그인 성공
			return "redirect:/detail";
		}else { //로그인 실패
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/reqRegister", method = RequestMethod.GET)
	public String reqRegister(Locale locale, Model model) {
		return "register";
	}
	
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public String doRegister(Locale locale, Model model,  HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		
		String uid = req.getParameter("uid");
		String upw = req.getParameter("upw");
		String unick = req.getParameter("unick");
		
		memberService.registerUser(uid, upw, unick);
		
		HttpSession session = req.getSession();
		session = req.getSession();
		session.setAttribute("unick", unick);
		
		return "redirect:/detail";
	}
	
	//3초마다:*/3 * * * * * 매일 오후 6시마다: 0 0 18 * * *
	@Scheduled(cron="0/3 * * * * *")
	@Async
	public void checkForBatch(){
		logger.debug("############## 스케줄러 작동 중");
		JSONArray ret = stockInfoService.getItemInfo();
		if(ret.length() == 0) {
			checkForBatch();
		}else {
			// 종목명이랑 가격만 빼주기
			HashMap<String, Integer> map = stockInfoService.parseItemInfo(ret);
		}
	}
}
