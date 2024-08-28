package com.ericsson.oss.nfe.poc.tasks.ssh;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.utils.FileUtils;
import com.ericsson.oss.nfe.poc.utils.SecureShellConnectionUtil;
import com.ericsson.oss.nfe.poc.utils.Utils;

public class FTPgetServiceTask implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public Expression remoteFilename;
	
	public Expression remoteFilepath;

	public Expression sshHost;

	public Expression sshPortNumber;

	public Expression sshUser;

	public Expression sshPassword;
	
	public Expression outputWorkFileName;

	SecureShellConnectionUtil connection;
	
	public void execute(DelegateExecution execution) throws Exception {
		
		if (isValidExpression(sshHost, execution) 
				&& isValidExpression(sshPortNumber, execution) 
				&& isValidExpression(sshUser, execution)
				&& isValidExpression(sshPassword, execution) 
				&& isValidExpression(remoteFilename, execution)
				&& isValidExpression(remoteFilepath, execution)) {
			
			int port = Utils.toInt(sshPortNumber.getValue(execution).toString(), 22);

			connection = new SecureShellConnectionUtil(sshUser.getValue(execution).toString(), sshPassword.getValue(execution).toString(), sshHost
					.getValue(execution).toString(), port);

			String remoteFilenameStr = (String) remoteFilename.getValue(execution);
			String remoteFilePathStr = (String) remoteFilepath.getValue(execution);

			String result = "";
			try {
				result = connection.getFile(remoteFilenameStr, remoteFilePathStr);
				
				if(isValidExpression(outputWorkFileName,execution))
				{
					String outPutfileNameStr = (String) outputWorkFileName.getValue(execution);
					 
					String appCfgDir = System.getProperty("APPCONFIGDIR", "");
					
					FileUtils.writeFileToDir(appCfgDir+"/"+outPutfileNameStr, result);
				}
			 
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				connection.disconnect();
			}
			log.info("FTP get executed and output --> " + result);

			execution.setVariable("ftpOutput", result);
		}
		else {
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "SSH Connection details are incomplete");

		}
	}

	private boolean isValidExpression(Expression expr, DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr.getExpressionText() != null));
	}
}
