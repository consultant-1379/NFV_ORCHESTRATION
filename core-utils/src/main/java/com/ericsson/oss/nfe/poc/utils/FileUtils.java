package com.ericsson.oss.nfe.poc.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;

public class FileUtils {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static InputStream loadFile(String directory, String filename)
			throws IOException {

		logger.debug("directory : " + directory + " FileName : "
				+ filename);
		File file = new File(directory + "/" + filename);

		try {
 			return new FileInputStream(file);

		} catch (Exception e) {
			throw new IOException("FILE_READ_FAIELD",e);
		}

	}

	public static String loadFileAsString(String fileName) {
		BufferedReader reader = null;

		StringBuilder stringBuilder = new StringBuilder();
		try {
			 
			reader = new BufferedReader(new InputStreamReader(
					FileUtils.class.getClassLoader().getResourceAsStream(fileName)));
			String line = null;

			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
		} catch (Exception e) {

		}
		finally
		{
			closeFailSafe(reader);
		}

		return stringBuilder.toString();
	}
	
	public static InputStream loadFileFromAppConfig(String fileName)
	{
		
		String appconfiDir = AppConfigLoader.getAppConfigDir();
		InputStream is = null;
		if(!StringUtils.isEmpty(appconfiDir))
		{
			try {
				
				is = new FileInputStream(new File(appconfiDir+"/"+fileName));
				
			} catch (FileNotFoundException e) {
				logger.debug("File not found !! directory : " + appconfiDir + " FileName : "+fileName);
			}
		}  
		return is;
	}
	
	public static String loadOvfAsString(String fileName) {
		return loadStreamAsString(loadFileFromAppConfig("ovfs/"+fileName));
	}
	
	public static String loadValueFromAppConfig(String fileName, String variableName)
	{
		
		String appconfiDir = AppConfigLoader.getAppConfigDir();
		InputStream is = null;
		Properties properties = null;
		if(!StringUtils.isEmpty(appconfiDir))
		{
			try {
				
				if(!new File(appconfiDir+"/"+fileName).exists())
					return null;
				
				is = new FileInputStream(new File(appconfiDir+"/"+fileName));				
				properties = new Properties();
				properties.load(is);
			} catch (FileNotFoundException e) {
				logger.debug("File not found !! directory : " + appconfiDir + " FileName : "+fileName);
			} catch (IOException e) {
				logger.debug("property could not be loaded !! directory : " + appconfiDir + " FileName : "+fileName);
			}
			finally{
				closeFailSafe(is);
			}
		}  
		
		if(properties!=null){
			Object value = properties.get(variableName);
			if(value == null){
				return null;
			}
			if(!(value instanceof String)){
				return null;
			}
			return (String)value;
		}
		
		else 
			return null;
	}
	
	/**
	 * This method is add A K-V pair to any props file.
	 * 
	 * @param fileName Props file name in AppConfigDir
	 * @param key Key of the property
	 * @param value
	 * @return result as true for success and false for failure.
	 */
	
	public static boolean addKeyValuetoAppConfig(String fileName, String key,String value)
	{
		boolean result = false;
		
		String appconfiDir = AppConfigLoader.getAppConfigDir();
		InputStream is = null;
		Properties properties = null;
		if(!StringUtils.isEmpty(appconfiDir))
		{
			try {
				
				is = new FileInputStream(new File(appconfiDir+"/"+fileName));
				properties = new Properties();
				properties.load(is);
				properties.put(key, value);
				
				properties.store(new FileOutputStream(new File(appconfiDir+"/"+fileName)), "Saving Mapping");
				result = true;
				
			} catch (FileNotFoundException e) {
				result = false;
				logger.debug("File not found !! directory : " + appconfiDir + " FileName : "+fileName);
			} catch (IOException e) {
				result = false;
				logger.debug("property could not be loaded !! directory : " + appconfiDir + " FileName : "+fileName);
			}
			finally{
				closeFailSafe(is);
			}
		}		
		return result;		 
	}	
	
	public static InputStream loadFileFromWorkDir(String fileName)
	{
		
		String appconfiDir = AppConfigLoader.getAppConfigDir();
		InputStream is = null;
		if(!StringUtils.isEmpty(appconfiDir))
		{
			try {
				
				is = new FileInputStream(new File(appconfiDir+"/"+fileName));
				
			} catch (FileNotFoundException e) {
				logger.error("File not found !! directory : " + appconfiDir + " FileName : "+fileName);
			}
		}  
		return is;
	}
	
	
	public static String createWorkSubDir(String dirName)
	{
		
		String appconfiDir = AppConfigLoader.getAppConfigDir();
		boolean success = false;
		File directory = null;
		if(!StringUtils.isEmpty(appconfiDir))
		{
			try {
				
				directory = new File(appconfiDir+"/workspace/"+dirName);
				if (!directory.exists()) 
					success = directory.mkdir();
				
			} catch (Exception e) {
				logger.debug(" Creating  directory : " + dirName + " failed : "+e);
			}
		}  
		if(directory!=null)
			return directory.getPath();
		else 
			return null;
	}
	
	public static boolean writeFileToDir(String fileName,String fileContents)
	{
		FileWriter fw = null;
		try {
			 fw = new FileWriter(fileName);
			fw.append(fileContents);
			
		} catch (Exception e) {		
			logger.error(" writeFileToDir  : " + fileName + " failed : "+e);
			return false;
		}finally{closeFailSafe(fw);}
		
		return true;
	}

	public static boolean cleanUp(String dirName)
	{
		
		String appconfiDir = AppConfigLoader.getAppConfigDir();
		boolean success = false;
		 
		if(!StringUtils.isEmpty(appconfiDir))
		{
			try {
				
				File directory = new File(appconfiDir+"/workspace/"+dirName);
				if (!directory.exists()) 
					success = directory.mkdir();
				
			} catch (Exception e) {
				logger.debug(" Cleanup  directory : " + dirName + " failed : "+e);
			}
		}  
		return success;
	}
	 	
	
	public static String loadStreamAsString(InputStream is) {
		BufferedReader reader = null;

		StringBuilder stringBuilder = new StringBuilder();
		try {
			 
			reader = new BufferedReader(new InputStreamReader(is));
			String line = null;

			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
		} catch (Exception e) {

		}

		return stringBuilder.toString();
	}
	
	
	public static void closeFailSafe(Closeable closable)
	{
		try {
			if(closable!=null)
				closable.close();
			} catch (IOException ignore) {
				logger.warn("Failsafe close of closable failed");
			}
	}

}
