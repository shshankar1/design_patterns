package com.anz.handler;

import java.util.Map;

public class SameCurrencyRelationshipHandler implements RelationshipHandler {

	@Override
	public Double handler(Map<String, Double> exchangeRateMatrix, String srcCurrency, String targetCurrency) {
		return 1d;
	}

}
