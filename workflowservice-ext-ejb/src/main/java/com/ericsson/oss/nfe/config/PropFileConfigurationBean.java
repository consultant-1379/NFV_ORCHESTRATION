package com.ericsson.oss.nfe.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.ericsson.oss.nfe.api.ConfigurationService;
import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.FileUtils;
import com.ericsson.oss.nfe.poc.utils.RESTUtil;
import com.ericsson.oss.nfe.poc.utils.vo.HeaderTupple;

@Remote(value = ConfigurationService.class)
@Singleton
@Startup
public class PropFileConfigurationBean implements ConfigurationService, ConfigurationServiceLocal {

	private static Properties props;

	private static Properties userProps;

	@PostConstruct
	private void init() {
		System.out.println("PropFileConfigurationBean : init");

		try {
			// config.properties
			InputStream is = getClass().getClassLoader().getResourceAsStream("./config.properties");
			props = new Properties();
			props.load(is);

			// user.properties
			userProps = new Properties();
			is = getClass().getClassLoader().getResourceAsStream("./default-user.properties");
			userProps.load(is);

			reloadUserProps();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getConfigByKey(String key) {
		return props.getProperty(key);
	}

	@Override
	public String getCamundaBaseURL() {
		return props.getProperty("camunda.baseurl");
	}

	@Override
	public String getCamundaUser() {
		return props.getProperty("camunda.username");
	}

	@Override
	public String getCamundaPassword() {
		return props.getProperty("camunda.pasword");
	}

	/**
	 * user configuration properties
	 */

	@Override
	public String getUserType(String user) {
		return userProps.getProperty(user);
	}

	@Override
	public void reloadUserProps() {
		InputStream is = FileUtils.loadFileFromAppConfig("user.properties");
		if (is != null) {
			try {
				userProps.clear();
				userProps.load(is);
				System.out.println("New user-props loaded: " + userProps);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Map<String, String> getAllUserPropsMap() {
		Map<String, String> propsMap = new HashMap<String, String>();
		for (Object key : userProps.keySet()) {
			propsMap.put(key.toString(), userProps.getProperty(key.toString(), ""));

		}
		return propsMap;
	}

	/**
	 * application configuration properties
	 */

	@Override
	public void reloadAppProps() {
		AppConfigLoader.einstance.loadAppConfiguration();
	}

	@Override
	public Map<String, String> getAllAppPropsMap() {
		return AppConfigLoader.einstance.getAllPropsMap();
	}

	@Override
	public String addToAppconfigFile(String name, String value, String description) {
		
		try {
			
			String jsonReqStr = "{\"name\": \"" + name + "\", \"value\": \"" + value + " \" , \"description\": \"" + description + "\"}";
			
			String nfv_admin_addprop = AppConfigLoader.einstance.getProperty("NFV_ADMIN_ADDAPPPROP");// "http://localhost:8080/nfv-admin/rest/appconfig/addprop";
			
			if (nfv_admin_addprop == null || nfv_admin_addprop == "") {
			
				nfv_admin_addprop = "http://localhost:8081/nfv-admin/rest/appconfig/addprop";
			}
			
			RESTUtil restRequest = new RESTUtil();

			return restRequest.doPOSTRequest(jsonReqStr, nfv_admin_addprop, new ArrayList<HeaderTupple>());
			
		} catch (RESTInvokeException ignore) {
		}
		return null;
	}

}
