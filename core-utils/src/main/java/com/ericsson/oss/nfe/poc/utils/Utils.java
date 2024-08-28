package com.ericsson.oss.nfe.poc.utils;

import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

	private static Logger logger = LoggerFactory.getLogger(Utils.class);

	public static int toInt(String integerStr, int defaultVal) {

		int returnval = defaultVal;

		if (StringUtils.isNumeric(integerStr)) {
			try {
				returnval = Integer.parseInt(integerStr);
			} catch (NumberFormatException nfe) {
			}
		}
		return returnval;

	}

	public static String extractVDCID(String fullVDCStr) {

		if (StringUtils.isNotBlank(fullVDCStr) && fullVDCStr.contains("(")) {
			int strt = fullVDCStr.indexOf("(");
			int end = fullVDCStr.indexOf(")");
			return fullVDCStr.substring(strt + 1, end);

		}

		return fullVDCStr;
	}

	public static String extractMOname(String fdnString) {

		if (StringUtils.isNotBlank(fdnString) && fdnString.contains("ManagedElement=")) {
			int strt = fdnString.indexOf("ManagedElement=");
			int end = fdnString.indexOf(",", strt);
			return (end == -1) ? fdnString.substring(strt + 15) : fdnString.substring(strt + 15, end);

		}

		return fdnString;
	}
	
	public static String extractENMMOname(String fdnString)
	{
		 
		
		if (StringUtils.isNotBlank(fdnString)&& fdnString.contains("NetworkElement="))
		{
			int strt = fdnString.indexOf("NetworkElement=");
			int end = fdnString.indexOf(",", strt);		
			return 	(end==-1)?fdnString.substring(strt+15):fdnString.substring(strt+15, end);
			
		}
		
		return fdnString;
	}

	public static String getFDNfromVDCID(String vdcId) {
		String nameIDMapStr = FileUtils.loadStreamAsString(FileUtils.loadFileFromAppConfig("MO_VDCID.properties"));
		String enmFDN = null;

		if (nameIDMapStr.contains(vdcId)) {
			String[] nameIDMap = nameIDMapStr.split("\n");
			for (String nameID : nameIDMap) {
				if (nameID.contains(vdcId)) {
					enmFDN = nameID.split("=")[0];
					return enmFDN;
				}
			}
		} else {
			return null;
		}
		return enmFDN;
	}
	public static int getRandomInt()
	{
		
		Random seed = new Random();
		return seed.nextInt(500);
	} 
	 
}
