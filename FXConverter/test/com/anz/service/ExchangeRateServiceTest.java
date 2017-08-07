package com.anz.service;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import com.anz.constant.Constant;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class ExchangeRateServiceTest {
	
    private ExchangeRateService exchangeRateService;
	private RelationshipExRateService relationshipExRateService;

	@Before
    public void init() throws FileNotFoundException, IOException {
		exchangeRateService = new ExchangeRateService();
		relationshipExRateService = new RelationshipExRateService();
		relationshipExRateService.init();
		ReflectionTestUtils.setField(exchangeRateService, "currRelationshipMatrixFilePath", Constant.currMatrixFilePath);
		ReflectionTestUtils.setField(exchangeRateService, "exchangeRateMatrixPath", Constant.exchangeRateMatrixFilePath);
		ReflectionTestUtils.setField(exchangeRateService, "relationshipExRateService", relationshipExRateService);
		exchangeRateService.init();
    }

	@Test
	public void test1() {
		assertEquals(new Double(100), exchangeRateService.getConvertedAmout("USD", "USD", 100d));
	}
	
	@Test
	public void test2() {
		assertEquals(new Double(100.41d), exchangeRateService.getConvertedAmout("AUD", "JPY", 1d));
	}
	
	@Test
	public void test3() {
		assertEquals(new Double(0.14d), exchangeRateService.getConvertedAmout("NOK", "USD", 1d));
	}
	
	@Test
	public void test4() {
		assertEquals(new Double(0.14d), exchangeRateService.getConvertedAmout("NOK", "USD", 1d));
	}
	
	@Test
	public void test5() {
		assertEquals(null, exchangeRateService.getConvertedAmout("FKY", "USD", 1d));
	}
	
	@Test
	public void test6() {
		assertEquals(null, exchangeRateService.getConvertedAmout("USD", "ABC", 1d));
	}
	
	@Test
	public void test7() {
		assertEquals(new Double(83.71), exchangeRateService.getConvertedAmout("AUD", "USD", 100.00d));
	}

}
