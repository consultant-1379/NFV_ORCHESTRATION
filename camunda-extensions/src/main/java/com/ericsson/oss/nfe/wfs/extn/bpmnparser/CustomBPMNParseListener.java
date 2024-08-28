package com.ericsson.oss.nfe.wfs.extn.bpmnparser;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.util.xml.Element;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.wfs.extn.JMSUtil;

public class CustomBPMNParseListener extends AbstractBpmnParseListener {

	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    private static final String DEPLOYMENT_QUEUE = "/queue/wfsInternalDeploymentEventQueue";
    //"java:jms/queue/wfsInternalDeploymentEventQueue";
    
    public CustomBPMNParseListener(){
    	super();
    	logger.debug("CustomBPMNParseListener constructor");
    }

	
	@Override
	public void parseProcess(Element processElement,
			ProcessDefinitionEntity processDefinition) {
		 
		 
		logger.debug("CustomBPMNParseListener : parseProcess started on process deployment");

		super.parseProcess(processElement, processDefinition);

		Element extensionElement = processElement.element("extensionElements");
		
		logger.debug(" extensionElement : "+extensionElement);

		if (extensionElement != null && extensionElement
				.element("processTriggers")!=null) {

			logger.debug("CustomBPMNParseListener triggers:"+extensionElement.element("processTriggers")
					.elements("trigger").get(0).attribute("triggerType"));
			 
			List<Element> triggers = extensionElement
					.element("processTriggers").elements();
			
			JSONArray  triggersJsonArray = new JSONArray ();
			
			JSONObject  parentObj = new JSONObject();			
			
			if (triggers != null) {

				for (Element trigger : triggers) {

					JSONObject  triggerJson = new JSONObject();					
					
					String name = trigger.element("triggerName")!=null ? trigger.element("triggerName").getText():"";
					
					String filterStr = trigger.element("filterString")!=null ? trigger.element("filterString").getText():"";
					String triggerType = trigger.attribute("triggerType");
					
					if(StringUtils.isEmpty(name) || StringUtils.isEmpty(triggerType))
						continue;
					
					triggerJson.put("triggerName", name);
					triggerJson.put("triggerType", triggerType);
					triggerJson.put("filterString", filterStr);

					logger.debug("name : " + name + " triggerType: "+ triggerType);
					
					logger.debug("Triggername : " + name + " triggerType: "+ triggerType + " filterString: "+ filterStr);
					triggersJsonArray.add(triggerJson); 
				}
				
				parentObj.put("triggers", triggersJsonArray);
				parentObj.put("workflow-key", processElement.attribute("id"));				
				
				//Finally publish on to the Queue
				try {
					if(triggersJsonArray!=null && triggersJsonArray.size()>0)						
					logger.debug(parentObj.toString()); 
					new JMSUtil().sendMessage(DEPLOYMENT_QUEUE, parentObj.toJSONString());
				} catch (Throwable ex) {
					ex.printStackTrace();
					 //Catch throwable cause in any case the failure to register
					// the Triggers should not stop the deployment
					logger.error("Registering a deployment with tiggers failed !!"+ ex.getMessage());
				}

			}
		}

		logger.debug("Parsing the process deployment");
	}

}
 
