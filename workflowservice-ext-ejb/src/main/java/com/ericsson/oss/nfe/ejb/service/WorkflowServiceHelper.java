package com.ericsson.oss.nfe.ejb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.config.ConfigurationServiceLocal;
import com.ericsson.oss.nfe.persistence.entity.ExtendedProcessDefinition;
import com.ericsson.oss.nfe.persistence.entity.ExtendedProcessInstance;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.RESTUtil;
import com.ericsson.oss.nfe.poc.utils.vo.HeaderTupple;

@Singleton
public class WorkflowServiceHelper {

	@Inject
	private ConfigurationServiceLocal configBean;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	  
	public String getWorkFlowDefinitionByKey(String workflowKey) {

		String getProcessDefinitionURL = configBean.getCamundaBaseURL() + "/process-definition/key/" + workflowKey;

		// String getProcessDefinitionURL = configBean.getCamundaBaseURL()+
		// "/process-definition?key="+workflowKey+"&latest=true&active=true";

		try {
			String response = new RESTUtil().doGETRequest(getProcessDefinitionURL, new ArrayList<HeaderTupple>());

			JSONObject processDefn = (JSONObject) JSONValue.parse(response);
			if (processDefn != null && !((Boolean) processDefn.get("suspended"))) {
				return (String) processDefn.get("id");
			}

		} catch (RESTInvokeException e) {

			e.printStackTrace();
		}

		return null;
	}
	
	public String getWorkflowDefinitionNameById(String workflowDefinitionId) {

		String getProcessDefinitionURL = configBean.getCamundaBaseURL() + "/process-definition/" + workflowDefinitionId;

		// String getProcessDefinitionURL = configBean.getCamundaBaseURL()+
		// "/process-definition?key="+workflowKey+"&latest=true&active=true";

		try {
			String response = new RESTUtil().doGETRequest(getProcessDefinitionURL, new ArrayList<HeaderTupple>());

			JSONObject processDefn = (JSONObject) JSONValue.parse(response);
			if (processDefn != null) {
				return (String) processDefn.get("name");
			}

		} catch (Exception e) {}

		return null;
	}
	
	public ExtendedProcessInstance getProcessInstanceDetails(String processInstanceId) {

		String getProcessDefinitionURL = configBean.getCamundaBaseURL() + "/history/process-instance/" + processInstanceId;

		ExtendedProcessInstance processInstance = null;
		// String getProcessDefinitionURL = configBean.getCamundaBaseURL()+
		// "/process-definition?key="+workflowKey+"&latest=true&active=true";

		try {
			String response = new RESTUtil().doGETRequest(getProcessDefinitionURL, new ArrayList<HeaderTupple>());

			JSONObject processInstanceJson = (JSONObject) JSONValue.parse(response);
			if (processInstanceJson != null) {
				
				processInstance = new ExtendedProcessInstance();
				processInstance.setId(processInstanceId);
				String workflowDefinitionId = (String)processInstanceJson.get("processDefinitionId");
				processInstance.setWorkflowDefinitionId(workflowDefinitionId);
				processInstance.setWorkflowDefinitionName(this.
						getWorkflowDefinitionNameById(workflowDefinitionId));
				processInstance.setSuperProcessInstanceId((String)processInstanceJson.get("superProcessInstanceId"));
				
				//processInstance.set
				
				return  processInstance;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return processInstance;
	}

	public JSONArray getInstancesForDefinition(String processDefnID, String order) {

		String getDefnInstancesURL = configBean.getCamundaBaseURL() + "/history/process-instance?unfinished=true&sortBy=startTime&sortOrder=" + order + "&processDefinitionId=" + processDefnID;
		String getAllInstancesURL = configBean.getCamundaBaseURL() + "/history/process-instance?unfinished=true&sortBy=startTime&sortOrder=asc";

		try {
			String responseDefn = new RESTUtil().doGETRequest(getDefnInstancesURL, new ArrayList<HeaderTupple>());
			String responseAll = new RESTUtil().doGETRequest(getAllInstancesURL, new ArrayList<HeaderTupple>());
			// JSONArray
			JSONArray instancesForDefn = (JSONArray) JSONValue.parse(responseDefn);
			JSONArray allActiveInstances = (JSONArray) JSONValue.parse(responseAll);

			JSONArray instancesArray = new JSONArray();
			Map<String, JSONObject> childObjectsMap = new HashMap<String, JSONObject>();

//			for (int i = 0; i < processInstances.size(); i++) {
//				JSONObject parentProcessInstance = (JSONObject) processInstances.get(i);
//				String parentProcessInstanceID = (String) parentProcessInstance.get("id");
//				instancesArray.add(parentProcessInstance);
//				
//				for (int j = 0; j < allProcessesInstances.size(); j++) {
//					JSONObject childProcessInstance = (JSONObject) allProcessesInstances.get(j);
//					String parentID = (String) childProcessInstance.get("superProcessInstanceId");
//					
//					if(parentID!=null && parentID.equals(parentProcessInstanceID)) {
//						instancesArray.add(childProcessInstance);
//					}
//					
//					for (int k = 0; k < allProcessesInstances.size(); k++) {
//						JSONObject gChildProcessInstance = (JSONObject) allProcessesInstances.get(k);
//						String gParentID = (String) gChildProcessInstance.get("superProcessInstanceId");
//						
//						if(gParentID!=null && gParentID.equals(childProcessInstance)) {
//							instancesArray.add(gChildProcessInstance);
//						}
//					}
//				}
//			}
			
			for (int j = 0; j < allActiveInstances.size(); j++) {
				JSONObject instance = (JSONObject) allActiveInstances.get(j);
				String superProcessInstanceId = (String) instance.get("superProcessInstanceId");
				
				if (superProcessInstanceId == null) 
					continue;
				else
					childObjectsMap.put(superProcessInstanceId, instance);
			}
			
			for (int i = 0; i < instancesForDefn.size(); i++) {
				JSONObject parentProcessInstance = (JSONObject) instancesForDefn.get(i);
				int level = 0;
				parentProcessInstance.put("level", level);
//				parentProcessInstance.remove("parentProcessInstanceId");
				instancesArray.add(parentProcessInstance);
				
				String parentId = (String) parentProcessInstance.get("id");
				boolean hasChild = true;
				
				while(hasChild) {
					JSONObject child = childObjectsMap.get(parentId);
					if (child != null) {
						child.put("level", ++level);
						instancesArray.add(child);
						parentId = (String) child.get("id");
					} else {
						hasChild = false;
					}
				}
			}
			
			return instancesArray;
			
		} catch (RESTInvokeException e) {

			e.printStackTrace();
		}
		return null;
	}

	
	public List<ExtendedProcessDefinition> getProcessDefinitions(String order, String firstResult, String maxResults, String userType) {
		String getDefnInstancesURL = configBean.getCamundaBaseURL() + "/process-definition?latest=true&sortBy=name&sortOrder=" + order + "&maxResults="+ maxResults + "&firstResult="+ firstResult;
		
		if (!userType.equals("admin")) {
			getDefnInstancesURL+="&categoryLike=%25"+userType+"%25";
		}
 
		List<ExtendedProcessDefinition> defnList = new ArrayList<>();
		
		try {
			String responseDefn = new RESTUtil().doGETRequest(getDefnInstancesURL, new ArrayList<HeaderTupple>());
			// JSONArray
			JSONArray processDefns = (JSONArray) JSONValue.parse(responseDefn);


			for (int i = 0; i < processDefns.size(); i++) {
				JSONObject procesDefJson = (JSONObject) processDefns.get(i);
				
				ExtendedProcessDefinition procesdefn = new ExtendedProcessDefinition();
				
				procesdefn.setId((String) procesDefJson.get("id"));
				procesdefn.setKey((String) procesDefJson.get("key"));
				procesdefn.setName((String) procesDefJson.get("name"));
				//procesdefn.setDescription((String) procesDefJson.get("description"));
				procesdefn.setDeploymentId((String) procesDefJson.get("deploymentId"));		
				procesdefn.setVersion(((Long)procesDefJson.get("version")).intValue());
				
				String category = (String) procesDefJson.get("category");
				if(category!=null && category.contains("/"))
				{
					category = category.substring(category.lastIndexOf("/")+1);
					procesdefn.setCategory(category);
				}
				else
					procesdefn.setCategory("UnCategorised");
				
					defnList.add(procesdefn);
				
			}
			
			return defnList;
		} catch (RESTInvokeException e) {

			e.printStackTrace();
		}
		return defnList;
	}
	
	
	
	public String startWorkflowByKey(String workflowKey, Map<String, String> variables)
	  {
	    String startbyKeyURL = this.configBean.getCamundaBaseURL() + "/process-definition/key/" + workflowKey + "/start";

	    List headers = new ArrayList();
	    headers.add(new HeaderTupple("Content-Type", "application/json"));
	    headers.add(new HeaderTupple("Accept", "application/json"));
	    try
	    {
	      JSONObject processVariableList = new JSONObject();

	      for (Map.Entry mapEntry : variables.entrySet()) {
	        JSONObject temp = new JSONObject(); temp.put("value", mapEntry.getValue());
	        processVariableList.put(mapEntry.getKey(), temp);
	      }
	      JSONObject processVariable = new JSONObject();
	      processVariable.put("variables", processVariableList);
	      String response = new RESTUtil().doPOSTRequest(processVariable.toJSONString(), startbyKeyURL, headers);

	      JSONObject processInstance = (JSONObject)JSONValue.parse(response);
	      if (processInstance != null) {
	        return (String)processInstance.get("id");
	      }
	    }
	    catch (RESTInvokeException e)
	    {
	      this.logger.error("Error starting process definition by key", e);
	    }

	    return null;
	  }
//	public JSONArray getUserTask(String taskId){
//		
//		String getUserTaskURL = configBean.getCamundaBaseURL() + "/task/" + taskId;
//		System.out.println(">>>>>>>>>>>>>>>>>" +getUserTaskURL+"<<<<<<<<<<<<<<<<<<<<<<<");
//
//		try {
//			String responseUserTask = new RESTUtil().doGETRequest(getUserTaskURL, new ArrayList<HeaderTupple>());
//			//String responseAll = new RESTUtil().doGETRequest(getAllInstancesURL, new ArrayList<HeaderTupple>());
//			// JSONArray
//			JSONArray processUserTasks = (JSONArray) JSONValue.parse(responseUserTask);
//			//JSONArray allProcessesInstances = (JSONArray) JSONValue.parse(responseAll);
//
//			JSONArray instancesArray = new JSONArray();
//
//			for (int i = 0; i < processUserTasks.size(); i++) {
//				JSONObject parentProcessInstance = (JSONObject) processUserTasks.get(i);
//				instancesArray.add(parentProcessInstance);
//			}
//			
//			return instancesArray;
//		} catch (RESTInvokeException e) {
//
//			e.printStackTrace();
//		}
//		return null;
//	}
}
