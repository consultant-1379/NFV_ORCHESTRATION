package com.ericsson.oss.nfe.alarmirp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.alarmirp.ConfigUtils;
import com.ericsson.oss.nfe.alarmirp.DBUtils;
import com.ericsson.oss.nfe.alarmirp.corba.BasePOA;
import com.ericsson.oss.nfe.alarmirp.corba.InitR99;
import com.ericsson.oss.nfe.alarmirp.corba.ORBUtil;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity;

public class CorbaIrpListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private CorbaIrpListener() {
	}
	
	public static void main(String[] args) {
		
		new CorbaIrpListener().init();
		
	}

	private void init() {
		
		logger.info("init method started ...");
		
		DBUtils.eInstance();
		
		logger.debug("dbUitls instantiated successfully");
		
		List<ProcessTriggerDefinitionEntity> registeredTriggers = getRegisteredAlarmsFilters();

		logger.debug("registeredTriggers :"+registeredTriggers);
		
 
		ORBUtil orbUtil = new ORBUtil();
		orbUtil.initORB();		
		
		logger.debug("ORBUtil#initORB() successful ");
		
		for(ProcessTriggerDefinitionEntity trigger:registeredTriggers){
		
			attachListener(trigger.getFilterString(),orbUtil);
			logger.debug("attachListener for filter {} successful ! ",trigger.getFilterString());
		}
		
		//String filterString = ConfigUtils.instance.getProperty("filterString");
		//attachListener("'SubNetwork=ONRM_RootMo' ~ $f",orbUtil);		
		
		
		orbUtil.getOrb().run();
		
		logger.debug("ORBUtil#run() successful ");
		
		logger.info("init method ended successfully ...");
		
		
	}

	
	private void attachListener(String filter,ORBUtil orbUtil)
	{
		BasePOA poalistener = new InitR99(filter,orbUtil);
		poalistener.attach(ConfigUtils.instance.getProperty("notifciationendpoint"));
	}
	
	
	
	// TODO Convert to use Connection pooling and also JPA
	private List<ProcessTriggerDefinitionEntity> getRegisteredAlarmsFilters() {
		
		DBUtils dbUitls = DBUtils.eInstance();
		Connection dbConnection = null;

		PreparedStatement preparedStatement = null;

		List<ProcessTriggerDefinitionEntity> triggerDefns = new ArrayList<>();

		try {
			dbConnection = dbUitls.getDBConnection();
			
			logger.debug("dbConnection opened successfully");
			
			preparedStatement = dbConnection.prepareStatement(dbUitls
					.getQuery("getregistered.triggers.sql"));
			
			preparedStatement.setString(1, "FMAlarm");

			ResultSet rs = preparedStatement.executeQuery();

			logger.debug("rs obtained !!");

			while (rs.next()) {

				String triggerName = rs.getString("triggername");
				String triggerFilter = rs.getString("filterstring");

				ProcessTriggerDefinitionEntity definition = new ProcessTriggerDefinitionEntity();
				definition.setTriggerName(triggerName);
				definition.setFilterString(triggerFilter);
				triggerDefns.add(definition);
			}

		} catch (SQLException e) {

			logger.error("SQLException in querrying",e);
			System.out.println(e.getMessage());

		} finally {
			logger.debug("Finally releasing the connection");
			dbUitls.releaseResources(dbConnection);

		}

		logger.debug("getRegisteredAlarmsFilters completed with :"+triggerDefns.size());

		return triggerDefns;
	}

}
