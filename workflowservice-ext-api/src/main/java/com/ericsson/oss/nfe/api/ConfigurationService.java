package com.ericsson.oss.nfe.api;

import java.util.Map;

public interface ConfigurationService {

	// config.properties
	public String getConfigByKey(String key);
	
	public String getCamundaBaseURL();
	
	public String getCamundaUser();
	
	public String getCamundaPassword();
	
	public String getUserType(String user);	

	// user.properties
	public void reloadUserProps();
	
	public Map<String, String> getAllUserPropsMap();
	
	// appconfig.properties
	public void reloadAppProps();
	
	public String addToAppconfigFile(String name, String value, String description);
	
	public Map<String, String> getAllAppPropsMap();
	
}
