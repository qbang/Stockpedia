package com.qbang.stockpedia.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Member
 *
 */
@Entity
@Table(name="member")
public class Member implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int num;
	private String uid;
	private String unick;
	private String upw;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUnick() {
		return unick;
	}
	public void setUnick(String unick) {
		this.unick = unick;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
}
