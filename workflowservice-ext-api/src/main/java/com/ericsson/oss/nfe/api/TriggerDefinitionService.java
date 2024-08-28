package com.ericsson.oss.nfe.api;

import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity;

public interface TriggerDefinitionService {
	
	void updateTriggerDefinition(ProcessTriggerDefinitionEntity entity);

	void insertTriggerDefinition(ProcessTriggerDefinitionEntity entity);
}
