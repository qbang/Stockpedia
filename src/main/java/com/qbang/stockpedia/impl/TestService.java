package com.qbang.stockpedia.impl;

import com.qbang.stockpedia.domain.Member;
import com.qbang.stockpedia.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TestService {
    MemberRepository memberRepository;

    TestService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMember(String uid) {
        return memberRepository.findByUid(uid);
    }
}
