package com.ericsson.oss.nfe.poc.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandParserUtil {
 
	public static String[] getOutPutTokens(String commandOutPut) {
		
		 Scanner scanner = new Scanner(commandOutPut);
		 List<String> tokens = new ArrayList<String>();
		 
		 while(scanner.hasNext())
			 tokens.add(scanner.next());
		 
		return tokens.toArray(new String[]{});
	}

	private static boolean verifyIPRange(String ipRange) {

		String ip;
		int prefix;
		
		String[] parts = ipRange.split("/");
		ip = parts[0];

		if (parts.length < 2) {
			return false;
		} else {
			prefix = Integer.parseInt(parts[1]);
		}

		return true;
	}
	
	 
}
