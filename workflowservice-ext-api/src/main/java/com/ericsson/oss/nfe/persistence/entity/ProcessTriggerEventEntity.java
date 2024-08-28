package com.ericsson.oss.nfe.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * JPA persistent class for the "WorkflowProgressEvent" database table.
 * 
 */
@Entity
@Table(name = "ProcessTriggerEvent")
public class ProcessTriggerEventEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	private String triggerName;

	private String triggerType;

	private String filterString;

	private String status;
	
	private String externalEventId;

	@Temporal(TemporalType.DATE)
	private Date occuredOn;

	public ProcessTriggerEventEntity(){}
	
	
	public ProcessTriggerEventEntity(String triggerName, String triggerType,
			String status, Date occuredOn) {
		super();
		this.triggerName = triggerName;
		this.triggerType = triggerType;
		this.status = status;
		this.occuredOn = occuredOn;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "trigger_eventid")
	private Set<TriggeredWorkflowEntity> triggeredWorkFlows;

	public long getId() {
		return id;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFilterString() {
		return filterString;
	}

	public void setFilterString(String filterString) {
		this.filterString = filterString;
	}

	public Date getOccuredOn() {
		return occuredOn;
	}

	public void setOccuredOn(Date occuredOn) {
		this.occuredOn = occuredOn;
	}
	 

	public String getExternalEventId() {
		return externalEventId;
	}


	public void setExternalEventId(String externalEventId) {
		this.externalEventId = externalEventId;
	}


	public Set<TriggeredWorkflowEntity> getTriggeredWorkFlows() {
		return triggeredWorkFlows;
	}

	public void setTriggeredWorkFlows(
			Set<TriggeredWorkflowEntity> triggeredWorkFlows) {
		this.triggeredWorkFlows = triggeredWorkFlows;
	}

	public void addTriggeredWorkFlow(TriggeredWorkflowEntity workFlowEntity) {
		if (this.triggeredWorkFlows == null)
			this.triggeredWorkFlows = new HashSet<TriggeredWorkflowEntity>();
		this.triggeredWorkFlows.add(workFlowEntity);
	}

	@Override
	public String toString() {
		return "ProcessTriggerEventEntity [id=" + id + ", triggerName="
				+ triggerName + ", triggerType=" + triggerType + "]";
	}

	@Entity
	@Table(name = "TriggerEventWorkflowMapping")
	public static class TriggeredWorkflowEntity implements Serializable {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		@Column(insertable=false,name = "trigger_eventid",nullable=true)
		private long triggerEventid;

		private String workflowKey;

		private String workInstanceId;

		public TriggeredWorkflowEntity(){} 
		
		
		public TriggeredWorkflowEntity(String workflowKey) {
			super();
			this.workflowKey = workflowKey;
		}
 

		public String getWorkflowKey() {
			return workflowKey;
		}

		public void setWorkflowKey(String workflowKey) {
			this.workflowKey = workflowKey;
		}

		public String getWorkInstanceId() {
			return workInstanceId;
		}

		public void setWorkInstanceId(String workInstanceId) {
			this.workInstanceId = workInstanceId;
		}

		public long getId() {
			return id;
		}

		public long getTriggerEventid() {
			return triggerEventid;
		}

		public void setTriggerEventid(long triggerEventid) {
			this.triggerEventid = triggerEventid;
		}

	}

}
