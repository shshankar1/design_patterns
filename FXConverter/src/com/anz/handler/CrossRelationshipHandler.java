package com.anz.handler;

import java.util.Map;

public class CrossRelationshipHandler implements RelationshipHandler {

	@Override
	public Double handler(Map<String, Double> exchangeRateMatrix, String srcCurrency, String targetCurrency) {
		Double result=1d;
		
		result =  exchangeRateMatrix.get(srcCurrency + targetCurrency) != null ? exchangeRateMatrix
				.get(srcCurrency + targetCurrency) : (1 / exchangeRateMatrix
				.get(targetCurrency + srcCurrency));
				
		return result;
	}
}
