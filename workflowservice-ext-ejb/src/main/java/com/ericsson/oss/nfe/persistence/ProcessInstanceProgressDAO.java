package com.ericsson.oss.nfe.persistence;

import java.util.List;

import com.ericsson.oss.nfe.persistence.entity.ExtendedProcessInstance;
import com.ericsson.oss.nfe.persistence.entity.ProcessDeploymentEntity;

public interface ProcessInstanceProgressDAO {

	public List<ExtendedProcessInstance> getProcessInstancesProgressMap(List<ExtendedProcessInstance> instancesList);
	
	public List<ProcessDeploymentEntity> getAllProcessDeployments();

}
