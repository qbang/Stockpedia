package com.qbang.stockpedia.persistence;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.qbang.stockpedia.domain.Stock;

@Repository("StockDAOJPA")
public class StockDAOJPA {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Stockpedia");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tx = em.getTransaction();
	
	public void insertStock(HashMap<String, Integer> map, Date sqlDate) {
		try {
			tx.begin();
			
			for(String key : map.keySet()) {
				Stock stock = new Stock();
				
				stock.setName(key);
				stock.setReg_date(sqlDate);
				stock.setValue(map.get(key));
				
				em.persist(stock);
			}
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		emf.close();
	}
	
	public List<Stock> selectStock(Date today) {
		List<Stock> list = new ArrayList<Stock>();
		String jpql = "select s from Stock as s where s.reg_date = '"+today+"'";
		try {
			list = em.createQuery(jpql, Stock.class).getResultList();
		}catch(Exception e){
			list = null;
		}
		return list;
	}
	
	public List<Stock> selectIdxStock(int start, int end, Date today){
		List<Stock> list = new ArrayList<Stock>();
		String jpql = "select s from Stock as s where s.reg_date = '"+today+"' and s.value >= "+start+" and s.value < "+end+" order by s.value";
		try {
			list = em.createQuery(jpql, Stock.class).getResultList();
		}catch(Exception e){
			list = null;
		}
		return list;
	}
}
