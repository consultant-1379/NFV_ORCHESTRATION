package com.ericsson.oss.nfe.poc.listeners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class ActivityEntryExitListener implements ExecutionListener {

	private final Logger Logger = LoggerFactory.getLogger("activity-tracker");

	
	public void notify(DelegateExecution execution) throws Exception {
		
		String user = (execution.getVariable("tor-user-id")!=null)? (String)execution.getVariable("tor-user-id") : "nfv-wfs-user";
		
		MDC.put("tor-user",user);
		
		if("start".equalsIgnoreCase(execution.getEventName()))
		{
			Logger.info("------------------- Starting Proccess ------> "+getProcessDefinitionName(execution.getProcessDefinitionId()) + " process Id : "+execution.getProcessInstanceId());

		}
		else if("end".equalsIgnoreCase(execution.getEventName()))
		{
			Logger.info("------------------- Ending Proccess ------> "+ getProcessDefinitionName(execution.getProcessDefinitionId())+ " process Id : "+execution.getProcessInstanceId());

		} 
 
 	}
	
	
	private String getProcessDefinitionName(String processDefinitionId)
	{
		String defnName = "";
		if(processDefinitionId!=null && processDefinitionId.contains(":"))
			defnName =  processDefinitionId.substring(0,processDefinitionId.indexOf(":"));
		else
			defnName = processDefinitionId;			 
		
		return defnName;
	}

}
