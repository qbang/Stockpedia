package com.qbang.stockpedia.impl;

import com.qbang.stockpedia.domain.Member;
import com.qbang.stockpedia.persistence.MemberDAOJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("MemberService")
@RequiredArgsConstructor
public class MemberService {
	private final MemberDAOJPA memberDAOJPA;
	private BCryptPasswordEncoder passwordEcoder = new BCryptPasswordEncoder();

	//회원가입
	public void registerUser(String uid, String upw, String unick) {
		String encPw = passwordEcoder.encode(upw);
		memberDAOJPA.insertMember(uid, encPw, unick);
	}
	
	//로그인
	public String checkUser(String uid, String upw) {
		Member member = memberDAOJPA.selectMember(uid);
		
		if (member != null) {
			String id = member.getUid();
			String pw = member.getUpw();
			String nick = member.getUnick();
			
			if (id.equals(uid) && passwordEcoder.matches(upw, pw)) {
				return nick;
			}
		}
		return null;
	}
	
	//글 등록에 필요한 user num 가져오기
	public int getUserNum(String uid) {
		Member member = memberDAOJPA.selectMember(uid);
		if (member != null) {
			return member.getNum();
		}
		return -1;
	}
	
	
}
