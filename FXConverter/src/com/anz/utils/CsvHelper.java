package com.anz.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvHelper {
	
	public static Map<String, Map<String, String>> generateCurrencyMatrix(
			String currencyMatrixFilePath) throws FileNotFoundException, IOException {
		Map<String, Map<String, String>> currMatrixContext = new HashMap<String, Map<String, String>>();
		CSVFormat csvFileFormat = CSVFormat.TDF.withFirstRecordAsHeader();
		try (CSVParser csvParser = new CSVParser(new FileReader(currencyMatrixFilePath), csvFileFormat)) {			
			List<CSVRecord> records = csvParser.getRecords();
			IntStream.range(0, records.size()).forEach(row ->{
				Map<String, String> relation = records.get(row).toMap();
				relation.remove("/");
				currMatrixContext.put(records.get(row).get(0), relation);
			});
		} 
		return currMatrixContext;
	}

	public static Map<String, Double> generateExchangeRateMatrix(String exchangeRateMatrixFilePath) 
			throws FileNotFoundException, IOException {
		Map<String, Double> exchangeRateMatrix = new HashMap<String, Double>();
		
		try (CSVParser csvParser = new CSVParser(new FileReader(exchangeRateMatrixFilePath), CSVFormat.TDF)) {
			csvParser.getRecords().forEach(row->{
				if(row.size()==2)
					exchangeRateMatrix.put(row.get(0), Double.parseDouble(row.get(1)));
			});
		}
		return exchangeRateMatrix;
	}
	
	public static Map<String, String> getCurrencyPrecisionMap(String currencyPrecisionMatrixFilePath) 
			throws FileNotFoundException, IOException {
		Map<String, String> currencyPrecisionMatrix = new HashMap<String, String>();

		try (CSVParser csvParser = new CSVParser(new FileReader(currencyPrecisionMatrixFilePath), CSVFormat.TDF)) {
			csvParser.getRecords().forEach(row -> {
				if (row.size() == 2)
					currencyPrecisionMatrix.put(row.get(0), row.get(1));
			});
		}
		return currencyPrecisionMatrix;
	}
}
