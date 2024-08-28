package com.ericsson.oss.nfe.poc.listeners;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;

public class AppConfigLoadListener implements ExecutionListener {

	private static final String ECM_PROPS_MAP_KEY = "ecm.props.map.";

	public void notify(DelegateExecution execution) throws Exception {

		System.out
				.println(" --------------------~~ AppConfigLoadListener ~~---------------------------");

		Map<String, String> propsMap = AppConfigLoader.einstance
				.loadAppConfig();
		execution.setVariable("appConfigProps", propsMap);
		execution.setVariable(ECM_PROPS_MAP_KEY, new HashMap<String, String>());
	}

}
