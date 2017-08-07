package com.anz.main;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Calculator {
	
	static Logger logger = Logger.getLogger("Calculator.class");

	public static void main(String[] args) {
		
		//ignored ApplicationContext interface due to resource leak warning
		ClassPathXmlApplicationContext appContext = null;
		Scanner scanner = null;
		try{
			appContext = new ClassPathXmlApplicationContext(new String[] {"config\\ApplicationContext.xml"});
			FXCalculator calculator = (FXCalculator)appContext.getBean("FXCalculatorImpl");
			
			System.out.println("> ");
			scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			System.out.println(calculator.convert(input));
		} catch(Exception e) {
			logger.info(Arrays.toString(e.getStackTrace()));
		} finally {
			scanner.close();
			appContext.close();
		}
	}
}
