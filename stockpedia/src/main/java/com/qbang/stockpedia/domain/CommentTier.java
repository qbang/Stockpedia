package com.qbang.stockpedia.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CommentTier
 *
 */
@Entity
public class CommentTier implements Serializable {
	@Id
	private int comment_num;
	private String content;
	private int board_num;
	private int member_num;
	private int num;
	private int user_num;
	private int tier_num;
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public int getTier_num() {
		return tier_num;
	}
	public void setTier_num(int tier_num) {
		this.tier_num = tier_num;
	}
   
}
