package com.ericsson.oss.nfe.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.config.ConfigurationServiceLocal;
import com.ericsson.oss.nfe.ejb.service.ProcessDefinitionCallable;
import com.ericsson.oss.nfe.ejb.service.WorkflowServiceHelper;
import com.ericsson.oss.nfe.persistence.ProcessInstanceProgressDAO;
import com.ericsson.oss.nfe.persistence.ProcessTriggerDAO;
import com.ericsson.oss.nfe.persistence.entity.ExtendedProcessDefinition;
import com.ericsson.oss.nfe.persistence.entity.ExtendedProcessInstance;
import com.ericsson.oss.nfe.persistence.entity.ProcessDeploymentEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity.ProcessTriggerWorkflowEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerEventEntity;

@Stateless
public class ProcessInstanceProgressServiceBean implements ProcessInstanceProgressServiceLocal,ProcessInstanceProgressServiceRemote {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ProcessInstanceProgressDAO processInstanceProgressDAO;
	
	@Inject
	private ProcessTriggerDAO processTriggerDAO;

	@Inject
	private WorkflowServiceHelper helper;
	
	@Inject
	private ConfigurationServiceLocal configBean;

	@Override
	public List<ExtendedProcessInstance> getProcessInstanceForDefinitionId(String processDefnID, String order) {

		JSONArray result = helper.getInstancesForDefinition(processDefnID, order);

		List<ExtendedProcessInstance> instancesList = getInstanceList(result);

		return processInstanceProgressDAO.getProcessInstancesProgressMap(instancesList);
	}

	private List<ExtendedProcessInstance> getInstanceList(JSONArray result) {

		List<ExtendedProcessInstance> instancesList = new ArrayList<ExtendedProcessInstance>();

		// TODO List<ExtendedProcessInstance> from JSONArray
		for (int i = 0; i < result.size(); i++) {

			// String workflowDefinitionId; -- processDefinitionId
			// String id; -- id
			// String parentProcessInstanceId;
			// String businessKey; -- businessKey
			// Date startTime; -- startTime
			// Date endTime; -- endTime
			// String superProcessInstanceId; -- superProcessInstanceId
			
			JSONObject j = (JSONObject) result.get(i);

			ExtendedProcessInstance instance = new ExtendedProcessInstance();
			instance.setWorkflowDefinitionId((String) j.get("processDefinitionId"));
			instance.setId((String) j.get("id"));
			instance.setBusinessKey((String) j.get("businessKey"));
			instance.setStartTime((String) j.get("startTime"));
			instance.setEndTime((String) j.get("endTime"));
			instance.setSuperProcessInstanceId((String) j.get("superProcessInstanceId"));
			instance.setLevel((int) j.get("level"));
			
			instancesList.add(instance);
		}
		return instancesList;
	}
	
	
	
	public List<ProcessTriggerDefinitionEntity> getAllTriggerDefinitions()
	{
		return processTriggerDAO.getAllTriggerDefinitions();
	}
	
	public List<ProcessTriggerWorkflowEntity> getMappedWorkFlowDefinitions(String triggerType,String filterString){
		return processTriggerDAO.
				getMappedWorkFlowDefinitions(null, triggerType, filterString);
	}

	@Override
	public List<ProcessDeploymentEntity> getAllProcessDeployments() {
		// TODO Auto-generated method stub
		return processInstanceProgressDAO.getAllProcessDeployments();
	}
	
	
	public List<ExtendedProcessDefinition> getProcessDefinitions(String sortOrder, String firstResult, String maxResults, String userType)
	{
 
		 long start=System.currentTimeMillis();
		
		 List<ExtendedProcessDefinition> result = helper.getProcessDefinitions("asc", firstResult, maxResults, userType);
		 
		 ExecutorService executor = Executors.newFixedThreadPool(10);
		 
		 List<ProcessDefinitionCallable> callables = new ArrayList<ProcessDefinitionCallable>();
		 
		 List<Future<ExtendedProcessDefinition>> futureList = new ArrayList<Future<ExtendedProcessDefinition>>();
		 
		 for (ExtendedProcessDefinition iterator :result) {			 
			 ProcessDefinitionCallable callable = new ProcessDefinitionCallable(configBean.getCamundaBaseURL(), iterator);
			 Future<ExtendedProcessDefinition> future = executor.submit(callable);
			 futureList.add(future);			
		}		 
		 
		 result = new ArrayList<>();
		 
		 Map<String,Date> deploymentIdmap = this.getDeploymentTimeMap();
		 
		 for (Future<ExtendedProcessDefinition> futureItr :futureList) {			 
			 try 
			 {
				 ExtendedProcessDefinition currentItr = futureItr.get();
				 if(currentItr!=null)
				 {
					 currentItr.setCreatedOn(deploymentIdmap.get(currentItr.getDeploymentId()));
				 }
				result.add(currentItr);
				
			} catch (InterruptedException e) {System.err.println("Error in future exectuion :"+e.getMessage());}
			  catch (ExecutionException e) {System.err.println("Error in future exectuion :"+e.getMessage());}
		}
		 
		 System.out.println("Total Exectuion Time :"+(System.currentTimeMillis()-start));
		 return result;
		 
	}
	
	private Map<String,Date> getDeploymentTimeMap()
	{
		List<ProcessDeploymentEntity> alldeployments = processInstanceProgressDAO.getAllProcessDeployments();
		if(alldeployments!=null && !alldeployments.isEmpty())
		{
			Map<String,Date> deploymentIdmap = new HashMap<String,Date>();
			for(ProcessDeploymentEntity deployment : alldeployments)
				deploymentIdmap.put(deployment.getDeploymentId(), deployment.getDeploymentTime());
			
			return deploymentIdmap; 
			
		}
		return new HashMap<String,Date>();
	}

	@Override
	public List<ProcessTriggerEventEntity> getAllTriggerEvents() {
		 
		logger.debug("getAllTriggerEvents bean method called ");
		return processTriggerDAO.getAllTriggerEvents();
	}

	public List<ProcessTriggerEventEntity> getAllTriggerEvents(String triggerType,String filterString) {
		
		logger.debug("getAllTriggerEvents bean method called ");
		return processTriggerDAO.getAllTriggerEvents(triggerType, filterString);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String deleteProcessTriggerDefinition(String definitionId){
		return this.processTriggerDAO.deleteProcessTriggerDefinition(definitionId);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String  deleteProcessTriggerWorkflowEntity(String id){
		return this.processTriggerDAO.deleteProcessTriggerWorkflowEntity(id);
	}

	@Override
	public List<ExtendedProcessInstance> getProcessAncestry(
			String processInstanceId) {
		
		List<ExtendedProcessInstance> instanceList = new ArrayList<ExtendedProcessInstance>();
		ExtendedProcessInstance currerntInst;
		int level=0;
		do {
			
			currerntInst = this.helper.getProcessInstanceDetails(processInstanceId);
			if(currerntInst!=null)
			{
				currerntInst.setLevel(level);		
				instanceList.add(currerntInst);
				processInstanceId = currerntInst.getSuperProcessInstanceId();
			}			
			level--;
			
		} while (currerntInst!=null && currerntInst.getSuperProcessInstanceId()!=null);
		 
		// TODO Auto-generated method stub
		return instanceList;
	}

	@Override
	public ProcessTriggerDefinitionEntity setProcessTriggerDefinitionEntity(ProcessTriggerDefinitionEntity identity) {
		// TODO Auto-generated method stub
		return processTriggerDAO.modifyProcessTriggerDefinition(identity);
	}

	@Override
	public ExtendedProcessInstance getInstanceStatus(String processInstanceId) {
		ExtendedProcessInstance result = this.helper.getProcessInstanceDetails(processInstanceId);
		String defnId = result.getWorkflowDefinitionId();
		List<ExtendedProcessInstance> allInstances = getProcessInstanceForDefinitionId(defnId, "desc");
		for(ExtendedProcessInstance instance: allInstances){
			if(instance.getId().equals(processInstanceId)) {
				result = instance;
				break;
			}
		}
		return result;
	}
	
}
