package com.anz.main.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anz.constant.Constant;
import com.anz.domain.FXInput;
import com.anz.main.FXCalculator;
import com.anz.service.ExchangeRateService;
import com.anz.utils.CsvHelper;
import com.anz.utils.FXInputParser;

@Component
public class FXCalculatorImpl implements FXCalculator{
	
	Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	ExchangeRateService service;
	
	Map<String, String> currencyPrecisionMatrix;
	DecimalFormat outputDataFormat = new DecimalFormat("0.00");
	
	@PostConstruct
	public void init() throws FileNotFoundException, IOException{
		currencyPrecisionMatrix = CsvHelper.getCurrencyPrecisionMap(Constant.currencyPrecisionMatrixFilePath);
	}

	@Override
	public String convert(String input) {
		logger.info(input);
		FXInput fxInput = FXInputParser.parse(input);
		
		if(null ==fxInput)
			return Constant.invalidInput;
		
		Double convertedAmount = service.getConvertedAmout(
				fxInput.getSourceCurrency(), fxInput.getTargetCurrency(),
				fxInput.getAmount());
		
		logger.info("Converted Amout="+convertedAmount);
		
		StringBuilder output = new StringBuilder();
		
		if(convertedAmount==null){
			output.append(Constant.unableToFindRate)
			.append(fxInput.getSourceCurrency())
			.append("/")
			.append(fxInput.getTargetCurrency());
		}else{
			DecimalFormat sourceCurrOpFormat = new DecimalFormat(currencyPrecisionMatrix.get(fxInput.getSourceCurrency()));
			DecimalFormat targetCurrOpFormat = new DecimalFormat(currencyPrecisionMatrix.get(fxInput.getTargetCurrency()));
			output.append(fxInput.getSourceCurrency() + " ")
					.append(sourceCurrOpFormat.format(fxInput.getAmount())+ " ")
					.append("= ")
					.append(fxInput.getTargetCurrency() + " ")
					.append(targetCurrOpFormat.format(convertedAmount));
			
		}
		logger.info(output.toString());
		return output.toString();
	}

}
