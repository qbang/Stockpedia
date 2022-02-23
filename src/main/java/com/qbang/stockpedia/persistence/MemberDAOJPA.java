package com.qbang.stockpedia.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.qbang.stockpedia.dto.EmfFactory;
import org.springframework.stereotype.Repository;

import com.qbang.stockpedia.domain.Member;

@Repository("MemberDAOJPA")
public class MemberDAOJPA {
	private EntityManagerFactory emf = EmfFactory.getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tx = em.getTransaction();
	
	public Member insertMember(String uid, String upw, String unick) {
		try {
			tx.begin();
			
			Member member = new Member();
			member.setUid(uid);
			member.setUnick(unick);
			member.setUpw(upw);
			
			em.persist(member);
			
			tx.commit();

			return member;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		return null;
	}
	
	public Member selectMember(String uid) {
		String jpql = "select m from Member as m where m.uid = '"+uid+"'";
		Member member;
		try {
			member = em.createQuery(jpql, Member.class).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			member = null;
		}
		return member;
	}
}
