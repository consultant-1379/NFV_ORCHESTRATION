/**
 * CliConfigLoader
 * @author ejosarm
 * @version 1.0
 * 
 */

package com.ericsson.oss.nfe.cli.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class NFVConfigLoader {

	private static boolean isInit =false;

	private static Properties props = new Properties();
 
	private static void loadCliConfig() {

		if(!isInit)
		{
			System.out.println("================================================================");
			try { 					
				
				String env = getCliConfigDir();
				if ("".equalsIgnoreCase(env))   // load in default one from the classpath.
				{
					System.out.println("Loading default property file...");
					InputStream is = NFVConfigLoader.class.getResourceAsStream("/cliconfig.properties");			
					props.load(is);
				}
				else
				{
					System.out.println("Loading property file from \""+env+"\"");
					File clifile = new File(env+"/cliconfig.properties");
					System.out.println("CLICONFIGFile is " + clifile.getAbsolutePath());
					if (clifile.exists())
					{
					
						InputStream is2 = new FileInputStream(clifile);			
						props.load(is2);	
						System.out.println("Properties added from  " + clifile.getAbsolutePath());
					}
					else{
						throw new RuntimeException("No file is found at: "+clifile.getPath());
					}
				}
				List<String> sortedPropNames = new ArrayList<String>(props.stringPropertyNames());
				Collections.sort(sortedPropNames);
				
				for(String propName: sortedPropNames){
					System.out.println("\t"+propName + "=\""+ props.getProperty((String)propName)+"\"");
				}
 	
			} catch (Exception e) {
				e.printStackTrace();
			}
			isInit =true;
			
			System.out.println("================================================================");
		}
	}
	public static String getProperty(String key)
	{
		if(!isInit){loadCliConfig();}
		return props.getProperty(key, "");
	}
	
	public static String getCliConfigDir() {
		String property = System.getProperty("CLICONFIGDIR", "");

		if (property.equals("")) {
			property = (System.getenv("CLICONFIGDIR") == null) ? "" : System
					.getenv("CLICONFIGDIR");
		}
		return property;
	}
}
