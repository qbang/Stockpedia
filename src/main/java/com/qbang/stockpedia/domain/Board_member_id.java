package com.qbang.stockpedia.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Board_member
 *
 */

public class Board_member_id implements Serializable {
	private int board_num;
	private int member_num;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board_member_id id = (Board_member_id) o;
        return (board_num == id.board_num) &&
        		(member_num == id.member_num);
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(board_num, member_num);
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


}
