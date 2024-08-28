package com.ericsson.oss.nfe.poc.sapc.mock.server;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

public class TestSapcCreateVDCFlow {
	
	@Rule
	public ProcessEngineRule processEngineRule = new ProcessEngineRule();
	
	@BeforeClass
	public static void setup(){
		if(!SapcMockServer.isStarted()){
			SapcMockServer.start();
		}
	}
	
	@AfterClass
	public static void shutdown(){
		if(SapcMockServer.isStarted()){
			SapcMockServer.stop();
		}
	}
	
	@Test
	@Ignore
	@Deployment(resources = { "sapc/sapc_createVDC.bpmn" })
	public void testSapcCreateVDCFlow() {
		final RuntimeService runtimeService = processEngineRule.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("sapc_createVDC", "");
		String executionId = processInstance.getId();
		System.out.println(executionId);
	}


	
}
