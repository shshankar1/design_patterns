package com.anz.handler;

import java.util.Map;

public class DirectRelationshipHandler implements RelationshipHandler {
	
	@Override
	public Double handler(Map<String, Double> exchangeRateMatrix, String srcCurrency, String targetCurrency){
		return exchangeRateMatrix.get(srcCurrency+targetCurrency);
	}
}
