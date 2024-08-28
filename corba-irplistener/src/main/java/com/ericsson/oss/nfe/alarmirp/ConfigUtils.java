package com.ericsson.oss.nfe.alarmirp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigUtils {

	public static ConfigUtils instance = new ConfigUtils();
	
	public static boolean isInitalised = false;

	private Properties dbProps; 

	private ConfigUtils() {

		System.out.println("---------------------called the singleton ConfigUtils-----------------------------");
		try {
			
			String configFile = System.getProperty("appconfig.properties");
			
			System.out.println(" configFile system props : "+configFile);

			configFile = (configFile != null && configFile.trim().length() > 0) ? 
						configFile.trim():null;
						
			if(configFile!=null)
			{
				System.out.println(" Resolved the application config to : "+configFile);
				dbProps = new Properties();
				dbProps.load(new FileInputStream(new File(configFile)));
			}
			else
			{
				System.out.println("cannot find system property for appconfig,falling back to jar contained propsfiles : "+configFile);

				dbProps = new Properties();
				dbProps.load(getClass().getClassLoader().getResourceAsStream("dbconfig.properties"));
			}
			isInitalised=true;
			System.out.println("dbProps built : "+dbProps);
		} catch (IOException e) {
			isInitalised=false;
			e.printStackTrace();
		}
	}

	public String getProperty(String queryKey) {
		return dbProps.getProperty(queryKey);
	}
	
	public Map<String,String> getConfigAsMap()
	{
		Map<String,String> resultMap = new HashMap<String, String>();
		for (final String name: dbProps.stringPropertyNames())
			resultMap.put(name, dbProps.getProperty(name));
		return resultMap;
	}
}
