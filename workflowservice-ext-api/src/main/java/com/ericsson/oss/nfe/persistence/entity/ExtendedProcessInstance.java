package com.ericsson.oss.nfe.persistence.entity;

import java.io.Serializable;

public class ExtendedProcessInstance implements Serializable {

	private static final long serialVersionUID = -9156203444828472408L;

	private String workflowDefinitionId;
	private String workflowDefinitionName;
	private String id;
	private String parentProcessInstanceId;
	private String businessKey;
	private String startTime;
	private String endTime;
	private String progressPercentage;
	private String superProcessInstanceId;
	private int level;

	public String getWorkflowDefinitionId() {
		return workflowDefinitionId;
	}

	public void setWorkflowDefinitionId(String workflowDefinitionId) {
		this.workflowDefinitionId = workflowDefinitionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentProcessInstanceId() {
		return parentProcessInstanceId;
	}

	public void setParentProcessInstanceId(String parentProcessInstanceId) {
		this.parentProcessInstanceId = parentProcessInstanceId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(String progressPercentage) {
		this.progressPercentage = progressPercentage;
	}

	public String getSuperProcessInstanceId() {
		return superProcessInstanceId;
	}

	public void setSuperProcessInstanceId(String superProcessInstanceId) {
		this.superProcessInstanceId = superProcessInstanceId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	
	public String getWorkflowDefinitionName() {
		return workflowDefinitionName;
	}

	public void setWorkflowDefinitionName(String workflowDefinitionName) {
		this.workflowDefinitionName = workflowDefinitionName;
	}

	@Override
	public String toString() {
		return "ExtendedProcessInstance [workflowDefinitionId=" + workflowDefinitionId + ", id=" + id + ", parentProcessInstanceId=" + parentProcessInstanceId + ", businessKey=" + businessKey
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", progressPercentage=" + progressPercentage + ", superProcessInstanceId=" + superProcessInstanceId + ", level=" + level + "]";
	}

	
}
