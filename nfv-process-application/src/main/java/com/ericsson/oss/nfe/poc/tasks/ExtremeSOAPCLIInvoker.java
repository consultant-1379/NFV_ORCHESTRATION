package com.ericsson.oss.nfe.poc.tasks;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.javax.el.ExpressionFactory;
import org.camunda.bpm.engine.impl.javax.el.ValueExpression;
import org.camunda.bpm.engine.impl.juel.SimpleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.utils.FileUtils;
import com.ericsson.oss.nfe.poc.utils.SOAPUtil;
import com.ericsson.oss.nfe.poc.utils.vo.HeaderTupple;

public class ExtremeSOAPCLIInvoker implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private Expression cliCommand;
	
	private Expression commandOutPutVariable;
	
	private static int reqCounter = 1;

	public void execute(DelegateExecution execution) {

		log.info("------------------------------ExtremeSOAPCLIInvoker task started ----------------- ");

		invokeExtremeCLIOverSOAP(execution);

		log.info("Variables" + execution.getVariables());

	}

	private void invokeExtremeCLIOverSOAP(DelegateExecution execution) {

		
		String outputVarName = "exCliOut";
		
		if(commandOutPutVariable!=null && commandOutPutVariable.getExpressionText()!=null)
			outputVarName = commandOutPutVariable.getExpressionText();
		
		String soapResponse = "";
		
		StringBuilder cliOutput = new StringBuilder();		
		String cliCommandStr = "";
		
		if(!isValidExpression(cliCommand,execution))
			throw new BpmnError(ApplicationConstants.BPMN_BUSINESS_ERROR,"Extreme Command field not set !");
		
		cliCommandStr = (String)cliCommand.getValue(execution);
		
		String [] cliCommandList = null;
		
		if(cliCommandStr!=null & cliCommandStr.contains(";"))
			cliCommandList=	cliCommandStr.split(";");
		else
			cliCommandList=new String[]{cliCommandStr}; 
		 	
		if(execution.getVariable("extremeSessionId")==null)
			throw new BpmnError(ApplicationConstants.BPMN_BUSINESS_ERROR,"Not authenticated to Extreme");
		 
		try {

			String xmlCliTemplate = FileUtils.loadStreamAsString(FileUtils
					.loadFileFromAppConfig("extremeCliTemplate.xml"));
			
			for(String cmd:cliCommandList)
			{
				cliOutput.append(runExtremeCLI(execution,cmd,xmlCliTemplate));
			} 

		} catch (Throwable e) {
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR,
					"Error in SOAP  Authenticate:" + e);
		}
		
		/*//If the CLI output fails
		if(StringUtils.isEmpty(cliOutput))
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR,"CLI execution failed");*/
		
		//Append to to the current CLI Out variable... used to club outputs from multiple CLI's
		
		String currentOp = (String)execution.getVariable(outputVarName);
		
		if(StringUtils.isEmpty(currentOp))
			currentOp ="";
		
		currentOp +=cliOutput.toString();
		
		execution.setVariable(outputVarName, currentOp);
	}

	private String runExtremeCLI(DelegateExecution execution,String cliCommandStr,String xmlCliTemplate) throws Exception {
		
		String soapResponse;
		String fullyBuiltCommandXML = "";		

		String extremeSessionID = ""; 	
		extremeSessionID =execution.getVariable("extremeSessionId").toString();
		String cliOutput= null;
		
		Map<String, String> propsMap = (Map<String, String>)execution.getVariable("appConfigProps");
		
		String endpointURL = (String)propsMap.get("extreme.soapurl");
 
		if (xmlCliTemplate != null) {
			ExpressionFactory factory = ExpressionFactory.newInstance();

			SimpleContext context = new SimpleContext(); 
 
			context.setVariable("commandPlcholder", factory.createValueExpression(cliCommandStr, String.class));
			
			context.setVariable("reqCounter", factory.createValueExpression(reqCounter, String.class));
			
			context.setVariable("sessionID", factory.createValueExpression(extremeSessionID, String.class));

			ValueExpression e = factory.createValueExpression(context, xmlCliTemplate, String.class);
			
			fullyBuiltCommandXML = e.getValue(context).toString();
		}
		
		soapResponse = new SOAPUtil().doPOSTRequest(fullyBuiltCommandXML, endpointURL,
				new ArrayList<HeaderTupple>());

		if (!StringUtils.isEmpty(soapResponse)) {
			
			log.info("SOAP Command Successful");
			
			cliOutput = SOAPUtil.extractXPath(
							"/SOAP-ENV:Envelope/SOAP-ENV:Body/xoscfg:execCliResponse",
							soapResponse);
			
			reqCounter++;

			log.info("SOAP CLI Output extracted is :"+cliOutput);
			
		}
		return cliOutput;
	}
	
	private boolean isValidExpression(Expression expr, DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr.getExpressionText() != null));
	}

}