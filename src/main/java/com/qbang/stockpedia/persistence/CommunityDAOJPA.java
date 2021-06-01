package com.qbang.stockpedia.persistence;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.qbang.stockpedia.domain.Board;
import com.qbang.stockpedia.domain.Board_member;
import com.qbang.stockpedia.domain.Board_member_id;
import com.qbang.stockpedia.domain.Comment;
import com.qbang.stockpedia.domain.CommentCount;
import com.qbang.stockpedia.domain.CommentTier;

@Repository("CommunityDAOJPA")
public class CommunityDAOJPA {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Stockpedia");
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tx = em.getTransaction();
	
	public void insertContent(String title, String content, int num, Date today) {
		try {
			tx.begin();
			
			Board board = new Board();
			board.setTitle(title);
			board.setContent(content);
			board.setMember_num(num);
			board.setReg_date(today);
			
			em.persist(board);
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public List<Board> selectContentList() {
		String jpql = "select m from Board as m order by m.board_num desc";
		List<Board> list;
		try {
			list = em.createQuery(jpql, Board.class).getResultList();
		}catch(Exception e){
			list = null;
		}
		return list;
	}
	
	public List<Board> selectContentTopList() {
		String jpql = "select board.* from board right join (select board_num, count(*) as cnt from board_member group by board_num order by cnt desc limit 6) as s on s.board_num = board.board_num";
		List<Board> list = null;
		Query query = em.createNativeQuery(jpql, Board.class);
		try {
			list = (List<Board>) query.getResultList();
		}catch(Exception e){
			list = null;
		}
		return list;
	}
	
	public Board selectSingleContent(int board_num) {
		String jpql = "select m from Board as m where m.board_num ="+board_num;
		Board board;
		try {
			board = em.createQuery(jpql, Board.class).getSingleResult();
		}catch(Exception e){
			board = null;
		}
		return board;
	}
	
	public void insertComment(String content, int board_num, int member_num) {
		try {
			tx.begin();
			
			Comment comment = new Comment();
			comment.setBoard_num(board_num);
			comment.setContent(content);
			comment.setMember_num(member_num);
			
			em.persist(comment);
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public List<CommentTier> selectCommentList(int board_num) {
		String jpql = "select * from (select * from comment where comment.board_num = "+board_num+") as comment join tier on comment.member_num = tier.user_num";
		List<CommentTier> list = null;
		
		try {
			list = em.createNativeQuery(jpql, CommentTier.class).getResultList();
		}catch(Exception e){
			list = null;
		}
		return list;
	}
	
	public void insertLike(int board_num, int member_num) {
		try {
			tx.begin();
			
			Board_member_id board_member_id = new Board_member_id();
			board_member_id.setBoard_num(board_num);
			board_member_id.setMember_num(member_num);
			
			Board_member board_member = new Board_member(board_member_id);
			
			em.persist(board_member);
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public Board_member selectSingleLike(int board_num, int member_num) {
		Board_member board_member;
		
		String jpql = "select m from Board_member as m where m.board_num ="+board_num+" and m.member_num ="+member_num;
		try {
			board_member = em.createQuery(jpql, Board_member.class).getSingleResult();
		}catch(Exception e){
			board_member = null;
		}
		return board_member;
	}
}
