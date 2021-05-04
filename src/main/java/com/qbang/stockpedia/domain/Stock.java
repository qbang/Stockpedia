package com.qbang.stockpedia.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Stock
 *
 */
@Entity
@Table(name="stock")
public class Stock implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stock_num;
	private String name;
	private int value;
	private Date reg_date;
	
	public int getStock_num() {
		return stock_num;
	}
	public void setStock_num(int stock_num) {
		this.stock_num = stock_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}
