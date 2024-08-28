package com.ericsson.oss.nfe.ejb.service;

import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Singleton
public class PropFileConfigurationBean  {
	
	
	static Properties props;
	
	static String enmhost;
	
	static String enmUserName;
	
	static String enmUserPassword;
	
	static  String getAlarmsURL;
	
	static  String enmLoginURL;
	
	static  String enmLogOut;
	
	static  String getAllCollectionsURL;
	
	static  String vEPG_COLLECTION_NAME;
	
	static  String ENM_FMALARM="ENMFMAlarm";
	
	@PostConstruct
	private void init()
	{
		System.out.println("PropFileConfigurationBean : init");
	
		try{
			InputStream is =  getClass().getClassLoader().getResourceAsStream("./config.properties");
			props = new Properties();
			props.load(is);
			
			if(props!=null && !props.isEmpty())
			{
				enmUserName = (props.getProperty("enm.login")!=null)?props.getProperty("enm.login"):"vignesh";
				enmUserPassword = (props.getProperty("enm.password")!=null)?props.getProperty("enm.password"):"Passw0rd";
				enmLoginURL = (props.getProperty("enm.login.url")!=null)?props.getProperty("enm.login.url"):"https://apache.vts.com/login";
				enmLogOut = (props.getProperty("enm.logout.url")!=null)?props.getProperty("enm.logout.url"):"https://apache.vts.com/logout";
				vEPG_COLLECTION_NAME = (props.getProperty("vepg.collectionName")!=null)?props.getProperty("vepg.collectionName"):"vEPG-Collection";
				getAllCollectionsURL = (props.getProperty("enm.getAllcollections")!=null)?props.getProperty("enm.getAllcollections"):"https://apache.vts.com/topologyCollections/staticCollections";
				getAlarmsURL = (props.getProperty("enm.getAlarmsURL")!=null)?props.getProperty("enm.getAlarmsURL"):"https://apache.vts.com/alarmcontroldisplayservice/alarmMonitoring/alarmoperations/getalarmlist/eventPoIdList?limit=50&recordLimit=50&filters=CLEARED&latest=true";
				
				System.out.println("----------------------~~PropFileConfigurationBean init success~~ --------------------------------------");
			}			
			
		}catch (Exception e) {e.printStackTrace();}
	}
	
	
	
	
 

}
