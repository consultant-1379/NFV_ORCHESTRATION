package com.ericsson.oss.nfe.persistence;

import java.util.List;

import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity.ProcessTriggerWorkflowEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerEventEntity;

public interface ProcessTriggerDAO {
	
	public ProcessTriggerDefinitionEntity createProcessTriggerDefinition(ProcessTriggerDefinitionEntity processTrigger);
	
	public List<ProcessTriggerDefinitionEntity> getAllTriggerDefinitions();
	
	public List<ProcessTriggerWorkflowEntity> getMappedWorkFlowDefinitions(String triggerName,String triggerType,String filterString);
	
	public ProcessTriggerEventEntity createProcessTriggerEventEntity(ProcessTriggerEventEntity processTriggerEvent);
	
	public List<ProcessTriggerEventEntity> getAllTriggerEvents() ;
	
	public List<ProcessTriggerEventEntity> getAllTriggerEvents(String triggerType,String filterString) ;
	
	public String deleteProcessTriggerDefinition(String definitionId);
	
	public String deleteProcessTriggerWorkflowEntity(String id);

	public ProcessTriggerDefinitionEntity modifyProcessTriggerDefinition(
			ProcessTriggerDefinitionEntity processTrigger);
	
	public ProcessTriggerDefinitionEntity updateProcessTriggerDefinition(ProcessTriggerDefinitionEntity processTrigger); 
	
}
