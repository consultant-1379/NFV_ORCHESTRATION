package com.ericsson.oss.nfe.api;

import java.util.List;

import com.ericsson.oss.nfe.persistence.entity.ExtendedProcessDefinition;
import com.ericsson.oss.nfe.persistence.entity.ExtendedProcessInstance;
import com.ericsson.oss.nfe.persistence.entity.ProcessDeploymentEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerEventEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity.ProcessTriggerWorkflowEntity;


public interface ProcessInstanceProgressService {
	
	List<ExtendedProcessInstance> getProcessInstanceForDefinitionId(String processDefnID, String order);
	
	List<ProcessDeploymentEntity> getAllProcessDeployments();
	
	public List<ProcessTriggerDefinitionEntity> getAllTriggerDefinitions();
	public ProcessTriggerDefinitionEntity setProcessTriggerDefinitionEntity(ProcessTriggerDefinitionEntity identity);
	
	public List<ProcessTriggerWorkflowEntity> getMappedWorkFlowDefinitions(String triggerType,String filterString);
	
	public List<ExtendedProcessDefinition> getProcessDefinitions(String sortOrder, String firstResult, String maxResults, String userType);
	
	public List<ProcessTriggerEventEntity> getAllTriggerEvents();
	
	public List<ProcessTriggerEventEntity> getAllTriggerEvents(String triggerType,String filterString) ;
	
	public String deleteProcessTriggerDefinition(String definitionId);
	
	public String deleteProcessTriggerWorkflowEntity(String id);
	
	public List<ExtendedProcessInstance> getProcessAncestry(String processInstanceId);
	
	public ExtendedProcessInstance getInstanceStatus(String processInstanceId);
}
