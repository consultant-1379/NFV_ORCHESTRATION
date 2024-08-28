package com.ericsson.oss.nfe.ejb;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.ejb.service.WorkflowServiceHelper;
import com.ericsson.oss.nfe.persistence.ProcessTriggerDAO;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity.ProcessTriggerWorkflowEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerEventEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerEventEntity.TriggeredWorkflowEntity;
import com.ericsson.oss.nfe.persistence.entity.TriggerStatus;

@MessageDriven(name = "TriggerIncidentMDBean", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/wfsTiggerEventQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class TriggerIncidentListenerMDBean implements MessageListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ProcessTriggerDAO processTriggerDAO;
	
	@Inject
	private WorkflowServiceHelper workflowhelper;
	
	//@EJB(lookup="ejb:wfs-jee-ear-2.1.39/wfs-jee-ejb-2.1.39/WorkflowInstanceServiceRemoteBean!com.ericsson.oss.services.wfs.jee.api.WorkflowInstanceServiceRemote")
	//private WorkflowInstanceServiceRemote workflowInstanceService;

	
 	/**
	 * Asynchronously receives workflow event messages from a JMS queue.
	 * 
	 * @param message
	 *            JMS message containing work flow event object
	 */
	@Override
	public void onMessage(final Message message) {

		logger.debug("-----------------~~ Message received in TriggerIncidentListenerMDBean ~~--------------------" + message.toString());
		System.out
				.println("-----------------~~ Message received in TriggerIncidentListenerMDBean ~~--------------------" + message);
		if (message instanceof TextMessage) {
			
			final TextMessage objMessage = (TextMessage) message;			

			try { 
				
				String jsonStr = objMessage.getText();
				
				handleMessage(jsonStr);
			  
			} catch (Exception e) {
				 logger.error("Error processing message : "+objMessage+ " e : "+e);
				e.printStackTrace();
			}

		}

	}
	
	private void handleMessage(String jsonStr)
	{
		
		ProcessTriggerEventEntity triggerEvent = new ProcessTriggerEventEntity();
		
		//get the workflows for a given Trigger
		JSONObject compositeJSONObj = (JSONObject)JSONValue.parse(jsonStr);
		logger.info(" handleMessage "+jsonStr);
		if(compositeJSONObj!=null && compositeJSONObj.get("triggerName")!=null
				&& compositeJSONObj.get("filterString")!=null)
		{
			String triggerName = compositeJSONObj.get("triggerName").toString();
			String triggerType = compositeJSONObj.get("triggerType").toString();
			String filterString = compositeJSONObj.get("filterString").toString();
			String externaleventid = (String)compositeJSONObj.get("externaleventid");
			
			triggerEvent.setOccuredOn(new Date());
			triggerEvent.setTriggerName(triggerName);
			triggerEvent.setTriggerType(triggerType);
			triggerEvent.setFilterString(filterString);
			triggerEvent.setExternalEventId(externaleventid);
			
			 Map<String,String> variablesMap = new HashMap<String,String>();

		      JSONObject alarmParams = (JSONObject)compositeJSONObj.get("alarmParams");

		      for (Iterator itr = alarmParams.keySet().iterator(); itr.hasNext(); ) 
		      { Object key = itr.next();

		      	if(alarmParams.get(key)!=null)
		      		variablesMap.put(key.toString(), alarmParams.get(key).toString());
		      }

			
			List<ProcessTriggerWorkflowEntity> mappedWorkFlows = null;
			try {
				
				mappedWorkFlows = processTriggerDAO.
						getMappedWorkFlowDefinitions(null, triggerType, filterString);
				
			} catch (Exception ignore) {}
			
			logger.info(" Mapped workflow for the trigger : "+mappedWorkFlows);
			
			if(mappedWorkFlows!=null && !mappedWorkFlows.isEmpty())
			{
				for(ProcessTriggerWorkflowEntity workFlow :mappedWorkFlows)
				{
						TriggeredWorkflowEntity triggeredFlow= doWorkflowInovcation(workFlow.getWorkflowKey(),variablesMap);
						if(triggeredFlow!=null)
						{
							triggerEvent.addTriggeredWorkFlow(triggeredFlow);
						}
				}
				
				if(triggerEvent.getTriggeredWorkFlows()!=null &&
						!triggerEvent.getTriggeredWorkFlows().isEmpty())
					triggerEvent.setStatus(TriggerStatus.SUCCESS.name());
				else
					triggerEvent.setStatus(TriggerStatus.FAILED.name());

			}
			else
				triggerEvent.setStatus(TriggerStatus.NO_WORKFLOWS.name());			
			 
		}
		
		//Persist the trigger audit
		try {
			processTriggerDAO.createProcessTriggerEventEntity(triggerEvent);
		}catch (Throwable e) {
			logger.error("Error presting in createProcessTriggerEventEntity " + e);
		}		
		
	}
	
	 private ProcessTriggerEventEntity.TriggeredWorkflowEntity doWorkflowInovcation(String workflowKey, Map<String, String> variablesMap)
	  {
	    ProcessTriggerEventEntity.TriggeredWorkflowEntity triggeredEntity = null;
	    try
	    {
	      this.logger.debug("Workflow definition workflowKey returned : " + workflowKey);

	      String instanceId = null;

	      if ((workflowKey != null) && (workflowKey.trim().length() > 0))
	      {
	        instanceId = this.workflowhelper.startWorkflowByKey(workflowKey, variablesMap);

	        this.logger.debug("workflowhelper.startWorkflowByKey  completed with: " + instanceId);

	        if (instanceId != null) {
	          triggeredEntity = new ProcessTriggerEventEntity.TriggeredWorkflowEntity(workflowKey);
	          triggeredEntity.setWorkInstanceId(instanceId);
	        }
	      }

	    }
	    catch (Throwable e)
	    {
	      this.logger.error("Error doWorkflowInovcation for key : " + workflowKey + " e : " + e);
	    }

	    return triggeredEntity;
	  }

}
