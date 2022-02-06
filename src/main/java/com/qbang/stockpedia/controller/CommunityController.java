package com.qbang.stockpedia.controller;

import com.qbang.stockpedia.domain.Board;
import com.qbang.stockpedia.domain.CommentTier;
import com.qbang.stockpedia.impl.CommunityService;
import com.qbang.stockpedia.impl.MemberService;
import com.qbang.stockpedia.persistence.CommunityDAOJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController {
    private static java.sql.Date today = new java.sql.Date(new java.util.Date().getTime());

    private final CommunityService communityService;
    private final MemberService memberService;
    private final CommunityDAOJPA communityDAOJPA;

    @GetMapping("/community")
    public String community(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("uid") == null) {
            return "redirect:/login";
        }
        // 글 리스트 가져오기
        List<Board> list = communityDAOJPA.selectContentList();
        model.addAttribute("list", list);

        return "community";
    }

    @GetMapping("/reqWrite")
    public String reqWrite() {
        return "write";
    }

    @PostMapping("/doWrite")
    public String doWrite(HttpServletRequest req) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        String uid = (String) session.getAttribute("uid");
        int num = memberService.getUserNum(uid);
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        // 게시글 등록
        communityDAOJPA.insertContent(title, content, num, today);

        return "redirect:/community";
    }

    @GetMapping("/post")
    public String content(Model model, HttpServletRequest req, @RequestParam int board_num) {
        HttpSession session = req.getSession();

        if(session.getAttribute("uid") == null) {
            return "redirect:/login";
        }
        //클릭 게시글 내용 가져오기
        Board board = communityDAOJPA.selectSingleContent(board_num);

        //게시글 로드 과정에서 오류가 나지 않았을 때
        if (board != null) {
            //댓글 리스트 가져오기
            List<CommentTier> comment = communityDAOJPA.selectCommentList(board_num);

            model.addAttribute("content", board);
            model.addAttribute("comment", comment);
        } else {
            return "redirect:/community";
        }

        //현재 이용자가 등록한 좋아요가 있는지 검사
        int member_num = memberService.getUserNum((String) session.getAttribute("uid"));
        boolean check = communityService.checkExistLike(board_num, member_num);

        model.addAttribute("like", check ? true : false);

        return "post";
    }

    @PostMapping("/comment")
    public String comment(HttpServletRequest req) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        String uid = (String) session.getAttribute("uid");
        int member_num = memberService.getUserNum(uid);
        int board_num = Integer.parseInt(req.getParameter("board_num"));
        String comment = req.getParameter("comment");
        //댓글 등록
        communityDAOJPA.insertComment(comment, board_num, member_num);

        return "redirect:/post?board_num=" + board_num;
    }

    @PostMapping( "/like")
    public String like(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String board_num = request.getParameter("board_num");
        String uid = (String) session.getAttribute("uid");
        int member_num = memberService.getUserNum(uid);
        // 이미 좋아요를 눌렀는데 또 눌렀을 때 처리 필요
        communityDAOJPA.insertLike(Integer.parseInt(board_num), member_num);

        return "redirect:/post?board_num=" + board_num;
    }

}
