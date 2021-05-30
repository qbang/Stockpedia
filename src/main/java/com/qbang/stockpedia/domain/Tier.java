package com.qbang.stockpedia.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Tier
 *
 */
@Entity
@Table(name="tier")
public class Tier implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int num;
	private int user_num;
	private int tier_num;
	public int getNum() {
		return num;
	}
//	public void setNum(int num) {
//		this.num = num;
//	}
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
