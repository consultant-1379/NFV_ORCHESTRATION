package com.ericsson.oss.nfe.poc.utils;

import org.apache.commons.lang.StringUtils;

public class TupplUtils {
	
	private TupplUtils(){}
	
	 private Class getTuppleType(String tuppleString)
	 {
		 if(StringUtils.isEmpty(tuppleString))
			 return null;
		 
		 if(tuppleString.contains(":"))
		 {
			 String typeP = tuppleString.substring(tuppleString.indexOf(":")+1);
			 if(typeP.equalsIgnoreCase("String"))
				 return String.class;
			 else
				 return Object.class;
		 }
			
		 else 
			 return String.class;
	 }
	 
	 private Object getTuppleValue(String tuppleString)
	 {
		 return null;
	 }
}
 
