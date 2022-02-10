package com.qbang.stockpedia.persistence;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.qbang.stockpedia.domain.EmfFactory;
import org.springframework.stereotype.Repository;

import com.qbang.stockpedia.domain.Stock;

@Repository("StockDAOJPA")
public class StockDAOJPA {
	private EntityManagerFactory emf = EmfFactory.getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tx = em.getTransaction();
	
	public void insertStock(HashMap<String, Integer> map, Date sqlDate) {
		try {
			tx.begin();
			
			for (String key : map.keySet()) {
				Stock stock = new Stock();
				
				stock.setName(key);
				stock.setReg_date(sqlDate);
				stock.setValue(map.get(key));
				
				em.persist(stock);
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	}
	
	public List<Stock> selectStock(Date date) {
		List<Stock> list = new ArrayList<Stock>();
//		String jpql = "select s from Stock as s where s.reg_date = '"+date+"'";
		String jpql = "select s from Stock as s where s.reg_date = '2021-06-04'";
		try {
			list = em.createQuery(jpql, Stock.class).getResultList();
		} catch (Exception e) {
			list = null;
		}
		return list;
	}
	
	public List<Stock> selectIdxStock(int start, int end, Date date, int idx){
		List<Stock> list = new ArrayList<>();
//		String jpql = "select s from Stock as s where s.reg_date = '"+date+"' and s.value >= "+start+" and s.value < "+end+" order by s.value";
		String jpql = "select s from Stock as s where s.reg_date = '2021-06-04' and s.value >= "+start+" and s.value < "+end+" order by s.value";
		try {
			list = em.createQuery(jpql, Stock.class)
					.setFirstResult(15 * idx)
					.setMaxResults(14)
					.getResultList();
		} catch (Exception e) {
			list = null;
		}
		return list;
	}
}
