package com.qbang.stockpedia.impl;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qbang.stockpedia.domain.Board;
import com.qbang.stockpedia.domain.Board_member;
import com.qbang.stockpedia.domain.Comment;
import com.qbang.stockpedia.persistence.CommunityDAOJPA;

@Service("CommunityService")
public class CommunityService {
	@Resource(name="CommunityDAOJPA")
	private CommunityDAOJPA communityDAOJPA;
	
	private Date today = new Date(new java.util.Date().getTime());
	
	public void registerContent(String title, String content, int num) {
		communityDAOJPA.insertContent(title, content, num, today);
	}
	
	public List<Board> getContentList() {
		List<Board> list = communityDAOJPA.selectContentList();
		return list;
	}
	
	public List<Board> getTopContentList() {
		List<Board> list = communityDAOJPA.selectContentTopList();
		return list;
	}
	
	public Board getSingleContent(int board_num) {
		Board board = communityDAOJPA.selectSingleContent(board_num);
		return board;
	}
	
	public void registerComment(String comment, int board_num, int member_num) {
		communityDAOJPA.insertComment(comment, board_num, member_num);
	}
	
	public List<Comment> getCommentList(int board_num) {
		List<Comment> list = communityDAOJPA.selectCommentList(board_num);
		return list;
	}
	
	public void registerLike(int board_num, int member_num) {
		communityDAOJPA.insertLike(board_num, member_num);
	}
	
	public boolean checkExistLike(int board_num, int member_num) {
		Board_member member = communityDAOJPA.selectSingleLike(board_num, member_num);
		if(member == null) {
			return false;
		}else {
			return true;
		}
	}
}
