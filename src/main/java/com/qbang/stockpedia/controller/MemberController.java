package com.qbang.stockpedia.controller;

import com.qbang.stockpedia.impl.MemberService;
import com.qbang.stockpedia.impl.TierService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final MemberService memberService;
    private final TierService tierService;

    @RequestMapping(value = "/member")
    public String member(HttpMethod httpMethod, HttpServletRequest request) throws UnsupportedEncodingException {
        String result = "";
        if (httpMethod.matches("GET")) {
            result = "register";
        } else if (httpMethod.matches("POST")) {
            request.setCharacterEncoding("UTF-8");

            String uid = request.getParameter("uid");
            String upw = request.getParameter("upw");
            String unick = request.getParameter("unick");

            memberService.registerUser(uid, upw, unick);

            HttpSession session = request.getSession();
            session.setAttribute("unick", unick);
            session.setAttribute("uid", uid);

            tierService.updateTier();

            result = "redirect:/";
        }
        return result;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        String upw = request.getParameter("upw");
        String unick = memberService.checkUser(uid, upw);

        System.out.println("??????????"+uid+"/"+upw+"/"+unick);

        if (unick == null) {	//로그인 실패
            return "redirect:/login";
        } else { //로그인 성공
            HttpSession session = request.getSession();
            session.setAttribute("unick", unick);
            session.setAttribute("uid", uid);

            logger.info("# login id = " + uid + " at "+ simpleDateFormat.format(System.currentTimeMillis()));
        }

        return "redirect:/";
    }

}
