package com.ericsson.oss.nfe.poc.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AppConfigLoader {

	private static final String ECM_PROPS_MAP_KEY = "ecm.props.map.";

	private static boolean isInit = false;

	public static AppConfigLoader einstance = new AppConfigLoader();

	private static Properties props;

	private static final String ECM_DOMAIN_KEY = "ECM_DOMAIN";

	private AppConfigLoader() {
		props = new Properties();
		initAppConfig();
	}

	private void initAppConfig() {

		synchronized (AppConfigLoader.class) {
			if (!isInit) {

				try {
					InputStream is = AppConfigLoader.class.getClassLoader().getResourceAsStream("./appconfig-default.properties");
					props.load(is);
					System.out.println("Set props to " + props);
				} catch (Exception e) {
					System.err.println("Error loading default app config props file !!");
					e.printStackTrace();
				}

				loadAppConfiguration();
				isInit = true;
			}
		}
	}

	private void updatePropsForECM() {

		String ecm_domain = getProperty(ECM_DOMAIN_KEY);

		System.out.println("ECM DOMAIN is --------------------- " + ecm_domain);
		
		String toReplace = "%" + ECM_DOMAIN_KEY + "%";

		Map<String, String> propsMap = new HashMap<String, String>();

		System.out.println("Updating for ECM Domain...");
		
		for (final String name : props.stringPropertyNames()) {
			if (props.getProperty(name).contains(toReplace)) {
				String newPropValue = props.getProperty(name).replace(toReplace, ecm_domain);
				propsMap.put(name, newPropValue);
				//System.out.println(name + "=" + newPropValue);
			}
			
		}
		
		//props.clear();
		props.putAll(propsMap);
		
		System.out.println(" propos(UPDATED) now " + props);
	}

	public void loadAppConfiguration() {
		try {
			String env = getAppConfigDir();
			if ("".equalsIgnoreCase(env)) {
				System.out.println(" Didn't find APPCONFIGDIR  in " + System.getProperties());
			} else {
				File appfile = new File(env + "/appconfig.properties");
				System.out.println(" APPCONFIGFile is " + appfile.getAbsolutePath());
				if (appfile.exists()) {

					InputStream is = new FileInputStream(appfile);
					//props.clear();
					props.load(is);
					System.out.println(" properties added from  " + appfile.getAbsolutePath() + " propos now " + props);
				}
			}

		} catch (Exception e) {
			System.err.println("Error loading app config props file !! Application might not work as expected");
			e.printStackTrace();
		}
		updatePropsForECM();
	}

	public Map<String, String> loadAppConfig() {

		Map<String, String> propsMap = new HashMap<String, String>();
		try {

			if (!isInit) {
				InputStream is = AppConfigLoader.class.getClassLoader().getResourceAsStream("./appconfig-default.properties");
				props.load(is);
				System.out.println("App config successfully load ---> " + props);
			}

			System.out.println("App config Loading props map ");

			for (Object key : props.keySet()) {
				String strKey = (String) key;
				// if(!strKey.startsWith(ECM_PROPS_MAP_KEY))
				// {
				propsMap.put(strKey, props.getProperty(key.toString(), ""));

				// }

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return propsMap;
	}

	public static String getProperty(String key) {
		return props.getProperty(key, "");
	}

	public static Map<String, String> getECMPropsMap() {
		Map<String, String> propsMap = new HashMap<String, String>();

		for (Object key : props.keySet()) {
			String strKey = (String) key;
			if (strKey.startsWith(ECM_PROPS_MAP_KEY)) {
				String chippedKey = strKey.substring(ECM_PROPS_MAP_KEY.length());
				propsMap.put(chippedKey, props.getProperty(key.toString(), ""));

			}

		}
		return propsMap;
	}

	public static Map<String, String> getAllPropsMap() {
		Map<String, String> propsMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			propsMap.put(key.toString(), props.getProperty(key.toString(), ""));

		}
		return propsMap;
	}

	public static String getAppConfigDir() {
		return System.getProperty("APPCONFIGDIR", "");
	}

}
