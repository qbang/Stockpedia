package com.qbang.stockpedia.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="board_member")
@IdClass(Board_member_id.class)
public class Board_member {
	@Id
	private int board_num;
	@Id
	private int member_num;
	
	public Board_member() {}
	
	public Board_member(Board_member_id id) {
		this.board_num = id.getBoard_num();
		this.member_num = id.getMember_num();
	}
}
