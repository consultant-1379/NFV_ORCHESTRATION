/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------
 */
package com.ericsson.oss.nfe.poc.tasks.ssh;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import org.camunda.bpm.engine.delegate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.utils.Utils;
import com.maverick.ssh.*;
import com.sshtools.net.SocketTransport;

public class MMENetconfServiceTask implements JavaDelegate {
	private static int rpc_message_id = 100;
	private final Logger log = LoggerFactory.getLogger(getClass());

	public Expression netconfHost;

	public Expression netconfPortNumber;

	public Expression netconfUser;

	public Expression netconfPassword;

	public Expression command;

	public Expression netconfFilter;

	private SshClient sshClient;
	private SshSession sshSession;
	private SocketTransport socketTransport;
	private Pattern commandPrompt = Pattern.compile("]]>]]>", Pattern.DOTALL);;
	private InputStream stdOut;
	private OutputStream stdIn;
	private String userName;
	private String password;
	private String host;
	private String commandStr;
	private String filterStr = "";
	private int port;

	public void execute(DelegateExecution execution) throws Exception {
		if (licenseCheck()) {
			if (expressionCheck(execution)) {
				openSession();
				String commandNetconf = buildNetconfCommand(commandStr);
				String result = executeCommand(commandNetconf);

				log.info("Netconf command to be executed -->\n"
						+ commandNetconf);
				log.info("Netconf executed and output -->\n" + result);

				String commandNetconf2 = buildNetconfCommand(commandStr);
				String result2 = executeCommand(commandNetconf);

				log.info("Netconf command to be executed -->\n"
						+ commandNetconf2);
				log.info("Netconf executed and output -->\n" + result2);

				execution.setVariable("netconfOutput", result2);
				execution.setVariable("success", checkSuccess(result2));
				closeSession();

			} else {
				execution.setVariable("success", Boolean.FALSE);
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR,
						"Netconf Connection details are incomplete");
			}
		} else {
			execution.setVariable("success", Boolean.FALSE);
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR,
					"Maverick Licensing is incorrect");
		}
	}

	public void openSession() throws Exception {

		log.info("Netconf Service TASK to Host :" + host + " User:" + userName
				+ " on Port :" + port + " using Password :" + password);

		socketTransport = new SocketTransport(host, port);
		this.socketTransport.setSoTimeout(2000);

		this.sshClient = SshConnector.getInstance().connect(socketTransport,
				userName, false);

		int authenticationState = -1;
		final PasswordAuthentication pwd = new PasswordAuthentication();
		pwd.setPassword(password);
		authenticationState = this.sshClient.authenticate(pwd); // User
																// authentication

		switch (authenticationState) {
		case SshAuthentication.COMPLETE: {
			this.sshSession = this.sshClient.openSessionChannel();
			sshSession.executeCommand("/usr/bin/mmi");
			stdIn = this.sshSession.getOutputStream();
			stdOut = this.sshSession.getInputStream();
			break;
		}
		default: {
			throw new Exception("Authentication failed - Error code: "
					+ authenticationState);
		}
		}
	}

	public final String executeCommand(final String command) throws Exception {
		sendCommand(command);
		final String outputCmd = readOutputCommand(this.commandPrompt);
		return outputCmd;
	}

	protected final void sendCommand(final String command) throws Exception {
		this.stdIn.write(command.concat("\n").getBytes());
		this.stdIn.flush();
	}

	// reads input until closing "]]>]]>" is found
	protected final String readOutputCommand(final Pattern expectedPattern)
			throws Exception {
		final StringBuilder output = new StringBuilder();
		final byte[] buffer = new byte[1024];
		int readBytesNum = 0;
		StringBuilder matchString = new StringBuilder();
		while (true) {
			readBytesNum = this.stdOut.read(buffer);
			if (readBytesNum == -1) {
				throw new Exception("The stdout stream is closed - output: \""
						+ output + "\"" + output.toString());
			}
			String currentChunk = new String(buffer, 0, readBytesNum,
					StandardCharsets.UTF_8);
			output.append(currentChunk);

			matchString.append(currentChunk);

			// If the expected string is found in stdout buffer 
			if (findPattern(matchString, expectedPattern)) {
				return output.toString();
			}
			matchString.setLength(0);
			matchString.append(currentChunk);
		}

	}

	protected final boolean findPattern(final StringBuilder commadOutput,
			final Pattern pattern) {
		return pattern.matcher(commadOutput).find();
	}

	private String buildNetconfCommand(String command) {
		return "<rpc message-id=\"" + rpc_message_id
				+ "\" xmlns=\"urn:ietf:params:xml:ns:netconf:base:1.0\">"
				+ System.getProperty("line.separator") + "<" + command + ">"
				+ System.getProperty("line.separator") + filterStr
				+ System.getProperty("line.separator") + "</" + command + ">"
				+ System.getProperty("line.separator") + "</rpc>"
				+ System.getProperty("line.separator") + "]]>]]>";
		// <rpc message-id="100"
		// xmlns="urn:ietf:params:xml:ns:netconf:base:1.0"><get/></rpc>]]>]]>
		// <rpc message-id="100"
		// xmlns="urn:ietf:params:xml:ns:netconf:base:1.0"><pwd/></rpc>]]>]]>
	}

	public final void closeSession() {
		try {
			if (this.sshSession != null) {
				this.sshSession.close();
			}
			if (this.sshClient != null) {
				this.sshClient.disconnect();
			}
			if (this.socketTransport != null) {
				this.socketTransport.close();
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	// Utility methods
	private boolean licenseCheck() {
		try {

			final String fileName = "/maverick_license.txt";
			final BufferedReader bufferReader = new BufferedReader(
					new InputStreamReader(
							MMENetconfServiceTask.class
									.getResourceAsStream(fileName)));
			final StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = bufferReader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append("\r\n");
			}
			System.out.println("license String" + stringBuilder.toString());
			com.maverick.ssh.LicenseManager
					.addLicense(stringBuilder.toString());
			return true;
		} catch (final Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}

	private boolean expressionCheck(DelegateExecution execution) {
		if (isValidExpression(netconfHost, execution)
				&& isValidExpression(netconfPortNumber, execution)
				&& isValidExpression(netconfUser, execution)
				&& isValidExpression(netconfPassword, execution)
				&& isValidExpression(command, execution)) {

			port = Utils.toInt(
					netconfPortNumber.getValue(execution).toString(), 22);

			userName = netconfUser.getValue(execution).toString().trim();
			password = netconfPassword.getValue(execution).toString().trim();
			host = netconfHost.getValue(execution).toString().trim();
			commandStr = command.getValue(execution).toString().trim();
			if (isValidExpression(netconfFilter, execution)) {
				filterStr = netconfFilter.getValue(execution).toString().trim();
			}
			return true;
		}
		return false;
	}

	private boolean isValidExpression(Expression expr,
			DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr
				.getExpressionText() != null));
	}

	public Boolean checkSuccess(String result) {
		if (result != null && !(result.contains("<rpc-error>"))) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

}


 /* public static String HELLO =
 * "<?xml version=\"1.0\" encoding=\"UTF-8\"?><hello xmlns=\"urn:ietf:params:xml:ns:netconf:base:1.0\">"
 * + System.getProperty("line.separator") + "<capabilities>" +
 * System.getProperty("line.separator") + "<capability>" +
 * System.getProperty("line.separator") + "urn:ietf:params:netconf:base:1.0" +
 * System.getProperty("line.separator") + "</capability>" +
 * System.getProperty("line.separator") + "</capabilities>" +
 * System.getProperty("line.separator") + "</hello>" +
 * System.getProperty("line.separator") + "]]>]]>";
 * 
 * public static String CLOSE =
 * "<?xml version=\"1.0\" encoding=\"UTF-8\"?><rpc message-id=\""
 * +rpc_message_id+"\" xmlns=\"urn:ietf:params:xml:ns:netconf:base:1.0\">" +
 * System.getProperty("line.separator") + "<close-session/>" +
 * System.getProperty("line.separator") + "</rpc>" +
 * System.getProperty("line.separator") + "]]>]]>";
 * 
 * public static String GET =
 * "<?xml version=\"1.0\" encoding=\"UTF-8\"?><rpc message-id=\""
 * +rpc_message_id+"\" xmlns=\"urn:ietf:params:xml:ns:netconf:base:1.0\">" +
 * System.getProperty("line.separator") + "<get/>" +
 * System.getProperty("line.separator") + "</rpc>" +
 * System.getProperty("line.separator") + "]]>]]>";
 * 
 * public static String getFilter(String id){ String filter =
 * "<rpc message-id=\""
 * +rpc_message_id+"\" xmlns=\"urn:ietf:params:xml:ns:netconf:base:1.0\">"+
 * "<get>           <filter type=\"subtree\"><ManagedElement xmlns=\"urn:com:ericsson:ecim:SgsnMmeTop\"><managedElementId>"
 * + id +
 * "</managedElementId><SystemFunctions xmlns=\"urn:com:ericsson:ecim:SgsnMmeTop\"><systemFunctionsId>1</systemFunctionsId><SwInventory xmlns=\"urn:com:ericsson:ecim:SgsnMmeSwIM\"><swInventoryId>1</swInventoryId></SwInventory></SystemFunctions></ManagedElement></filter> </get>"
 * + "</rpc>" + "]]>]]>"; return filter; }
 
*/