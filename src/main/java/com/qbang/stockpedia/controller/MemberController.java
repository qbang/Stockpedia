package com.qbang.stockpedia.controller;

import com.qbang.stockpedia.impl.MemberService;
import com.qbang.stockpedia.impl.TierService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private static final String WARN_EXIST_MEMBER = "동일한 아이디가 존재합니다. 다른 계정 정보를 입력해주세요!";
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final MemberService memberService;
    private final TierService tierService;

    @GetMapping("/member")
    public String member() {
        return "register";
    }

    @PostMapping("/member")
    public String member(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        String uid = request.getParameter("uid");
        String upw = request.getParameter("upw");
        String unick = request.getParameter("unick");

        if (memberService.checkUser(uid, upw) != null) {
            model.addAttribute("warning", WARN_EXIST_MEMBER);
            return "register";
        }

        memberService.registerUser(uid, upw, unick);

        HttpSession session = request.getSession();
        session.setAttribute("unick", unick);
        session.setAttribute("uid", uid);

        tierService.updateTier();

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        String upw = request.getParameter("upw");
        String unick = memberService.checkUser(uid, upw);

        if (unick == null) {	//로그인 실패
            return "redirect:/login";
        }

        //로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute("unick", unick);
        session.setAttribute("uid", uid);

        logger.info("# login id = " + uid + " at "+ simpleDateFormat.format(System.currentTimeMillis()));

        return "redirect:/";
    }

}
