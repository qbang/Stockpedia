package com.qbang.stockpedia.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qbang.stockpedia.domain.CommentCount;
import com.qbang.stockpedia.persistence.TierDAOJPA;

@Service("TierService")
public class TierService {
	@Resource(name="TierDAOJPA")
	private TierDAOJPA tierDAOJPA;
	
	public void updateTier() {
		List<CommentCount> list = tierDAOJPA.selectCommentCount();
		if(list != null) {
			tierDAOJPA.deleteTier();
			tierDAOJPA.insertTier(list);
		}
	}
}
