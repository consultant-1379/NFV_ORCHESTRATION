package com.ericsson.oss.nfe.poc.tasks;

import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.ECMConstants;
import com.ericsson.oss.nfe.poc.utils.ECMRESTUtil;

public class GenericECMRESTInvoker extends BaseServiceTask {

	private final Logger log = LoggerFactory.getLogger("activity-tracker");
	
	private Expression requestString;

	private Expression method;
	
	private Expression endpointURL;

	private Expression outPutVariable;
	
 
	public void execute(DelegateExecution execution) {
		
		log.info("Starting GenericECMRESTInvoker task with Params : "+buildTaskParamsLog(execution, new Expression[]{endpointURL,method,requestString}));
		
		doECMRESTCall(execution);	 

		log.debug("Variables" + super.buildVariableString(execution));
		
 	} 
	
	private void doECMRESTCall(DelegateExecution execution) {
		String inputrequest = requestString.getValue(execution).toString();

		String httpMethod = method.getValue(execution).toString();
		
		
		String endpointURLStr = endpointURL.getValue(execution).toString();		

		String outputVarName = outPutVariable.getExpressionText(); 
 		
		String respvoStr= "";
		
		execution.setVariable("restCallPass", false);
		
		if(StringUtils.isEmpty(httpMethod)|| StringUtils.isEmpty(endpointURLStr))
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "URL and http method is required");		
		 
		if(ECMConstants.GET_METHOD.equalsIgnoreCase(httpMethod))
		{
			
			try {
				respvoStr = new ECMRESTUtil().doGETRequest(endpointURLStr);
				execution.setVariable("restCallPass", true);
			} catch (RESTInvokeException e) {
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Error in rest GET:"+e);
			}
			
		}
		else if(ECMConstants.POST_METHOD.equalsIgnoreCase(httpMethod))
		{
			try
			{
				respvoStr = new ECMRESTUtil().doPOSTRequest(inputrequest, endpointURLStr);
				execution.setVariable("restCallPass", true);
				
			} catch (RESTInvokeException e) {
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Error in rest POST:"+e);
			}
		}
		else if(ECMConstants.DELETE_METHOD.equalsIgnoreCase(httpMethod))
		{
			try
			{
				respvoStr = new ECMRESTUtil().doDeleteRequest(endpointURLStr);
				
			} catch (RESTInvokeException e) {
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Error in rest Delete:"+e);
			}

		}
		else
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Http method is invalid ");
		
 		execution.setVariable(outputVarName, respvoStr);
 		
 		log.info("Ending GenericECMRESTInvoker task with outputVar: "+respvoStr);
 		//System.out.println(execution.getVariable(outputVarName));
	}
}