package com.anz.utils;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.anz.constant.Constant;

import static org.junit.Assert.*;

public class CsvHelperTest {

	Map<String, Map<String, String>> currMatrixContext;
	Map<String,Double> exchangeRateMatrix;
	Map<String, String> currencyPrecisionMatrix;

	@Before
	public void setUp() throws Exception {
		currMatrixContext = CsvHelper.generateCurrencyMatrix(Constant.currMatrixFilePath);
		exchangeRateMatrix = CsvHelper.generateExchangeRateMatrix(Constant.exchangeRateMatrixFilePath);
		currencyPrecisionMatrix = CsvHelper.getCurrencyPrecisionMap(Constant.currencyPrecisionMatrixFilePath);
		//CsvHelper.generatCurrencyMatrix(Constant.currMatrixFilePath);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		assertEquals("1", currMatrixContext.get("USD").get("USD"));
	}

	@Test
	public void test2() {
		assertEquals("Inv", currMatrixContext.get("USD").get("AUD"));
	}

	@Test
	public void test3() {
		assertEquals("D", currMatrixContext.get("AUD").get("USD"));
	}

	@Test
	public void test4() {
		assertNotEquals("D", currMatrixContext.get("USD").get("USD"));
	}
	
	@Test
	public void test5() {
		assertEquals(new Double(0.8371), exchangeRateMatrix.get("AUDUSD"));
	}
	
	@Test
	public void test6() {
		assertEquals(new Double(1.5683), exchangeRateMatrix.get("GBPUSD"));
	}
	
	@Test
	public void test7() {
		assertNotEquals(new Double(1.5683), exchangeRateMatrix.get("ABYUTF"));
	}
	
	@Test
	public void test8() {
		assertEquals("0", currencyPrecisionMatrix.get("JPY"));
	}

}
