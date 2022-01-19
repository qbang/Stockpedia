package com.qbang.stockpedia.impl;

import com.qbang.stockpedia.domain.Board;
import com.qbang.stockpedia.domain.Board_member;
import com.qbang.stockpedia.domain.CommentTier;
import com.qbang.stockpedia.persistence.CommunityDAOJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service("CommunityService")
@RequiredArgsConstructor
public class CommunityService {
	private final CommunityDAOJPA communityDAOJPA;
	
//	private Date today = new Date(new java.util.Date().getTime());
	
	// 글 등록하기
//	public void registerContent(String title, String content, int num) {
//		communityDAOJPA.insertContent(title, content, num, today);
//	}
	
	// community.jsp에 필요한 글리스트 가져오기  
//	public List<Board> getContentList() {
//		List<Board> list = communityDAOJPA.selectContentList();
//		return list;
//	}
	
	// detail.jsp에 필요한 인기글 가져오기 
//	public List<Board> getTopContentList() {
//		List<Board> list = communityDAOJPA.selectContentTopList();
//		return list;
//	}
	
	// content.jsp에 필요한 글 정보 가져오기 
//	public Board getSingleContent(int board_num) {
//		Board board = communityDAOJPA.selectSingleContent(board_num);
//		return board;
//	}
	
	// content.jsp에서 댓글 달기 
//	public void registerComment(String comment, int board_num, int member_num) {
//		communityDAOJPA.insertComment(comment, board_num, member_num);
//	}
	
	// content.jsp에 필요한 댓글 리스트 가져오기 
//	public List<CommentTier> getCommentList(int board_num) {
//		List<CommentTier> list = communityDAOJPA.selectCommentList(board_num);
//		return list;
//	}
	
	// content.jsp에서 좋아요 누르기 
//	public void registerLike(int board_num, int member_num) {
//		communityDAOJPA.insertLike(board_num, member_num);
//	}

	// content.jsp에서 상황에 맞는 버튼을 보여주기 위해 좋아요 이미 눌렀는지 체크하기  
	public boolean checkExistLike(int board_num, int member_num) {
		Board_member member = communityDAOJPA.selectSingleLike(board_num, member_num);
		if(member == null) {
			return false;
		}else {
			return true;
		}
	}
}
