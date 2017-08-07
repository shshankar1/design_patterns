package com.anz.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.anz.domain.FXInput;

public class FXInputParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		FXInput fxInput = FXInputParser.parse("KRW 1000.00 in FJD");
		assertEquals(new Double(1000), fxInput.getAmount());
	}
	
	@Test
	public void test2() {
		FXInput fxInput = FXInputParser.parse("TYUI");
		assertEquals(null, fxInput);
	}

}
