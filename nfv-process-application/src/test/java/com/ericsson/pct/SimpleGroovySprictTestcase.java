package com.ericsson.pct;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

import com.ericsson.oss.nfe.poc.utils.FileUtils;

public class SimpleGroovySprictTestcase {

	@Rule
	public ProcessEngineRule processEngineRule = new ProcessEngineRule();
	
	private static final String template = FileUtils.loadFileAsString("x670.tmpl");

	@Test
	@Deployment(resources = { "simple-groovy.bpmn" })
	public void testServiceInvocationSuccessful() {

		final RuntimeService runtimeService = processEngineRule
				.getRuntimeService();

		
		//System.out.println("template : "+template);
		// this invocation should NOT fail
		Map<String, Object> variables = new HashMap<String, Object>();
		
		variables.put("ipAddressRange", "w5p1vepg1 (10.70.125.25/23)");

		variables.put("templateString", template);
		// start the process instance
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("simple-groovy", variables);

		System.out.println("done ?? --> " + processInstance.isEnded());

	}

}
