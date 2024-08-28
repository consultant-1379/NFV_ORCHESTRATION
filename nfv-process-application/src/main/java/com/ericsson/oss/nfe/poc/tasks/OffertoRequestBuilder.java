package com.ericsson.oss.nfe.poc.tasks;

import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.ECM_NAMEID_MAP;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.JSONTemplateBuilder;
import com.floreysoft.jmte.Engine;

public class OffertoRequestBuilder extends BaseServiceTask {

	private final Logger log = LoggerFactory.getLogger("activity-tracker");
	
	private Expression requestTemplate;
	
	private Expression ipAddressRange;
	
	private Expression vimZoneName;
	
	private Expression vdcName;
 
	private Expression outPutVariable;

	public void execute(DelegateExecution execution) {
		
		log.info("Starting OffertoRequestBuilder task with Params : "+buildTaskParamsLog(execution, new Expression[]{vdcName,ipAddressRange,requestTemplate}));
				 
		String inputJson = requestTemplate.getValue(execution).toString();
		String requestJSONTemplate = null;
		log.debug("inputJson : "+inputJson);
		try {
			 requestJSONTemplate = JSONTemplateBuilder.buildTemplateResponse(inputJson);
			
		} catch (RESTInvokeException e) {
			log.error("Exeception building template");
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Exeception building template:"+e);
		}
		
		log.debug("requestJSONTemplate : "+requestJSONTemplate);
		
		//Get the map of VDC,VNs,VMS etc populated from previous get
		Map<String, String> ecmProcessIDMap = (Map<String, String>) execution
				.getVariable(ECM_NAMEID_MAP);
		
		ecmProcessIDMap = (ecmProcessIDMap!=null?ecmProcessIDMap:new HashMap<String, String>());
		
		ecmProcessIDMap.put("tor-user-id", super.getTORUseId(execution));
		
		
		if(isValidExpression(ipAddressRange, execution)) {
			String ipAddressRangeStr = ipAddressRange.getValue(execution).toString();
			ecmProcessIDMap.put("ipAddressRange", ipAddressRangeStr);
			
		}
		
		if(isValidExpression(vimZoneName, execution)) {
			String vimZoneStr = vimZoneName.getValue(execution).toString();
			ecmProcessIDMap.put("vimZoneName", vimZoneStr);
			
		}
		
		if(isValidExpression(vdcName,execution))
		{
			String vdcNameStr =  vdcName.getValue(execution).toString();
			ecmProcessIDMap.put(ApplicationConstants.VDC_NAME_PLACHEHOLDER, vdcNameStr);
		}
		else
		{
			ecmProcessIDMap.put(ApplicationConstants.VDC_NAME_PLACHEHOLDER,"EPG-VDC");
		}
		
		
		String filledReq = null;
		if(requestJSONTemplate!=null)
			filledReq = buildAndPopulateTemplates(requestJSONTemplate,ecmProcessIDMap);
 
 
		log.debug("filledReq : "+filledReq);
		
		String outputVarName = outPutVariable.getExpressionText();
		
		execution.setVariable(outputVarName, filledReq); 
 
		log.info("Ending OffertoRequestBuilder task with outputVarName : "+filledReq);

	}
	
	
	private String buildAndPopulateTemplates(String jsonTemplate,Map<String, String> execVars) 
	{
		Engine engine = new Engine();		 	 
		Map<String, Object> tokens = new HashMap<String, Object>();
		
		Map<String, String> ecmPropsMaps =AppConfigLoader.getECMPropsMap();
		
 		for(Map.Entry<String, String> entry:ecmPropsMaps.entrySet())
			tokens.put(entry.getKey(), entry.getValue());		
		
 		if(execVars!=null)
			tokens.putAll(execVars);
		
 		String transformed = engine.transform(jsonTemplate, tokens);
 		
		return transformed;
	}
	
	private boolean isValidExpression(Expression expr, DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr.getExpressionText() != null));
	}
}