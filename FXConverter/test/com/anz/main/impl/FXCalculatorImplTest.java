package com.anz.main.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.anz.constant.Constant;
import com.anz.main.FXCalculator;
import com.anz.service.ExchangeRateService;
import com.anz.service.RelationshipExRateService;

public class FXCalculatorImplTest {

	FXCalculator calculator;
	@Before
	public void setUp() throws Exception {
		calculator = new FXCalculatorImpl();
		((FXCalculatorImpl)calculator).init();
		
		ExchangeRateService exchangeRateService = new ExchangeRateService();
		RelationshipExRateService relationshipExRateService = new RelationshipExRateService();
		relationshipExRateService.init();
		ReflectionTestUtils.setField(exchangeRateService, "currRelationshipMatrixFilePath", Constant.currMatrixFilePath);
		ReflectionTestUtils.setField(exchangeRateService, "exchangeRateMatrixPath", Constant.exchangeRateMatrixFilePath);
		ReflectionTestUtils.setField(exchangeRateService, "relationshipExRateService", relationshipExRateService);
		exchangeRateService.init();
		
		ReflectionTestUtils.setField(calculator, "service", exchangeRateService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		String convertedData = calculator.convert("AUD 100.21 in USD");
		assertEquals("AUD 100.21 = USD 83.88", convertedData);
	}
	
	@Test
	public void test2() {
		String convertedData = calculator.convert("AUD 100.00 in USD");
		assertEquals("AUD 100.00 = USD 83.71", convertedData);
	}
	
	@Test
	public void test3() {
		String convertedData = calculator.convert("AUD 100.00 in AUD");
		assertEquals("AUD 100.00 = AUD 100.00", convertedData);
	}
	
	@Test
	public void test4() {
		String convertedData = calculator.convert("AUD 100.00 in DKK");
		assertEquals("AUD 100.00 = DKK 505.76", convertedData);
	}
	
	@Test
	public void test5() {
		String convertedData = calculator.convert("JPY 100 in USD");
		assertEquals("JPY 100 = USD 0.83", convertedData);
	}
	
	@Test
	public void test6() {
		String convertedData = calculator.convert("KRW 1000.00 in FJD");
		assertEquals("Unable to find rate for KRW/FJD", convertedData);
	}

}
