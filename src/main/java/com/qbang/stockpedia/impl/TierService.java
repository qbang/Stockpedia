package com.qbang.stockpedia.impl;

import com.qbang.stockpedia.domain.CommentCount;
import com.qbang.stockpedia.persistence.TierDAOJPA;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TierService")
public class TierService {
	private TierDAOJPA tierDAOJPA;

	public TierService() {
		this.tierDAOJPA = new TierDAOJPA();
	}
	
	public void updateTier() {
		List<CommentCount> list = tierDAOJPA.selectCommentCount();
		if(list != null) {
			tierDAOJPA.deleteTier();
			tierDAOJPA.insertTier(list);
		}
	}
}
