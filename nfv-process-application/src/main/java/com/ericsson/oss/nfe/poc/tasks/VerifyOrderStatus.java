package com.ericsson.oss.nfe.poc.tasks;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.ECMRESTUtil;
import com.floreysoft.jmte.Engine;
import com.jayway.jsonpath.JsonPath;

public class VerifyOrderStatus  extends BaseServiceTask {

	private final Logger log = LoggerFactory.getLogger("activity-tracker");
	
	private Expression inputcreateOrderResponse;

	private Expression outPutVariable;

	public void execute(DelegateExecution execution) {

 
		log.info("Starting VerifyOrderStatus task with Params : "+buildTaskParamsLog(execution, new Expression[]{inputcreateOrderResponse}));
		
		String tempstring = (String) inputcreateOrderResponse.getValue(execution);

		String[] listoforders = tempstring.split(";");
		
		String orderStatus = null;
		String allorderStatus = "";
		for (int ordernumber = 0;ordernumber<listoforders.length;ordernumber++)
		{
			String inputJsonResponse = listoforders[ordernumber];
		
		 
			if (inputJsonResponse != null && !StringUtils.isEmpty(inputJsonResponse)) {

				String orderId = JsonPath.read(inputJsonResponse, "$.order.id");

				if (StringUtils.isEmpty(orderId))
					throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Error processing order no order id created");

				execution.setVariable("orderId", orderId);

				String baseECMUrl = AppConfigLoader.getProperty("BASE_ECM_URL");
				if(baseECMUrl == null || baseECMUrl.equalsIgnoreCase("")){
					throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "BASE_ECM_URL has to be defined.!");
				}
				if(baseECMUrl.endsWith("/")){
					baseECMUrl = baseECMUrl.substring(0,baseECMUrl.length()-1);
				}
				// Now get the orders
				String getOrderurl = baseECMUrl + "/orders/" + orderId;

				String getOrderResponse = "";
				try {
					getOrderResponse = new ECMRESTUtil().doGETRequest(getOrderurl);
				} catch (RESTInvokeException e) {
				
					throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR);
				}

				/*
				 * orderStatus = JsonPath.read(getOrderResponse.getJsonStr(),
				 * "$.order.provisioningStatus");
				 */

				orderStatus = JsonPath.read(getOrderResponse, "$.order.orderReqStatus");

				log.debug("Successfully extracted order details for id : " + orderId + " status : " + orderStatus);

				execution.setVariable("orderStatus", orderStatus);

				System.out.println(" orderId : " + orderId + " orderStatus : " + orderStatus);
				if (ordernumber!=0)
				{
					allorderStatus = allorderStatus +";" + orderStatus;
				}
				else 
				{
					allorderStatus = orderStatus;
				}
				if("ERR".equalsIgnoreCase(orderStatus))
				{
					throw new BpmnError("ORDER_ERROR", "ORDER_ERROR");
				}
				
				if (!orderStatus.equalsIgnoreCase("COM")) {
					throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Not active yet");
				}
					

			}

		/*} catch (Exception e) {
			log.error("Exeception building template", e);
			// e.printStackTrace();
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR);
		}*/
		}
		if (outPutVariable != null) {

			String outputVarName = outPutVariable.getExpressionText();
			execution.setVariable(outputVarName, allorderStatus);

		}

		log.info("Ending VerifyOrderStatus task with orderStatus : "+allorderStatus);
		 
	}

	private String buildAndPopulateTemplates(String jsonTemplate, Map<String, Object> execVars) {
		Engine engine = new Engine();
		Map<String, Object> tokens = new HashMap<String, Object>();
		tokens.putAll(execVars);

		Map<String, String> ecmPropsMaps = AppConfigLoader.getECMPropsMap();


		for (Map.Entry<String, String> entry : ecmPropsMaps.entrySet())
			tokens.put(entry.getKey(), entry.getValue());

		String transformed = engine.transform(jsonTemplate, tokens);

		return transformed;
	}
}