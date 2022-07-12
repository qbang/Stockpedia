package com.qbang.stockpedia.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CommentCount
 *
 */
@Entity
public class CommentCount implements Serializable {
	@Id
	private int member_num;
	private int count;
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
