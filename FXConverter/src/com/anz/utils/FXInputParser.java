package com.anz.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.anz.domain.FXInput;

public class FXInputParser {

	public static FXInput parse(String input){
		Pattern originalPattern = Pattern.compile("[A-Z]*\\s\\d+\\.\\d+\\sin\\s[A-Z]*");
		Pattern alternatePattern = Pattern.compile("[A-Z]*\\s\\d+\\sin\\s[A-Z]*");
		Matcher m = originalPattern.matcher(input);
		FXInput fxInput = null;
		if(m.find()){
			String[] args = input.split(" ");
			fxInput = new FXInput(args[0], args[args.length-1], Double.parseDouble(args[1]));
		}else{
			m = alternatePattern.matcher(input);
			if(m.find()){
				String[] args = input.split(" ");
				fxInput = new FXInput(args[0], args[args.length-1], Double.parseDouble(args[1]));
			}
		}
		return fxInput;
	}
}
