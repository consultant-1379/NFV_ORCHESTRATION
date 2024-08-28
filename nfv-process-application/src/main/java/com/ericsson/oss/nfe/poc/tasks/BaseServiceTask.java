package com.ericsson.oss.nfe.poc.tasks;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;

public abstract class BaseServiceTask implements JavaDelegate {

	  
	protected String buildTaskParamsLog(DelegateExecution execution,Expression[] params)
	{
		StringBuilder resultString = new StringBuilder();
		
		for(Expression paramName:params)
		{
			 if(isValidExpression(paramName,execution))
				resultString.append(paramName.getExpressionText() + " : "+paramName.getValue(execution)).append(" ");
			 
		}
		
		return resultString.toString();
	}
	
	protected String buildVariableString(DelegateExecution execution)
	{
		
		StringBuilder resultString = new StringBuilder();
		
		for(Map.Entry<String,Object> variable:execution.getVariables().entrySet())
		{
			//Igonre the appconfig and ECM Name Id Map
			if(ApplicationConstants.ECM_NAMEID_MAP.equalsIgnoreCase(variable.getKey()) || 
					"appConfigProps".equalsIgnoreCase(variable.getKey()))
			{
				continue;
			}
			else
			{
				resultString.append(variable.getKey() + " : "+variable.getValue());
			}
		}
		
		
		return resultString.toString();
		
	}
	
	private boolean isValidExpression(Expression expr,
			DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr
				.getExpressionText() != null));
	}
	 
	protected String getTORUseId(DelegateExecution execution)
	{
		return  (execution.getVariable("tor-user-id")!=null)? (String)execution.getVariable("tor-user-id") : "nfv-wfs-user";

	}
}