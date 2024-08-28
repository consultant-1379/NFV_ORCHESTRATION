package com.ericsson.oss.nfe.cli.client.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.ericsson.oss.nfe.cli.util.NFVCommandLineHelper;

/**
 * MainCLIParser  
 * Use Apache Commons CLI library to map the cli to
 * NFV rest service.
 *
 */
public class MainCLIParser {

	private static CommandLineParser parser = new PosixParser();
	private static Options options = new Options();
	
	// Standardrize the option names

	public static String helpOptionName = "help";
	public static String listDefinitionOptionName = "listdef";
	public static String listActiveInstanceOptionName = "list";
	public static String showProgressOptionName = "showprogress";
	public static String startInstanceOptionName = "start";
	public static String showDanglingOptionName = "showdangling";
	public static String deleteInstanceOptionName = "deletepi";
	public static String deleteAllDanglingOptionName = "deldanglings";
	
	/*
	 * Initialize Option Set while taking arguments from below format: java -jar
	 * XX-with-dependency.jar "[args...]"
	 * FYI. Only no-extra option commands are added in the set.
	 */
	public static Set<String> argsArgNames = new HashSet<>( 
		Arrays.asList(listDefinitionOptionName,listActiveInstanceOptionName, showDanglingOptionName, deleteAllDanglingOptionName)
	);

	/** 
	 * Base Constructor for creating a basic NFVCLITool
	 */
	public MainCLIParser() {
		// help - help option
		Option help = new Option(helpOptionName, "Print help message");

		// listdef - list definition option
		Option listDefinition = new Option(listDefinitionOptionName, "List all the workflow Definition names");

		// list - list active instance option
		Option listActiveInstance = new Option(listActiveInstanceOptionName,
				"List all the active instance");
		
		// show progress option
		Option showprogress = OptionBuilder
				.withArgName("defName")
				.hasArg()
				.withDescription("Display all workflow instances progresses by given workflow definition name")
				.create(showProgressOptionName);
		
		// start a process instances by given defName
		Option startInstance = OptionBuilder.withArgName("definition name, path").hasArgs(2)
				.withDescription("Start an instance with defName and userInput path").withLongOpt("create")
				.create(startInstanceOptionName);
		
		// show tangling instances
		Option showdangling = new Option(showDanglingOptionName,
				"List all the dangling instances, along with their definiton name, start time, incident time.");
				
		// delete instances
		Option deletepi = OptionBuilder.withArgName("pid").hasArg()
				.withDescription("Delete workflow instance by given id")
				.create(deleteInstanceOptionName);
		
		// delete all danglings
		
		Option delAllTangling = new Option(deleteAllDanglingOptionName, "Delete all dangling instances, print deletion report.");
		
		options.addOption(help).addOption(listDefinition).addOption(listActiveInstance)
				.addOption(showprogress).addOption(startInstance)
				.addOption(showdangling)
				.addOption(deletepi)
				.addOption(delAllTangling);

	}
	/**
	 * Print a Welcome Banner to use the CLI tool.
	 */
	void printoutWelcomeMessage(){
		System.out.println("Start using cli tool by typing 'cli -help'");
		System.out.println("Type 'cli -help' for more");
	}
	/**
	 * Input Parser for NFVCLITool to accept 'cli [option]' commands
	 */
	public void takeInput() {
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		String str;
		ArrayList<String> args = new ArrayList<String>();
		
		printoutWelcomeMessage();
		
		try {
			while ((str = input.readLine()) != null) {
				args.clear();
				if (str.indexOf("cli")==0) {
					
					for(int i=3; i<str.length();i++){
						int indexofarg = i;
						if(str.charAt(i)=='"'){
							i++;
							while(i<str.length()){
								if(str.charAt(i)=='"')
									break;
								i++;
							}
							if(i<str.length()+1)
								args.add(str.substring(indexofarg+1, i));
						}
						else if(str.charAt(i)!=' '){
							i++;
							while(i<str.length()){
								if(str.charAt(i)==' ')
									break;
								i++;
							}
							if(i<str.length()+1)
								args.add(str.substring(indexofarg, i));
						}
					}
					if(args.size()==0){
						System.out.println("'" + str + "' command not recognized");
						System.out.println("Type 'cli -help' for more");
					}
					commandLineTrial(args.toArray(new String[args.size()]));
				} 
				else if(str.trim().length()==0){
				}
				else {
					 
					System.out.println("'" + str + "' command not recognized");
					System.out.println("Type 'cli -help' for more");
						 
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Main parser for commands
	 * 
	 * @param args
	 */
	public void commandLineTrial(String args[]) {
		try {
			CommandLine line = parser.parse(options, args);
			if (line.hasOption(startInstanceOptionName)) {
				// startProcessInstance method
				String[] startArgs = line.getOptionValues(startInstanceOptionName);
				if(startArgs==null || startArgs.length<2)
					throw new ParseException("missing arguments for -start");
				String defName = startArgs[0];
				String userinputPath = startArgs[1];
				
				System.out.println("Starting instance using following settings:");
				System.out.println("\tDefName:'"+defName +"', userinput path: '"+userinputPath+"'");
				
				NFVCommandLineHelper.startInstanceFromDefinition(defName, userinputPath);
			}
			if (line.hasOption(helpOptionName)) {
				// print the help message
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("cli", options);

			}
			if (line.hasOption(listDefinitionOptionName)) {
				// listDefinition method
				NFVCommandLineHelper.getAlldefList();
			}
			if (line.hasOption(listActiveInstanceOptionName)) {
				NFVCommandLineHelper.getActiveInstanceList();

			}
			if (line.hasOption(showProgressOptionName)) {
				String defname = line.getOptionValue(showProgressOptionName);
				NFVCommandLineHelper.getProgress(defname);
			}

			if(line.hasOption(showDanglingOptionName)){
				NFVCommandLineHelper.getIncidentedPI();
			}
			if(line.hasOption(deleteInstanceOptionName)){
				String[] iIDs = line.getOptionValues(deleteInstanceOptionName);
				NFVCommandLineHelper.deleteInstance(iIDs);
			}
			if(line.hasOption(deleteAllDanglingOptionName)){
				NFVCommandLineHelper.deleteAllTanglings();
			}

		} catch (ParseException e) {

			System.err.println("ParseException:" + e.getMessage());
		}
	}
	

}
