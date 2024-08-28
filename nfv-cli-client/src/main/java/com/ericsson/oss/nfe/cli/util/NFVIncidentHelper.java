package com.ericsson.oss.nfe.cli.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import com.ericsson.oss.nfe.cli.client.datatypes.IncidentInstance;
import com.ericsson.oss.nfe.cli.client.datatypes.ProcessInstance;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.RESTUtil;

public class NFVIncidentHelper {
	
	/**
	 * Save all incidents instances into memory.
	 */
	public static List<IncidentInstance> getIncidents(){
		List<IncidentInstance> incidents = new ArrayList<IncidentInstance>();
		
		String endpointURL = NFVCommandLineHelper.getIncidentURL();
		try {
			String response = new RESTUtil("noAuth").doGETRequest(endpointURL, null);
			JSONArray instanceList = new JSONArray(response);
		
			for(int i=0; i<instanceList.length(); i++){
				JSONObject jsonObject = instanceList.getJSONObject(i);
				String incidentID = jsonObject.getString("id");
				String defID = jsonObject.getString("processDefinitionId");
				String instanceID = jsonObject.getString("processInstanceId");
				String incidentTimestamp = jsonObject.getString("incidentTimestamp");
				String incidentType = jsonObject.getString("incidentType");
				String incidentMessage = jsonObject.getString("incidentMessage");
				
				IncidentInstance incident = new IncidentInstance(incidentID, instanceID, defID);
				incident.setIncidentTimestamp(incidentTimestamp);
				incident.setIncidentType(incidentType);
				incident.setIncidentMessage(incidentMessage);
				
				incidents.add(incident);
			}
			 
			
		} catch (RESTInvokeException e) {

			e.printStackTrace();
		}
		return incidents;
	}
	
	/**
	 * Save all unfinished active instances into memory.
	 */
	public static List<ProcessInstance> getActiveProcessInstance(){
		
		List<ProcessInstance> processInstances = new ArrayList<ProcessInstance>();
		String endpointURL =  NFVCommandLineHelper.getActiveInstanceURL();
		try {
			
			String response = new RESTUtil("noAuth").doGETRequest(endpointURL, null);
			JSONArray instanceList = new JSONArray(response);
			
			for(int i=0;i<instanceList.length();i++){
				JSONObject instance = instanceList.getJSONObject(i);
				ProcessInstance pi = new ProcessInstance(instance.getString("processDefinitionId"), instance.getString("id"));
				String dateStr = instance.getString("startTime");
				pi.setDate(dateStr);
				
				processInstances.add(pi);
				
			}

		} catch (RESTInvokeException e) {
			e.printStackTrace();
		}		
		
		return processInstances;
	}
	
	/**
	 * Filter the incidented instances to be deleted.
	 */
	public static List<ProcessInstance> getTanglingProcessInstance(){
		
		List<ProcessInstance> pInstances = getActiveProcessInstance();
		List<IncidentInstance> incidents = getIncidents();
		List<ProcessInstance> tanglings = new ArrayList<ProcessInstance>();
	
		Map<String, IncidentInstance> insIDIncidentMap = new HashMap<String, IncidentInstance>();
		Set<String> incidentedInstances = insIDIncidentMap.keySet();
		
		for(IncidentInstance incident :incidents){
			insIDIncidentMap.put(incident.getProcessInstanceId(), incident);
		}
		
		for(ProcessInstance instance: pInstances){
			if(incidentedInstances.contains(instance.getProcessInstanceID())){
				IncidentInstance incidentMatched = insIDIncidentMap.get(instance.getProcessInstanceID());
				instance.setIncidentTime(incidentMatched.getIncidentTimestamp());
				instance.setIncidentMessage(incidentMatched.getIncidentType());
				tanglings.add(instance);
			}
		}
			
		return tanglings;
	}
	/**
	 * Delete Process Instance by given ID. 
	 * @param processInstanceID
	 * @return true or false
	 */

	public static boolean deleteProcessInstance(String processInstanceID){
		boolean deletionStatus = false;
		
		String endpointURL = NFVCommandLineHelper.getDeleteInstanceURL()  + processInstanceID;
		
		try {

			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("-----------------------------Start to delete Instance------------------------------");
			System.out.println();
			
			new RESTUtil("noAuth").doDeleteRequest(endpointURL, null);
			
		}catch (RESTInvokeException e) {
			e.printStackTrace();
		}catch(RuntimeException e){
			// capture the HTTP status Code, like 204, or 404
			String errorMessage = e.getMessage();
			String []message = errorMessage.replace("\\D+", " ").split("\\s");
			for(String str :message){
				str = str.trim();
				if(str.length()>0)
					if(str.equals("404")){
						System.err.println("Process Instance ID: '"+processInstanceID+"' hasn't been found.");
						break;
					}
					else if(str.equals("204")){
						System.out.println("Process Instance ID: '"+processInstanceID+"' has been deleted.");
						deletionStatus = true;
						break;
					}
				
			}
			if(!deletionStatus)
				System.err.println("Error occurred during deleting the process instance");
		}
		System.out.println();
		System.out.println("-----------------------------Delete Instance Process ended-------------------------");
		return deletionStatus;
	}
}
