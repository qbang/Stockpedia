package com.qbang.stockpedia.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.qbang.stockpedia.dto.EmfFactory;
import org.springframework.stereotype.Repository;

import com.qbang.stockpedia.domain.CommentCount;
import com.qbang.stockpedia.domain.Tier;

@Repository("TierDAOJPA")
public class TierDAOJPA {
	private static final int LOWEST_TIER_LEVEL = 5;
	private EntityManagerFactory emf = EmfFactory.getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tx = em.getTransaction();
	
	public List<CommentCount> selectCommentCount() {
		//String jpql = "select member_num, count(*) as count from comment group by member_num order by count desc";
		String jpql = "select member.num as member_num, count(*) as count from member left join comment on member.num = comment.member_num group by num order by count desc";
		List<CommentCount> list = null;
		Query query = em.createNativeQuery(jpql, CommentCount.class);
		try {
			list = (List<CommentCount>) query.getResultList();
		} catch (Exception e) {
			list = null;
		}
		return list;
	}
	
	public void deleteTier() {
		tx.begin();
		
		String jpql = "delete from Tier";
		Query query = em.createQuery(jpql);
		query.executeUpdate();
		
		tx.commit();
	}
	
	public void updateTier(List<CommentCount> list) {
		int len = list.size();

		try {
			tx.begin();
			
			for (int i = 0; i < len; i++) {
				int tierNum = 0;
				
				if (i < len * 0.04) {
					tierNum = 1;
				} else if (len * 0.04 < i && i < len * 0.1) {
					tierNum = 2;
				} else if (len * 0.1 <= i && i < len * 0.3) {
					tierNum = 3;
				} else if (len * 0.3 <= i && i < len * 0.6) {
					tierNum = 4;
				} else {
					tierNum = 5;
				}
				
				Tier tier = new Tier();
				tier.setTier_num(tierNum);
				tier.setUser_num(list.get(i).getMember_num());
				
				em.persist(tier);
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	public void insertTier(int uid) {
		Tier tier = new Tier();
		tier.setTier_num(LOWEST_TIER_LEVEL);
		tier.setUser_num(uid);

		em.persist(tier);
	}
}
