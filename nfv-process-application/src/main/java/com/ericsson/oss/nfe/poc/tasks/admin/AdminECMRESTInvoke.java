package com.ericsson.oss.nfe.poc.tasks.admin;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.ECMConstants;
import com.ericsson.oss.nfe.poc.utils.ECMRESTUtil;
import com.ericsson.oss.nfe.poc.utils.vo.ResponseVO;

/** This task is used for tenant creation, using ECM Admin authentication
 * @author epttwxz
 *
 */
public class AdminECMRESTInvoke implements JavaDelegate {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private Expression method;

	private Expression endpointURL;

	private Expression outPutVariable;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		log.info("------------------------------AdminECMRESTInvoker task started------------------------------");

		doAdminECMRESTCall(execution);

		log.info("Variables" + execution.getVariables());

		log.info("------------------------------AdminECMRESTInvoker task ended--------------------------------");

	}

	private void doAdminECMRESTCall(DelegateExecution execution) {
		String httpMethod = method.getValue(execution).toString();

		String endpointURLStr = endpointURL.getValue(execution).toString();

		String respvoStr = "";

		if (ECMConstants.GET_METHOD.equalsIgnoreCase(httpMethod)) {
			log.info(execution.getVariable("createUserRequestBody") + "");
			log.info(execution.getVariable("createTenantRequestBody") + "");

			String tenantOrder = execution.getVariable("createTenantRequestBody")+"";
			
			if(tenantOrder.equals("null")){
				endpointURLStr += execution.getVariable("TenantName")+"/"+execution.getVariable("UserName");
			}
			else{
				endpointURLStr += execution.getVariable("TenantName");
			}
			
			try {
				respvoStr = new ECMRESTUtil(true).doGETRequest(endpointURLStr);
				log.info("AdminECMRESTInvoker response: " + respvoStr);
				// remove the first request body
				execution.removeVariable("createTenantRequestBody");

			} catch (RESTInvokeException e) {
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR,
						"Error in doAdminECMRESTCall method, GET request:" + e);
			}
		}
		if (ECMConstants.POST_METHOD.equalsIgnoreCase(httpMethod)) {
			
			String jsonReqStr = execution
					.getVariable("createTenantRequestBody") + "";
			
			log.info("------>>>>Check the jsonReqStr: " + jsonReqStr);
			if (jsonReqStr.equals("null"))
				jsonReqStr = execution.getVariable("createUserRequestBody")+"";
			else
				execution.removeVariable("createTenantRequestBody");

			try {
				respvoStr = new ECMRESTUtil(true).doPOSTRequest(jsonReqStr,
						endpointURLStr);

			} catch (RESTInvokeException e) {
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR,
						"Error in doAdminECMRESTCall method, POST request:" + e);
			}
		}
	}

	private void tenantExist(String endpointURLStr, DelegateExecution execution) {

		String respvoStr;

		ResponseVO resVO;

		try {
			ECMRESTUtil ecmrestUti = new ECMRESTUtil();

			respvoStr = ecmrestUti.doGETRequest(endpointURLStr);
			execution.setVariable("Tenant Exists", "true");

			execution.setVariable("User created", false);
			// create tenant user
			createUserRESTCall(endpointURLStr, execution);

		} catch (RESTInvokeException e) {

			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR,
					"Error in tenantExist method :" + e);
		}

	}

	private void createTenantRESTCall(String endpointURLStr,
			DelegateExecution execution) {

		String respvoStr = "";
		String requestJSON = "{" + "\"tenantName\":\"lmioss\","
				+ "\"businessAdminContact\" : \"Andrew Fenner\","
				+ "\"technicalAdminContact\" : \"Vignesh Kumar Bandsale\","
				+ "\"tenantAdmin\" : {" + " \"firstName\" : \"lmioss\","
				+ "\"lastName\" : \"lmioss\","
				+ "\"emailAddress\" : \"s.plug@ericsson.com\","
				+ "\"mobilePhone\" : \"9081234567\","
				+ "\"roles\" : [ {\"roleName\" : \"TenantAdmin\"} ],"
				+ "\"password\" : \"lmioss\"," + "\"status\" : \"ACTIVE\","
				+ "\"contact\" : null," + "\"userName\" : \"lmioss\"" + "}"
				+ "}";

		try {
			respvoStr = new ECMRESTUtil(true).doPOSTRequest(requestJSON,
					endpointURLStr);
			// create user in tenant
			createUserRESTCall(endpointURLStr, execution);

		} catch (RESTInvokeException e) {
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR,
					"Error in rest POST:" + e);
		}

		System.out.println("Test output from Tenant creation:  "
				+ execution.getVariable(respvoStr));
	}

	private void createUserRESTCall(String endpointURLStr,
			DelegateExecution execution) {
		endpointURLStr = "http://10.224.23.125:8080/ecm_service/users";

		String respvoStr = "";
		String requestJSON = "{" + "\"tenantName\":\"lmioss\","
				+ "\"userName\": \"lmioss\"," + "\"password\":\"lmioss123\","
				+ "\"roles\": [ {" + "\"roleName\":\"TenantUser\"" + "} ],"
				+ "\"firstName\":\"lmioss\"," + "\"lastName\":\"lmioss\"" + "}";
		try {
			respvoStr = new ECMRESTUtil().doPOSTRequest(requestJSON,
					endpointURLStr);
			execution.setVariable("User created", true);

		} catch (RESTInvokeException e) {
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR,
					"User already created Error:" + e);
		}
	}
}
