package com.ericsson.oss.nfe.poc.tasks;

import java.util.ArrayList;
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
import com.ericsson.oss.nfe.poc.utils.FileUtils;
import com.ericsson.oss.nfe.poc.utils.SOAPUtil;
import com.ericsson.oss.nfe.poc.utils.vo.HeaderTupple;

public class ExtremeSOAPAuthenticator implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private Expression outPutVariable;

	public void execute(DelegateExecution execution) {

		log.info("------------------------------ExtremeSOAPAuthenticator task started ----------------- ");

		doAuthenticateCall(execution);

		log.info("Variables" + execution.getVariables());

	}

	private void doAuthenticateCall(DelegateExecution execution) {
 
		String soapResponse = "";
		String sessionId = "";
		
		Map<String, String> propsMap = (Map<String, String>)execution.getVariable("appConfigProps");
		
		String endpointURL = (String)propsMap.get("extreme.soapurl");

		try {

			String xmlAuthRequest = FileUtils.loadStreamAsString(FileUtils
					.loadFileFromAppConfig("extremeauth.xml"));
			
			soapResponse = new SOAPUtil().doPOSTRequest(xmlAuthRequest, endpointURL,
					new ArrayList<HeaderTupple>());

			if (!StringUtils.isEmpty(soapResponse)) {
				
				log.info("SOAP Authenticate Successful");
				
				sessionId = SOAPUtil.extractXPath(
								"/SOAP-ENV:Envelope/SOAP-ENV:Body/com:openSessionReply/session/sessionId",
								soapResponse);

				log.info("SOAP sessionId extracted is :"+sessionId);
				
				execution.setVariable("extremeSessionId", sessionId);
			}

		} catch (Throwable e) {
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR,
					"Error in SOAP  Authenticate:" + e);
		}
		
		if(isValidExpression(outPutVariable,execution))
				execution.setVariable(outPutVariable.getExpressionText(), sessionId);
	}
	private boolean isValidExpression(Expression expr, DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr.getExpressionText() != null));
	}
}