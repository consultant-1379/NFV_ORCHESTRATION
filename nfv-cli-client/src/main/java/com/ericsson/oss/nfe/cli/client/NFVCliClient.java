/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
/**
 * CliRestClient uses Apache Commons CLI to implement a cli tool
 *  
 */
package com.ericsson.oss.nfe.cli.client;

import java.util.Arrays;

import com.ericsson.oss.nfe.cli.client.commands.MainCLIParser;

public class NFVCliClient {

	/**
	 * NFVCliClient Main class for this CLI tool
	 * @param args
	 */
	public static void main(String[] args) {

		MainCLIParser nfvcli = new MainCLIParser();
		
		if(args.length>0){  // run a one-time execution for any matched arguments
			
//			System.out.println("Args are: "+ Arrays.toString(args));
			boolean matched = false;
			for(String arg: args){
				if(MainCLIParser.argsArgNames.contains(arg)){
					
					// run corresponding command while matched
					matched = true;
					nfvcli.commandLineTrial(new String[]{"-"+arg});
				}
			}
			if(!matched){
				System.err.println("Arguments:"+ Arrays.toString(args)+" not matched any of the followings:");
				for(String arg:MainCLIParser.argsArgNames){
					System.out.println("\t"+"\""+arg+"\"");
				}
				System.out.println("Exiting NFV CLI tool now.");
			}
			return;
		}
		
		nfvcli.takeInput();   // enter in the interactive mode instead
	}

}
