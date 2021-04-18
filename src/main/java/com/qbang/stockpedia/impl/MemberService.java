package com.qbang.stockpedia.impl;

import javax.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.qbang.stockpedia.domain.Member;
import com.qbang.stockpedia.persistence.MemberDAOJPA;


@Service("MemberService")
public class MemberService {
	
	@Resource(name="MemberDAOJPA")
	private MemberDAOJPA memberDAOJPA;
	
	private BCryptPasswordEncoder passwordEcoder = new BCryptPasswordEncoder();
	
	//회원가입
	public void registerUser(String uid, String upw, String unick) {
		String encPw = passwordEcoder.encode(upw);
		memberDAOJPA.insertMember(uid, encPw, unick);
	}
	
	//로그인
	public boolean checkUser(String uid, String upw) {
		Member member = memberDAOJPA.selectMember(uid);
		
		if(member != null) {
			String id = member.getUid();
			String pw = member.getUpw();
			
			if(id.equals(uid) && passwordEcoder.matches(upw, pw)) {
				return true;
			}
		}
		
		return false;
	}
}
