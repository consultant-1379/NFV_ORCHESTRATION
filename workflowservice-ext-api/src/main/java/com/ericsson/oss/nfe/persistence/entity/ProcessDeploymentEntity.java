package com.ericsson.oss.nfe.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class ProcessDeploymentEntity implements Serializable{
	
	private static final long serialVersionUID = -2617141725027391098L;

	protected String deploymentId;
	
	protected String name;
	
	protected Date deploymentTime;
	 
	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDeploymentTime() {
		return deploymentTime;
	}

	public void setDeploymentTime(Date deploymentTime) {
		this.deploymentTime = deploymentTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deploymentId == null) ? 0 : deploymentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessDeploymentEntity other = (ProcessDeploymentEntity) obj;
		if (deploymentId == null) {
			if (other.deploymentId != null)
				return false;
		} else if (!deploymentId.equals(other.deploymentId))
			return false;
		return true;
	}
  
	 
}
