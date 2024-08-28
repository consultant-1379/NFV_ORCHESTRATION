package com.ericsson.oss.nfe.wfs.extn;

import java.util.Collections;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

public class SimpleBPMNTestcase {

	 
	@Rule
	  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

	   @Test
	  @Deployment(resources = { "simple-groovy.bpmn" })
	  public void testServiceInvocationSuccessful() {

	    final RuntimeService runtimeService = processEngineRule.getRuntimeService();
	 
	    // this invocation should NOT fail
	    Map<String, Object> variables = Collections.<String, Object> singletonMap("test","testValue");

	    // start the process instance
	    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("simple_test", variables);
	    
	    Job job= processEngineRule.getManagementService().createJobQuery().singleResult();
	    
	    System.out.println("Job 1 --> "+job);
		   
	  }
	  
	  
	 
}
