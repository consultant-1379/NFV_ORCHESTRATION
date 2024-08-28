package com.ericsson.oss.nfe.poc.tasks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowElement;

public class MockExecutionEnvironment implements DelegateExecution {
	
	private static Map<String, Object> executionVariables;
	
	static{
		executionVariables = new HashMap<String,Object>();
		executionVariables.put("VDCName", "MME-VDC");
	}

	@Override
	public Map<String, Object> getVariables() {
		return executionVariables;
	}

	@Override
	public Map<String, Object> getVariablesLocal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getVariable(String variableName) {
		return executionVariables.get(variableName);
	}

	@Override
	public Object getVariableLocal(String variableName) {
		return executionVariables.get(variableName);
	}

	@Override
	public Set<String> getVariableNames() {
		return executionVariables.keySet();
	}

	@Override
	public Set<String> getVariableNamesLocal() {
		return executionVariables.keySet();
	}

	@Override
	public void setVariable(String variableName, Object value) {
		executionVariables.put(variableName, value);
		
	}

	@Override
	public void setVariableLocal(String variableName, Object value) {
		executionVariables.put(variableName, value);
	}

	@Override
	public void setVariables(Map<String, ? extends Object> variables) {
	}

	@Override
	public void setVariablesLocal(Map<String, ? extends Object> variables) {
		
	}

	@Override
	public boolean hasVariables() {
		return false;
	}

	@Override
	public boolean hasVariablesLocal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasVariable(String variableName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasVariableLocal(String variableName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeVariable(String variableName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVariableLocal(String variableName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVariables(Collection<String> variableNames) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVariablesLocal(Collection<String> variableNames) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVariables() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVariablesLocal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BpmnModelInstance getBpmnModelInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlowElement getBpmnModelElementInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessEngineServices getProcessEngineServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessInstanceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessDefinitionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParentId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentActivityId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentActivityName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getActivityInstanceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParentActivityInstanceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentTransitionId() {
		// TODO Auto-generated method stub
		return null;
	}

}
