package com.ericsson.oss.nfe.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * JPA persistent class for the "WorkflowProgressEvent" database table.
 * 
 */
@Entity
@Table(name="ProcessTriggerDefinition")
public class ProcessTriggerDefinitionEntity implements Serializable{
	 	
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
	    private long id;

	    private String triggerName;
	    
	    private String triggerType;
	    
	    private String schemaVersion;
	    
	    private String filterString;
	    
	    
	    //@OneToMany(fetch = FetchType.LAZY,mappedBy="trigger",cascade=CascadeType.ALL)
	    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval=true)
		@JoinColumn(name = "trigger_id")
	    private Set<ProcessTriggerWorkflowEntity> mappedWorkFlows;
	    
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

		public String getSchemaVersion() {
			return schemaVersion;
		}

		public void setSchemaVersion(String schemaVersion) {
			this.schemaVersion = schemaVersion;
		}
		 

		public String getFilterString() {
			return filterString;
		}


		public void setFilterString(String filterString) {
			this.filterString = filterString;
		}


		public Set<ProcessTriggerWorkflowEntity> getMappedWorkFlows() {
			return mappedWorkFlows;
		}


		public void setMappedWorkFlows(
				Set<ProcessTriggerWorkflowEntity> mappedWorkFlows) {
			this.mappedWorkFlows = mappedWorkFlows;
		}
		
		public void addMappedWorkFlows(
				ProcessTriggerWorkflowEntity workFlowEntity) {
			if(this.mappedWorkFlows==null)
				this.mappedWorkFlows = new HashSet<ProcessTriggerWorkflowEntity>();
				
			this.mappedWorkFlows.add(workFlowEntity);
		}


		public void setId(long id) {
			this.id = id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((filterString == null) ? 0 : filterString.hashCode());
			result = prime * result
					+ ((triggerType == null) ? 0 : triggerType.hashCode());
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
			ProcessTriggerDefinitionEntity other = (ProcessTriggerDefinitionEntity) obj;
			if (filterString == null) {
				if (other.filterString != null)
					return false;
			} else if (!filterString.equals(other.filterString))
				return false;
			if (triggerType == null) {
				if (other.triggerType != null)
					return false;
			} else if (!triggerType.equals(other.triggerType))
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "ProcessTrigger [id=" + id + ", triggerName=" + triggerName
					+ ", triggerType=" + triggerType + ", schemaVersion="
					+ schemaVersion + "filterString="+ filterString+"]";
		}
	     
		 @Entity
		 @Table(name="TriggerDefnWorkflowMapping")
		 public static  class ProcessTriggerWorkflowEntity implements Serializable{
			 
			 	@Id
			    @GeneratedValue(strategy=GenerationType.IDENTITY)
			    private long id;
			    
			    private String workflowKey;
			    
				@Column(insertable=false,name = "trigger_id",nullable=true)
				private long triggerdefnitionid;
				
			   /* @ManyToOne(fetch=FetchType.EAGER)
			    @JoinColumn(name="processTriggerId")
			    private ProcessTriggerDefinitionEntity trigger;*/
			    
			    public ProcessTriggerWorkflowEntity(){}
			    
			    public ProcessTriggerWorkflowEntity(String workflowKey)
			    {
			    	this.workflowKey=workflowKey;
			    }

				public long getId() {
					return id;
				} 
				

				public String getWorkflowKey() {
					return workflowKey;
				}

				public void setWorkflowKey(String workflowKey) {
					this.workflowKey = workflowKey;
				}
				  
				
			 
				public void setTriggerdefnitionid(long triggerdefnitionid) {
					this.triggerdefnitionid = triggerdefnitionid;
				}

				@Override
				public int hashCode() {
					final int prime = 31;
					int result = 1;
					result = prime
							* result
							+ ((workflowKey == null) ? 0 : workflowKey
									.hashCode());
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
					ProcessTriggerWorkflowEntity other = (ProcessTriggerWorkflowEntity) obj;
					if (workflowKey == null) {
						if (other.workflowKey != null)
							return false;
					} else if (!workflowKey.equals(other.workflowKey))
						return false;
					return true;
				}

				@Override
				public String toString() {
					return "ProcessTriggerWorkflowEntity [id=" + id
							+ ", workflowKey=" + workflowKey +  "]";
				}
				
				
		 }

		
}



