package com.ericsson.oss.nfe.poc.tasks;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;

public class TestPrepareMMEConsoleConnectionFlow {
	
	private static final String MME_VM_ID = "MME-NC-1.15-VM";
	private static final String QUERY_VM_ID = "QUERY_VM_BY_ID";
	private static final String QUERY_EXTENSION = "?$expand=vmvnics";
	private static final String ECM_MAP = "ECM_NAMEID_MAP";

	@Rule
	public ProcessEngineRule processEngineRule = new ProcessEngineRule();
	
	@BeforeClass
	public static void setup(){
		if(!MockServer.isStarted()){
			MockServer.start();
		}
	}
	
	@AfterClass
	public static void shutdown(){
		if(MockServer.isStarted()){
			MockServer.stop();
		}
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void ecmNameIdMapShouldContainMMENCWhenQuerryAndPopulateOrderItems() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		DelegateExecution execution = getMockDeletegateExecution();
		QuerryAndPopulateOrderItems queryAndPopulateOrderItems = new QuerryAndPopulateOrderItems();
		queryAndPopulateOrderItems.execute(execution);
		Object vmMapObject = execution.getVariable(ECM_MAP);
		Map<String,Object> vmMap = (HashMap<String,Object>)(vmMapObject);
		assertTrue(vmMap.containsKey(MME_VM_ID));
		String vmId = (String)vmMap.get(MME_VM_ID);
		assertNotNull(vmId);
		String vnUrl = AppConfigLoader.getProperty(QUERY_VM_ID) + vmId + QUERY_EXTENSION;
		execution.setVariable("getVNURL", vnUrl);
	}
	
	@Ignore
	@Test
	@Deployment(resources = { "mme/mme_prepareconsoleconection.bpmn" })
	public void testECMRestCall() {
		final RuntimeService runtimeService = processEngineRule.getRuntimeService();
		Map<String, Object> variables = Collections.<String, Object> singletonMap("instanceType","MME");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("mme_prepareconsoleconnection", variables);
		String executionId = processInstance.getId();
		String consoleUrl = (String)runtimeService.getVariable(executionId, "URL");
		System.out.println("retrieved Console URL : " + consoleUrl);
		assertNotNull(consoleUrl);
		String formContent = (String)runtimeService.getVariable(executionId, "formContent");
		assertTrue(formContent.startsWith("<a href=http"));
	}


	private DelegateExecution getMockDeletegateExecution() {
		return new MockExecutionEnvironment();
	}
	
}
