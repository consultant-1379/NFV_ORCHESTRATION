package com.ericsson.oss.nfe.cli.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONException;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import com.ericsson.oss.nfe.cli.client.datatypes.ProcessInstance;
import com.ericsson.oss.nfe.cli.client.startconfig.InstanceStartConfiguration;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.RESTUtil;

/**
 * 	NFVCliClient 
 *   - Query Process Definition,
 *   - Query Process Instance,
 *   - Query Process Instance complete status,
 *   - Start Process Instance
 *
 */
public class NFVCommandLineHelper {

	private static final String BASE_URL = NFVConfigLoader.getProperty("BASE_URL");
	private static final String ALL_DEF_URL = NFVConfigLoader.getProperty("LIST_DEFINITION");
	private static final String ALL_DEF_URL_UNSORTED = NFVConfigLoader.getProperty("LIST_DEFINITION_UNSORTED");
	private static final String DEF_INST_URL = NFVConfigLoader.getProperty("LIST_DEF_INSTANCES");
	private static final String COUNT_INST_URL = NFVConfigLoader.getProperty("COUNT_DEF_INSTANCES");
	private static final String ALL_ACTIVE_URL = NFVConfigLoader.getProperty("LIST_ALL_ACTIVE");
	
	private static final String INCIDENTS_URL = NFVConfigLoader.getProperty("INSTACE_WITH_INCIDENTS");
	private static final String  DELETE_INSTANCE = NFVConfigLoader.getProperty("DELETE_INSTANCE");
	
	private static Map<String, String> defNameIdMap = new HashMap<String, String>();
	private static Map<String, String> defIdNameMap = new HashMap<String, String>();
	
	static {
		System.out.println("================================================================");
		System.out.println("Listening to JBOSS server running on: '"+BASE_URL+"'");
		System.out.println("================================================================");
		
		String endpointURL = BASE_URL + ALL_DEF_URL;
		try {
			String resp = new RESTUtil("NoAuth").doGETRequest(endpointURL, null);

			
			JSONArray dlist = new JSONArray(resp);
			for (int i = 0; i < dlist.length(); i++) {
				String name = (String) dlist.getJSONObject(i).get("name");
				String id = (String) dlist.getJSONObject(i).get("id");
				defNameIdMap.put(name, id);
				defIdNameMap.put(id, name);
			}
			
			
		} catch (RESTInvokeException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Print Process Definition Names in list.
	 */
	public static String getAlldefList() {
		String endpointURL = BASE_URL + ALL_DEF_URL;
		try {
			String resp = new RESTUtil("NoAuth").doGETRequest(endpointURL, null);

			System.out.println("-----------------------------------------------------------------------------------");
			JSONArray dlist = new JSONArray(resp);
			for (int i = 0; i < dlist.length(); i++) {
				String name = (String) dlist.getJSONObject(i).get("name");
				String id = (String) dlist.getJSONObject(i).get("id");
				System.out.println("\""+name+"\"");
				defNameIdMap.put(name, id);
				defIdNameMap.put(id, name);
			}
			System.out.println("-----------------------------------------------------------------------------------");
			return dlist.toString();
		} catch (RESTInvokeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get the progresses of Definition name.
	 * @param defName workflow definition name.           
	 */
	public static void getProgress(String defName) {
		String defID = getIdfordefName(defName);
		String endpointURL = BASE_URL + DEF_INST_URL + defID;
		String endpointURLCount = BASE_URL + COUNT_INST_URL + defID;
		try {
			String resp = new RESTUtil("NoAuth").doGETRequest(endpointURL, null);
			String countStr = new RESTUtil("NoAuth").doGETRequest(endpointURLCount, null);

			JSONObject countJson = new JSONObject(countStr);
			JSONArray dlist = new JSONArray(resp);

			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("Total Instances for \"" + getNamefordefId(defID) + "\" = " + countJson.get("count"));
			for (int i = 0; i < dlist.length(); i++) {
				int level = (int) dlist.getJSONObject(i).get("level");
				if (level < 1) {
					System.out.println("<-->");
				} else {
					for (int j = level; j > 0; j--)
						System.out.print("...");
				}
				System.out.print(getNamefordefId(((String) dlist.getJSONObject(i).get("workflowDefinitionId"))) + " --> ");
				System.out.println(dlist.getJSONObject(i).get("progressPercentage") + "%");

			}
			System.out.println("-----------------------------------------------------------------------------------");
		} catch (RESTInvokeException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Print the Active Instance List, each instance has following format:
	 *  - Definition Name :"Name"
	 *  - Start time: "2015-01-01T00:00:00"
	 *  - Instance ID: "578e96c8-b2b3-11e4-b490-d87b20524153"
	 */
	public static void getActiveInstanceList(){
		String endpointURL = BASE_URL + ALL_ACTIVE_URL;
		
		try {
			String response = new RESTUtil("noAuth").doGETRequest(endpointURL, null);
			JSONArray instanceList = new JSONArray(response);
			System.out.println("-----------------------------------------------------------------------------------");
			
			for(int i=0; i<instanceList.length(); i++){
				JSONObject jsonObject = instanceList.getJSONObject(i);
				String processDefID = jsonObject.getString("processDefinitionId");
				String processInsID = jsonObject.getString("id");
				String time = jsonObject.getString("startTime");
				String defName = defIdNameMap.get(processDefID);
				System.out.println("Active Instances for Definition \""+defName+"\"");
				System.out.println("Start time:"+time);
				System.out.println("Instance ID:"+processInsID);
				System.out.println();
			}
			System.out.println("-----------------------------------------------------------------------------------");
			
		} catch (RESTInvokeException e) {

			e.printStackTrace();
		}
		
		
	}
		
	/**
	 * Get Required Input form to start instance. If not required,
	 * null is returned.
	 * @param defName
	 * @return predefined JSON config file for specified definition name
	 * 
	 */
	public static JSONObject getInputForm(String defName){
		
		String defID = defNameIdMap.get(defName);
		String startFormURL = BASE_URL + ALL_DEF_URL_UNSORTED +"/" + defID +"/" +"startForm";
		
		JSONObject inputform = null;
		try {
			String response = new RESTUtil("noAuth").doGETRequest(startFormURL, null);
			JSONObject json= new JSONObject(response);
			String key = json.getString("key");
			
			// no input form required for starting this definition
			if(key.equals("null"))
				return null;
			
			String contextpath = json.getString("contextPath");
			
			String inputFormName = key.substring(key.lastIndexOf("model1:")+"model1:".length());
			
			String responseform = new RESTUtil("noAuth").doGETRequest(BASE_URL+"/"+contextpath+"/"+inputFormName, null);
			inputform =  InstanceStartConfiguration.readConfig(responseform);
			
			return inputform;
		} catch (RESTInvokeException e) {
			e.printStackTrace();
		}
		return inputform;
	}
	private static boolean checkDefinitionExist(String defName){
		if(!defNameIdMap.containsKey(defName))
		{
			
			System.err.println("Definition Name: '"+defName+"' doesn't exist.");
			System.err.println("Please select a definition name exists in the process application.");
			System.out.println();
			
			return false;
		}
		return true;
	}
	
	/**
	 * Delete instance by given ID
	 * @param instanceID
	 */
	public static void deleteInstance(String... instanceID){
		for(String instID : instanceID){
			NFVIncidentHelper.deleteProcessInstance(instID);
		}
	}
	
	/**
	 * Start Instance by with form, if no form required, then the input will
	 * be ignore and instance will be started straight away.
	 * 	@param defName definition name from which to start a instance
	 * 	@param path location for instance start configuration pair
	 * 	
	 */
	public static void startInstanceFromDefinition(String defName, String path){
		
		String endpointURL = BASE_URL + ALL_DEF_URL_UNSORTED;
		
		boolean exist = checkDefinitionExist(defName);
		if(!exist) return;
		
		JSONObject template = getInputForm(defName);
		JSONObject input;
		
		// no input form required from user
		if(template == null){
			System.out.println("No input form needed for starting this instance.");
			System.out.println("Property file from '"+path+"' is ignored");
			input = new JSONObject();
			input.put("variables", new JSONObject());
		}
		else{
			
			input = InstanceStartConfiguration.getInputJsonStr(path);
		
			if(input == null)
				return;
	
			if(InstanceStartConfiguration.verifyConfig(input, template) == false)
				return;
		}
		String defID = defNameIdMap.get(defName);
		endpointURL += "/"+defID+"/"+"start";
		System.out.println("-----------------------------------------------------------------------------------");
		try{
			JSONObject jsonResponse = new JSONObject(new RESTUtil("noAuth").doPOSTRequest(input.toString(), endpointURL, null));
			System.out.println("Instance has been started:");
			System.out.println("\tInstance ID:"+jsonResponse.getString("id"));
			System.out.println("\tDefinition ID:"+jsonResponse.getString("definitionId"));
			System.out.println("\tDefiniton Name:"+defIdNameMap.get(jsonResponse.getString("definitionId")));
		}
		catch(RESTInvokeException e){
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------------------------------------------");
	}
	
	/**
	 * Print Incidented Process Instance Details to user.
	 */
	public static void getIncidentedPI(){
		List<ProcessInstance> pInstances= NFVIncidentHelper.getTanglingProcessInstance();
		System.out.println("-----------------------------Tangling Instances-----------------------------------");
		if(pInstances.size()==0){
			System.out.println("No incidented Instances found.");
			System.out.println("----------------------------------------------------------------");
			return;
		}
		
		for(ProcessInstance pi : pInstances){
			String defID = pi.getProcessDefinitionKey();
			String insID = pi.getProcessInstanceID();
			String defName = defIdNameMap.get(defID);
			String startTime = pi.getDate();
			String incidentTime = pi.getIncidentTime();
			String incidentMesage = pi.getIncidentMessage();
			System.out.println("Process Instance ID:"+insID);
			System.out.println("Definition Name:"+defName);
			System.out.println("Start time: "+startTime);
			System.out.println("Incident time:"+incidentTime);
			System.out.println("Incident message:"+incidentMesage);
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------");
	}
	
	/**
	 * 
	 */
	public static void deleteAllTanglings(){
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Trying to delete all tangling instances found in NFV workflow service");
		
		List<ProcessInstance> pInstances= NFVIncidentHelper.getTanglingProcessInstance();
		System.out.println("-----------------------------Tangling Instances-----------------------------------");
		if(pInstances.size()==0){
			System.out.println("No incidented Instances found.");
			System.out.println("----------------------------------------------------------------");
			return;
		}
		for(ProcessInstance pi : pInstances){
			String defID = pi.getProcessDefinitionKey();
			String insID = pi.getProcessInstanceID();
			String defName = defIdNameMap.get(defID);
			
			deleteInstance(insID);
			
			System.out.println();
		}
		
		System.out.println("-----------------------------Deletion process stopped-----------------------------");
		
	}
	
	
	public static Set<String> getDefinitionIDSet(){
		return defIdNameMap.keySet();
	}
	public static String getIdfordefName(String name) {
		return defNameIdMap.get(name);
	}

	public static String getNamefordefId(String id) {
		return defIdNameMap.get(id);
	}

	public static String getIncidentURL() {
		return BASE_URL+INCIDENTS_URL;
	}

	public static String getDeleteInstanceURL() {
		return BASE_URL + DELETE_INSTANCE;
	}

	public static String getActiveInstanceURL() {
		return BASE_URL +  ALL_ACTIVE_URL;
	}
}
