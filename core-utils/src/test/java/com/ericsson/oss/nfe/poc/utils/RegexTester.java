package com.ericsson.oss.nfe.poc.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
		String txt="s   10.51.215.128/25   30.30.31.2      1    -G---S-um---- -          1d:2h:20m:19s\ns   70.71.15.128/25   55.66.61.2      1    -G---S-um---- -          1d:2h:20m:19s";
		 Scanner s = new Scanner(txt);
	 
 		 int tokenCnt= 0;
		 while(s.hasNext())
		 {
			 System.out.print( " <"+s.next()+"> ");tokenCnt++;
		 }
		  
		 System.out.println("TokenCount : "+tokenCnt);
		 
	     /*System.out.println(s.next());
	     System.out.println(s.next());*/
	     s.close(); 
		
	}

	private static void extractBPMNDoc() {
		String txt="     <bpmn2:scriptTask id=\\\"ScriptTask_2\\\" name=\\\"Error Gen Step\\\" scriptFormat=\\\"groovy\\\">\\n      <bpmn2:incoming>SequenceFlow_5</bpmn2:incoming>\\n      <bpmn2:outgoing>SequenceFlow_3</bpmn2:outgoing>\\n      <bpmn2:script>";

	    String re1=".*?";	// Non-greedy match on filler
	    String re2="(bpmn2:documentation)";	// Word 1
	    String re3=".*?";	// Non-greedy match on filler
	    String re4="((?:[a-z]*\\s)+)";	// Word 2
	    String re5=".*?";
	    String re6=".*?";	// Non-greedy match on filler
	    String re7="(<\\/bpmn2:documentation)";	// Word 1
	    String re8=".*?";
	    
	    String compositePattern= "(\\<bpmn2:documentation\\>)([\\w\\s]*)(\\<\\/bpmn2:documentation\\>)";
	    Pattern p = Pattern.compile(compositePattern,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(txt);
	    if (m.find())
	    {
	        String word1=m.group(1);
	        String word2=m.group(2);
	        System.out.print("("+word1.toString()+")"+"("+word2.toString()+")"+"\n");
	    }
	}

}
