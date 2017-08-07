package com.anz.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anz.constant.Constant;
import com.anz.utils.CsvHelper;

@Service
public class ExchangeRateService {
	Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	
	@Value(Constant.currMatrixFilePath)
	private String currRelationshipMatrixFilePath;
	
	@Value(Constant.exchangeRateMatrixFilePath)
	private String exchangeRateMatrixPath;
	
	private Map<String, Double> exchangeRateMatrix;
	private Map<String, Map<String, String>> currRelationshipMatrix;
	
	@Autowired
	RelationshipExRateService relationshipExRateService;
	
	@PostConstruct
	public void init() throws FileNotFoundException, IOException{
		this.exchangeRateMatrix = CsvHelper.generateExchangeRateMatrix(exchangeRateMatrixPath);
		this.currRelationshipMatrix = CsvHelper.generateCurrencyMatrix(currRelationshipMatrixFilePath);
	}
	
	public Double getConvertedAmout(String srcCurr, String targetCurr, Double amount){
		Double exchangeRate = getExchangeRate(srcCurr, targetCurr, 1d);
		logger.info("Exchange Rate="+exchangeRate);
		return exchangeRate!=null?Math.floor(amount*exchangeRate*100)/100:null;
	}
	
	//tail recursion implementation to find the conversion rate
	private Double getExchangeRate(String srcCurr, String targetCurr, Double exchangeRate){
		String relationshipType = currRelationshipMatrix.get(srcCurr) != null ? currRelationshipMatrix
				.get(srcCurr).get(targetCurr) : null;
		
		if(null == relationshipType || relationshipType.equals(Constant.OneToOne)){
			Double rate = relationshipExRateService.calculateExRate(relationshipType, exchangeRateMatrix, srcCurr, targetCurr);
			return rate!=null?exchangeRate*=rate:null;
		}else{
			exchangeRate *= relationshipExRateService
					.calculateExRate(
							relationshipType,
							exchangeRateMatrix,
							srcCurr,
							(relationshipType.equals(Constant.directRelationship) 
									|| relationshipType.equals(Constant.invRelationship))? targetCurr
									: relationshipType);
			return getExchangeRate(relationshipType, targetCurr, exchangeRate);
		}		
	}
}
