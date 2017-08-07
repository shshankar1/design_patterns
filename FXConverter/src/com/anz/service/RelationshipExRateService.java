package com.anz.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.anz.constant.Constant;
import com.anz.handler.*;

@Service
public class RelationshipExRateService {
	
	private Map<String, ? extends RelationshipHandler> handlers;
	
	@PostConstruct
	public void init(){
		handlers = new HashMap<String, RelationshipHandler>(){
			private static final long serialVersionUID = 1L;
			{
				put(Constant.directRelationship,new DirectRelationshipHandler());
				put(Constant.invRelationship,new InverseRelationshipHandler());
				put(null,new NullRelationshipHandler());
				put(Constant.crossRelationship, new CrossRelationshipHandler());
				put(Constant.OneToOne, new SameCurrencyRelationshipHandler());
			}
			};
	}
	public Double calculateExRate(String relationshipType, Map<String, Double> exchangeRateMatrix, String srcCurrency, String targetCurrency){
		RelationshipHandler handler = handlers.get(relationshipType);
		if(handler == null)
			handler = handlers.get(Constant.crossRelationship);
		return handler.handler(exchangeRateMatrix, srcCurrency, targetCurrency);
	}

}
