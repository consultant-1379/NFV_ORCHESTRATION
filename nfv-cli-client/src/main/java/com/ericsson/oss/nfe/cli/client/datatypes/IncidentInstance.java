package com.ericsson.oss.nfe.cli.client.datatypes;

public class IncidentInstance {
	private String	incidentID;
	private String	processInstanceId;
	private String	processDefinitionId;
	private String	incidentTimestamp;
	private String	incidentType;
	private String	incidentMessage;

	public IncidentInstance(String incidentID, String processInstanceId,
			String processDefinitionId) {
		this.incidentID = incidentID;
		this.processInstanceId = processInstanceId;
		this.processDefinitionId = processDefinitionId;
	}

	public String getIncidentID() {
		return incidentID;
	}

	public void setIncidentID(String incidentID) {
		this.incidentID = incidentID;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getIncidentTimestamp() {
		return incidentTimestamp;
	}

	public void setIncidentTimestamp(String incidentTimestamp) {
		this.incidentTimestamp = incidentTimestamp;
	}

	public String getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}

	public String getIncidentMessage() {
		return incidentMessage;
	}

	public void setIncidentMessage(String incidentMessage) {
		this.incidentMessage = incidentMessage;
	}

	@Override
	public String toString() {
		return "InstanceID:" + incidentID + ",\n" + "Process InstanceId:" + processInstanceId
				+ ",\nprocessDefinitionId:" + processDefinitionId + ",\nincidentTimestamp:"
				+ incidentTimestamp + ",\nincidentType" + incidentType;
	}
}
