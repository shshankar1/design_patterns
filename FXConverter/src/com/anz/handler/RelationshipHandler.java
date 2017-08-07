package com.anz.handler;

import java.util.Map;

public interface RelationshipHandler {
	public Double handler(Map<String, Double> exchangeRateMatrix, String srcCurrency, String targetCurrency);
}
