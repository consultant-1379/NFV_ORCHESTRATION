package com.ericsson.oss.nfe.poc.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipInputStream;

import org.camunda.bpm.application.ProcessApplicationReference;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.RESTUtil;

public class ManageWorkflowTask implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private Expression requestString;

	private Expression wfname;
	
	private Expression method;

	private Expression outPutVariable;
	
	private static String DEFAULT_PROCESS_APPLICATION_NAME = "nfv-process-application-1.0.0";
	
	private static String GET_DEPLOYMENTS_URL = "http://localhost:8081/workflowservice-ext/rest/progress-service/process-deployments";
	
 
	public void execute(DelegateExecution execution) {
		
		log.info("------------------------------ManageWorkflow task started ----------------- ");
 		 
		domanageWF(execution);	 

		log.info("Variables" + execution.getVariables());
		
 	}

	private void domanageWF(DelegateExecution execution) {
		
		String inputrequest = requestString.getValue(execution).toString();

		String wfnameVar = wfname.getValue(execution).toString();
		
		String methodStr = method.getValue(execution).toString();		

		String outputVarName = outPutVariable.getExpressionText(); 
		String deploymentId = "NotDeployed";
		
		try {
			String filenamezip = wfnameVar;
			
			RepositoryService repositoryService = execution.getProcessEngineServices().getRepositoryService();
			
			String[] split = filenamezip.split(";");
							
			DeploymentBuilder createDeployment = repositoryService.createDeployment();
			
			createDeployment.name("Uploaded Deployment@"+System.currentTimeMillis());
			
			for (int i = 0; i < split.length; i++)
			{
				File file = new File(split[0]);
				if (file.getName().contains(".jar") || file.getName().contains(".war") || file.getName().contains(".zip")){ 
					createDeployment.addZipInputStream(new ZipInputStream(new FileInputStream(file)));
				}
				else {
					createDeployment.addInputStream(file.getName(),new FileInputStream(file));
				}
			}
			
			Deployment deploy = createDeployment.deploy();
			deploymentId = deploy.getId();
			
			ProcessApplicationReference currentProcessAppReference = null;
				
			if(getLatestDeploymentID()==null){
				log.warn("No default applicaiton named \""+DEFAULT_PROCESS_APPLICATION_NAME+"\" found!");
				log.warn("Using the current process application to register the deployment!");
				currentProcessAppReference = Context.getCurrentProcessApplication();   
			}
			else{
				currentProcessAppReference = Context.getProcessEngineConfiguration()
												    .getProcessApplicationManager()
												    .getProcessApplicationForDeployment(getLatestDeploymentID());
			}
			
			// Register the deployment with a process application
			execution.getProcessEngineServices().getManagementService().registerProcessApplication(deploymentId, currentProcessAppReference);	
			execution.setVariable(outputVarName, deploymentId);
				
		}
		catch (Exception e){
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Failed to deploy User Uploaded workflow ");
		}
		
	}
	
	private String getLatestDeploymentID() throws RESTInvokeException{
		log.info("Get Latest Deplopyment ID for Default Process Application named: "+DEFAULT_PROCESS_APPLICATION_NAME);
		log.info("Query Process Application Deployments on URL: "+GET_DEPLOYMENTS_URL);
		
		String deploymentID = null;
		JSONArray jsonResponse = new JSONArray(new RESTUtil().doGETRequest(
				GET_DEPLOYMENTS_URL, null));

		for (int index = jsonResponse.length()-1; index>=0; index--) {
			JSONObject currentDeployment = jsonResponse.getJSONObject(index);
			if (DEFAULT_PROCESS_APPLICATION_NAME.equals(currentDeployment.getString("name"))) {
				deploymentID = currentDeployment.getString("deploymentId");
			}
		}
		
		return deploymentID;
	}
	
	
	



	
}