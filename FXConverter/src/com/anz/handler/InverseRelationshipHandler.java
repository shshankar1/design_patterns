package com.anz.handler;

import java.util.Map;

public class InverseRelationshipHandler implements RelationshipHandler {

	@Override
	public Double handler(Map<String, Double> exchangeRateMatrix, String srcCurrency, String targetCurrency) {
		return 1/exchangeRateMatrix.get(targetCurrency+srcCurrency);
	}
}
