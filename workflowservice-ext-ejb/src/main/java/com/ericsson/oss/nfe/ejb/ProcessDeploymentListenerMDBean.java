package com.ericsson.oss.nfe.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.persistence.ProcessTriggerDAO;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity.ProcessTriggerWorkflowEntity;

@MessageDriven(name = "WorkflowEventServiceMDBean", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/wfsInternalDeploymentEventQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1")})
public class ProcessDeploymentListenerMDBean implements MessageListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ProcessTriggerDAO processTriggerDAO;

	/**
	 * Asynchronously receives workflow event messages from a JMS queue.
	 * 
	 * @param message
	 *            JMS message containing work flow event object
	 */
	@Override
	public void onMessage(final Message message) {

		logger.debug("-----------------~~ Message received in ProcessDeploymentListenerMDBean ~~--------------------");
		System.out
				.println("-----------------~~ Message received in ProcessDeploymentListenerMDBean ~~--------------------");
		if (message instanceof TextMessage) {
			
			final TextMessage objMessage = (TextMessage) message;			

			try {
				
				System.out.println("Text message : "+objMessage.getText());
				
				String jsonStr = objMessage.getText();
				
				JSONObject compositeJSONObj = (JSONObject)JSONValue.parse(jsonStr);
				
				if(compositeJSONObj!=null && compositeJSONObj.get("triggers")!=null
						&& compositeJSONObj.get("workflow-key")!=null)
				{				
					
					JSONArray triggers = (JSONArray)compositeJSONObj.get("triggers");
					
					for(Object itr:triggers)
					{
						JSONObject trigger = (JSONObject)itr;
						ProcessTriggerDefinitionEntity processtriggerEntity = new ProcessTriggerDefinitionEntity();

						processtriggerEntity.setTriggerName((String)trigger.get("triggerName"));
						processtriggerEntity.setTriggerType((String)trigger.get("triggerType"));
						processtriggerEntity.setFilterString((String)trigger.get("filterString"));
						
						ProcessTriggerWorkflowEntity pt1 = new ProcessTriggerWorkflowEntity((String)compositeJSONObj.get("workflow-key"));
 						
						processtriggerEntity.addMappedWorkFlows(pt1);						
						processTriggerDAO.createProcessTriggerDefinition(processtriggerEntity);					
					
						
					}
				}
			 
				/*ProcessTriggerEntity trigger = new ProcessTriggerEntity();

				trigger.setTriggerName("testTrigger1");
				trigger.setTriggerType("testTriggerTyp");
				
				ProcessTriggerWorkflowEntity pt1 = new ProcessTriggerWorkflowEntity("test1");
				pt1.setTrigger(trigger);
				
				ProcessTriggerWorkflowEntity pt2 = new ProcessTriggerWorkflowEntity("test2");
				pt2.setTrigger(trigger);
				
				
				trigger.addMappedWorkFlows(pt1);
				trigger.addMappedWorkFlows(pt2);

				processTriggerDAO.creatProcessTrigger(trigger);*/

			} catch (Exception e) {
				 
				e.printStackTrace();
			}

		}

	}

}
