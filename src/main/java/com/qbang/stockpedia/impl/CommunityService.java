package com.qbang.stockpedia.impl;

import com.qbang.stockpedia.domain.Board_member;
import com.qbang.stockpedia.persistence.CommunityDAOJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("CommunityService")
@RequiredArgsConstructor
public class CommunityService {
	private final CommunityDAOJPA communityDAOJPA;

	// content.jsp에서 상황에 맞는 버튼을 보여주기 위해 좋아요 이미 눌렀는지 체크하기  
	public boolean checkExistLike(int board_num, int member_num) {
		Board_member member = communityDAOJPA.selectSingleLike(board_num, member_num);
		return member == null ? false : true;
	}
}
