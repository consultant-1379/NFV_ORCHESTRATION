/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.nfe.cli.client.datatypes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessInstance {
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private String	processDefinitionKey;
	private Date	date;
	private String	processInstanceID;

	private Date	incidentTime;
	private String incidentMessage;
	
	/**
	 * @param processDefinitionKey
	 * @param date
	 */
	public ProcessInstance(String processDefinitionKey, Date date) {
		super();
		this.processDefinitionKey = processDefinitionKey;
		this.date = date;
	}

	public ProcessInstance(String processDefinitionKey, String processInstanceID) {
		this.processDefinitionKey = processDefinitionKey;
		this.processInstanceID = processInstanceID;
	}

	/**
	 * @return the processDefinitionKey
	 */
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	/**
	 * @param processDefinitionKey
	 *            the processDefinitionKey to set
	 */
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return formatter.format(this.date);
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String dateStr) {
		try {
			this.date = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	public String getIncidentTime() {
		return formatter.format(this.incidentTime);
	}

	public void setIncidentTime(String incidentTime) {
		try {
			this.incidentTime = formatter.parse(incidentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	
	public String getIncidentMessage() {
		return incidentMessage;
	}

	public void setIncidentMessage(String incidentMessage) {
		this.incidentMessage = incidentMessage;
	}

	public String getProcessInstanceID() {
		return processInstanceID;
	}

	public void setProcessInstanceID(String processInstanceID) {
		this.processInstanceID = processInstanceID;
	}
}
