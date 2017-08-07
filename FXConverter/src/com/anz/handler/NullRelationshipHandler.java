package com.anz.handler;

import java.util.Map;

import com.anz.constant.Constant;

public class NullRelationshipHandler implements RelationshipHandler {

	@Override
	public Double handler(Map<String, Double> exchangeRateMatrix, String srcCurrency, String targetCurrency) {
		if (srcCurrency.equalsIgnoreCase(targetCurrency)
				|| srcCurrency.equals(Constant.directRelationship)
				|| srcCurrency.equals(Constant.invRelationship)
				|| srcCurrency.equals(Constant.OneToOne))
			return 1d;

		return null;
	}
}
