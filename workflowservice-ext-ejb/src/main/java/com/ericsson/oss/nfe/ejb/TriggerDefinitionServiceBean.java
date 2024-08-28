package com.ericsson.oss.nfe.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ericsson.oss.nfe.persistence.ProcessTriggerDAO;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity;

/**
 * Session Bean implementation class TriggerDefinitionServiceBean
 */
@Stateless
public class TriggerDefinitionServiceBean implements
		TriggerDefinitionServiceBeanLocal {

	@Inject
	private ProcessTriggerDAO processTriggerDAO;

	public TriggerDefinitionServiceBean() {

	}

	@Override
	public void updateTriggerDefinition(ProcessTriggerDefinitionEntity entity) {
		processTriggerDAO.updateProcessTriggerDefinition(entity);
	}

	@Override
	public void insertTriggerDefinition(ProcessTriggerDefinitionEntity entity) {
		processTriggerDAO.createProcessTriggerDefinition(entity);
	}

}
