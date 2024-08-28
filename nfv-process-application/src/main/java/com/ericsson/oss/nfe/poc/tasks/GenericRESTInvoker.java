package com.ericsson.oss.nfe.poc.tasks;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.ECMConstants;
import com.ericsson.oss.nfe.poc.utils.RESTUtil;
import com.ericsson.oss.nfe.poc.utils.vo.HeaderTupple;

public class GenericRESTInvoker extends BaseServiceTask {

	private final Logger log = LoggerFactory.getLogger("activity-tracker");
	
	private Expression requestString;

	private Expression method;
	
	private Expression endpointURL;

	private Expression outPutVariable;
	
 
	public void execute(DelegateExecution execution) {
		
		log.info("Starting GenericRESTInvoker task with Params : "+buildTaskParamsLog(execution, new Expression[]{endpointURL,method,requestString}));
		
		doRESTCall(execution);	 

		log.debug("Variables" + super.buildVariableString(execution));
		
 	} 
	
	private void doRESTCall(DelegateExecution execution) {
		 

		String httpMethod = method.getValue(execution).toString();		
		
		String endpointURLStr = endpointURL.getValue(execution).toString();		
		
		if(StringUtils.isEmpty(httpMethod)|| StringUtils.isEmpty(endpointURLStr))
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "URL and http method is required");
		
		String inputrequest = isValidExpression(requestString, execution)?requestString.getValue(execution).toString():"";		

		String outputVarName = outPutVariable.getExpressionText(); 
 		
		String respvoStr= "";
		
		execution.setVariable("restCallPass", false);
		
				
		 
		if(ECMConstants.GET_METHOD.equalsIgnoreCase(httpMethod))
		{
			
			try {
				respvoStr = new RESTUtil("noAuth").doGETRequest(endpointURLStr,new ArrayList<HeaderTupple>());
				execution.setVariable("restCallPass", true);
			} catch (RESTInvokeException e) {
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Error in rest GET:"+e);
			}
			
		}
		else if(ECMConstants.POST_METHOD.equalsIgnoreCase(httpMethod))
		{
			try
			{
				respvoStr = new RESTUtil("noAuth").doPOSTRequest(inputrequest, endpointURLStr,new ArrayList<HeaderTupple>());
				execution.setVariable("restCallPass", true);
				
			} catch (RESTInvokeException e) {
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Error in rest POST:"+e);
			}
		}
		else if(ECMConstants.DELETE_METHOD.equalsIgnoreCase(httpMethod))
		{
			try
			{
				respvoStr = new RESTUtil("noAuth").doDeleteRequest(endpointURLStr,new ArrayList<HeaderTupple>());
				
			} catch (RESTInvokeException e) {
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Error in rest Delete:"+e);
			}

		}
		else
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Http method is invalid ");
		
 		execution.setVariable(outputVarName, respvoStr);
 		
 		log.info("Ending GenericRESTInvoker task with outputVar: ");
 	}
	
	private boolean isValidExpression(Expression expr,
			DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr
				.getExpressionText() != null));
	}
}