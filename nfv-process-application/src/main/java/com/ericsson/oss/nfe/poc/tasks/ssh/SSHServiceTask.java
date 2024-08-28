package com.ericsson.oss.nfe.poc.tasks.ssh;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.utils.SecureShellConnectionUtil;
import com.ericsson.oss.nfe.poc.utils.Utils;

public class SSHServiceTask implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public Expression sshHost;

	public Expression sshPortNumber;

	public Expression sshUser;

	public Expression sshPassword;

	public Expression command;

	SecureShellConnectionUtil connection;

	public void execute(DelegateExecution execution) throws Exception {

		if (isValidExpression(sshHost, execution) &&
				isValidExpression(sshPortNumber, execution) &&
				isValidExpression(sshUser, execution) &&
				isValidExpression(sshPassword, execution) &&
				isValidExpression(command, execution)) {
			
			//String knownHost = AppConfigLoader.getProperty("known_hosts");
			int port = Utils.toInt(sshPortNumber.getValue(execution).toString(), 22);
			
			String userName =sshUser.getValue(execution).toString().trim();
			String pasword = sshPassword.getValue(execution).toString().trim();
			String host = sshHost.getValue(execution).toString().trim();
			
			log.debug("SSH Service TASK SSH to Host :"+host+" User:"+userName+"on Port :"+port + "using Password :"+pasword);

			connection = new SecureShellConnectionUtil(userName,pasword,host,port);

			String commandStr = command.getValue(execution).toString().trim();// .replace("ndash;", "");
			
			log.info("SSh command to be executed -->" + commandStr);
			
			String result = null;
			try {
				result = connection.sendCommand(commandStr); 
 			}
			finally {
				connection.disconnect();
			}
			log.info("SSh executed and output -->" + result);

			
			
			execution.setVariable("sshoutPut", result);
			execution.setVariable("success", checkSuccess(result));

		}
		else {
			execution.setVariable("success", Boolean.FALSE);
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "SSH Connection details are incomplete");

		}
	}

	@Deprecated
	public String connectionStatus() {
		if (connection.isConnected()) {
			return "connected";
		}
		else {
			return "not-connected";
		}
	}

	public Boolean checkSuccess(String result) {
		if (result !=null && result.contains("No Errors Reported.")) {
			return Boolean.TRUE;
		}
		else {
			return Boolean.FALSE;
		}
	}

	private boolean isValidExpression(Expression expr, DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr.getExpressionText() != null));
	}
}
