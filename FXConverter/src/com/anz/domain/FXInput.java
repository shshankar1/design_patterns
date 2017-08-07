package com.anz.domain;

public class FXInput {

	private final String sourceCurrency;
	private final String targetCurrency;
	private final Double amount;
	
	public FXInput(String sourceCurrency, String targetCurrency, Double amount) {
		super();
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.amount = amount;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public Double getAmount() {
		return amount;
	}	
	
}
