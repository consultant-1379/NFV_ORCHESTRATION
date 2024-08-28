package com.ericsson.oss.nfe.poc.tasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;

public class ENMcommandPOSTRequest implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private Expression command;

	private Expression endpointURL;

	private Expression output;

	private Expression cookie;

	private Expression enmInterface;

	private HttpClient client = new HttpClient();

	public void execute(DelegateExecution execution) {

		log.info("------------------------------ENMupdatePOSTRequest task started ----------------- ");

		String commandStr = "";
		String url = "";
		String outputVarStr = "";
		String setCookie = "";
		String enmInterfaceType = "";
		
		try {
			commandStr = command.getValue(execution).toString();
			url = endpointURL.getValue(execution).toString();
			outputVarStr = output.getValue(execution).toString();
			setCookie = cookie.getValue(execution).toString();
			enmInterfaceType = enmInterface.getValue(execution).toString();

			if (commandStr == null || commandStr.equalsIgnoreCase("")) {
				throw new BpmnError(ApplicationConstants.BPMN_MISSING_PARAM, "command has to be defined!");
			}
			if (url == null || url.equalsIgnoreCase("")) {
				throw new BpmnError(ApplicationConstants.BPMN_MISSING_PARAM, "endpoint url has to be defined!");
			}
			if (outputVarStr == null || outputVarStr.equalsIgnoreCase("")) {
				throw new BpmnError(ApplicationConstants.BPMN_MISSING_PARAM, "output variable has to be defined!");
			}
			if (setCookie == null || setCookie.equalsIgnoreCase("")) {
				throw new BpmnError(ApplicationConstants.BPMN_MISSING_PARAM, "cookie has to be defined!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BpmnError(ApplicationConstants.BPMN_MISSING_PARAM);
		}
		
		String output = "";
		try {
			if (!(enmInterfaceType == null) && (enmInterfaceType.equalsIgnoreCase("collection"))) {
				System.out.println("Processing command towards collection interface");
				if(commandStr.equalsIgnoreCase("GET")) { output=sendGetRequestToCollectionEngineRESTinterface(url,setCookie); }
				else if(!(commandStr.startsWith("{"))) { output=sendGetRequestToCollectionEngineRESTinterface(url,commandStr,setCookie); }
				else {output = sendPostRequestToCollectionEngineRESTinterface(url, commandStr, setCookie);}
			} else if (enmInterfaceType != null && enmInterfaceType.equalsIgnoreCase("script")) {
				output = sendPostRequestToScriptEngineRESTinterface(url, commandStr, setCookie);
			} else if (enmInterfaceType != null && enmInterfaceType.equalsIgnoreCase("managedObject")) {
				output = sendGetRequestToManagedObjectEngineRESTinterface(url, commandStr, setCookie);
			} else if (!(enmInterfaceType == null) && (enmInterfaceType.equalsIgnoreCase("collectionDelete"))){ 
				output = sendDeleteRequestToCollectionEngineRESTinterface(url, commandStr, setCookie);
			}else if (!(enmInterfaceType == null) && (enmInterfaceType.equalsIgnoreCase("alarmAck"))){ 
				output = acknowledgeAlarm(url, commandStr, setCookie);
			}
			else {
				System.out.println("undefined! enmInterfaceType");
			}

			if (output == null){
				log.info("ENM Returned null");
				System.out.println("ENM Returned null");
			}
			else if (output.contains("rolled back")) {
				execution.setVariable(outputVarStr, output);
				log.info("****** Transaction rolled back ********");
				System.out.println("****** Transaction rolled back ********");
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Internal Error Transaction rolled back, auto-retry");
			}
			else if (output.contains("elements\":[],")) {
				execution.setVariable(outputVarStr, output);
				log.info("****** Transaction unsuccessful! ********");
				System.out.println("****** Transaction unsuccessful! ********");
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Internal Error Transaction unsuccessful, auto-retry");
			} else if (output.contains("Error")) {
				execution.setVariable(outputVarStr, output);
				log.info("****** Transaction unsuccessful! ********");
				System.out.println("****** Transaction unsuccessful! ********");
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Internal Error Transaction unsuccessful, auto-retry");
			}
		} finally {
			execution.setVariable(outputVarStr, output);
		}

	}

	private String sendPostRequestToScriptEngineRESTinterface(String url, String commandStr, String setCookie) {
		//String location = "";
		//String jsessionIdPOST = "";
		String result = null;
		PostMethod post = null;
		try {
			log.info("POSTing command: " + commandStr);
			System.out.println("\nPOSTing command: " + commandStr);
			Protocol myhttps = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);

			post = new PostMethod(url);
			Part[] parts = { new StringPart("command", commandStr), };

			post.addRequestHeader("Cookie", setCookie);
			post.addRequestHeader("Accept", "application/json");
			post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));

			client.executeMethod(post);
			result = loadStreamAsString(post.getResponseBodyAsStream());
			
			//location = post.getResponseHeader("Location").getValue();
			//jsessionIdPOST = post.getResponseHeader("Set-Cookie").getValue().split(";")[0];
			

			//log.info("POST Response: " + result + " location URL: " + location);
			log.info("POST Response: " + result);
			System.out.println("POST Response: " + result);// + "location URL: " + location);

		} catch (Exception e) {
			e.printStackTrace();
			Header[] responseHeaders = post.getResponseHeaders();
			for (int i = 0; i < responseHeaders.length; i++) {
				log.info("responseHeaders " + i + " " + responseHeaders[i].getValue() + " " + responseHeaders[i].getName());
			}
		}
		return result;
		//return sendGetRequestToScriptEngineRESTinterface(location, setCookie, jsessionIdPOST);
	}

	private String sendPostRequestToCollectionEngineRESTinterface(String url, String commandStr, String setCookie) {
		String responseBody = "";
		PostMethod post = null;
		try {
			log.info("POSTing command: " + commandStr);
			System.out.println("POSTing command: " + commandStr);
			Protocol myhttps = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);

			post = new PostMethod(url);
			post.setRequestBody(commandStr);

			post.addRequestHeader("Cookie", setCookie);
			post.addRequestHeader("Accept", "text/plain, */*; q=0.01");

			// set headers
			post.addRequestHeader("Content-Type", "application/json; charset=UTF-8");

			client.executeMethod(post);

			responseBody = loadStreamAsString(post.getResponseBodyAsStream());// post.getResponseBodyAsString();
			System.out.println("RESPONSE BODY:" + responseBody);
			
		} catch (Exception e) {
			e.printStackTrace();
			Header[] responseHeaders = post.getResponseHeaders();
			for (int i = 0; i < responseHeaders.length; i++) {
				log.info("responseHeaders " + i + " " + responseHeaders[i].getValue() + " " + responseHeaders[i].getName());
			}
		}
		return responseBody;
	}
	
	private String sendGetRequestToCollectionEngineRESTinterface(String location, String setCookie){
		String result = "";
		try {
			GetMethod get = new GetMethod(location.trim());
			get.addRequestHeader("Cookie", setCookie);
			get.addRequestHeader("Accept", "application/json");

			client.executeMethod(get);
			String jsessionIdGET = "";

			Header[] responseHeaders = get.getResponseHeaders("Set-Cookie");
			for (int i = 0; i < responseHeaders.length; i++) {
				//System.out.println("responseHeaders GET " + i + " --> " + responseHeaders[i].getName() + " " + responseHeaders[i].getValue());
				jsessionIdGET = responseHeaders[i].getValue().split(";")[0];
			}
			// jsessionIdGET =
			// get.getResponseHeader("Set-Cookie").getValue().split(";")[0];

			result = loadStreamAsString(get.getResponseBodyAsStream());

			if (result.contains("name")) {
				log.info("GET response: " + result);
				System.out.println("GET response: " + result);
				return result;
			}

			if (jsessionIdGET != setCookie) {
				// System.out.println("\n***ENM wrong session returned error***\n***Running GET Again***");
				client.executeMethod(get);
			}

			result = loadStreamAsString(get.getResponseBodyAsStream());

			log.info("GET response: " + result);
			System.out.println("GET response: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private String sendGetRequestToCollectionEngineRESTinterface(String location, String command, String setCookie){
		String result = "";
		try {
			GetMethod get = new GetMethod(location.trim()+"/"+command.trim());
			get.addRequestHeader("Cookie", setCookie);
			get.addRequestHeader("Accept", "application/json");

			client.executeMethod(get);
			String jsessionIdGET = "";

			Header[] responseHeaders = get.getResponseHeaders("Set-Cookie");
			for (int i = 0; i < responseHeaders.length; i++) {
				//System.out.println("responseHeaders GET " + i + " --> " + responseHeaders[i].getName() + " " + responseHeaders[i].getValue());
				jsessionIdGET = responseHeaders[i].getValue().split(";")[0];
			}
			// jsessionIdGET =
			// get.getResponseHeader("Set-Cookie").getValue().split(";")[0];

			result = loadStreamAsString(get.getResponseBodyAsStream());

			if (result.contains("name")) {
				log.info("GET response: " + result);
				System.out.println("GET response: " + result);
				return result;
			}

			if (jsessionIdGET != setCookie) {
				// System.out.println("\n***ENM wrong session returned error***\n***Running GET Again***");
				client.executeMethod(get);
			}

			result = loadStreamAsString(get.getResponseBodyAsStream());

			log.info("GET response: " + result);
			System.out.println("GET response: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	private String acknowledgeAlarm(String location, String command, String setCookie){
		String result = "";
		try {
			GetMethod get = new GetMethod(location.trim());
			get.addRequestHeader("Cookie", setCookie);
			get.addRequestHeader("Accept", "application/json");
			
 			client.executeMethod(get);
			String jsessionIdGET = "";

			Header[] responseHeaders = get.getResponseHeaders("Set-Cookie");
			for (int i = 0; i < responseHeaders.length; i++) {
				//System.out.println("responseHeaders GET " + i + " --> " + responseHeaders[i].getName() + " " + responseHeaders[i].getValue());
				jsessionIdGET = responseHeaders[i].getValue().split(";")[0];
			}
			// jsessionIdGET =
			// get.getResponseHeader("Set-Cookie").getValue().split(";")[0];

			result = loadStreamAsString(get.getResponseBodyAsStream());
 
			log.info("acknowledgeAlarm response jsessionIdGET: " + jsessionIdGET + result);
			System.out.println("acknowledgeAlarm GET response: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	private String sendDeleteRequestToCollectionEngineRESTinterface(String location, String command, String setCookie){
		String result = "";
		try {
			DeleteMethod delete = new DeleteMethod(location.trim()+"/"+command.trim());
			delete.addRequestHeader("Cookie", setCookie);
			delete.addRequestHeader("Accept", "application/json");

			client.executeMethod(delete);
			String jsessionIdGET = "";

			Header[] responseHeaders = delete.getResponseHeaders("Set-Cookie");
			for (int i = 0; i < responseHeaders.length; i++) {
				//System.out.println("responseHeaders GET " + i + " --> " + responseHeaders[i].getName() + " " + responseHeaders[i].getValue());
				jsessionIdGET = responseHeaders[i].getValue().split(";")[0];
			}
			// jsessionIdGET =
			// get.getResponseHeader("Set-Cookie").getValue().split(";")[0];

			result = loadStreamAsString(delete.getResponseBodyAsStream());

			if (result.contains("")) {
				log.info("DELETE response: " + result);
				System.out.println("DELETE response: " + result);
				return result;
			}

			if (jsessionIdGET != setCookie) {
				// System.out.println("\n***ENM wrong session returned error***\n***Running GET Again***");
				client.executeMethod(delete);
			}

			result = loadStreamAsString(delete.getResponseBodyAsStream());

			log.info("DELETE response: " + result);
			System.out.println("DELETE response: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	private String sendGetRequestToScriptEngineRESTinterface(String location, String setCookie, String jsessionIdPOST) {
		String result = "";
		GetMethod get = null;
		try {
			get = new GetMethod(location);
			get.addRequestHeader("Cookie", jsessionIdPOST + " ; " + setCookie + " ; ");
			get.addRequestHeader("Accept", "application/json");

			client.executeMethod(get);
			String jsessionIdGET = "";

			Header[] responseHeaders = get.getResponseHeaders("Set-Cookie");
			for (int i = 0; i < responseHeaders.length; i++) {
				//System.out.println("responseHeaders GET " + i + " --> " + responseHeaders[i].getName() + " " + responseHeaders[i].getValue());
				jsessionIdGET = responseHeaders[i].getValue().split(";")[0];
			}
			// jsessionIdGET =
			// get.getResponseHeader("Set-Cookie").getValue().split(";")[0];

			result = loadStreamAsString(get.getResponseBodyAsStream());

			if (result.contains("instance(s) updated")) {
				log.info("GET response: " + result);
				System.out.println("GET response: " + result);
				return result;
			}

			if (jsessionIdGET != jsessionIdPOST) {
				// System.out.println("\n***ENM wrong session returned error***\n***Running GET Again***");
				client.executeMethod(get);
			}

			result = loadStreamAsString(get.getResponseBodyAsStream());

			log.info("GET response: " + result);
			System.out.println("GET response: " + result);
		} catch (Exception e) {
			e.printStackTrace();
			Header[] responseHeaders = get.getResponseHeaders();
			for (int i = 0; i < responseHeaders.length; i++) {
				log.info("responseHeaders " + i + " " + responseHeaders[i].getValue() + " " + responseHeaders[i].getName());
			}
		}

		return result;
	}
	
	private String sendGetRequestToManagedObjectEngineRESTinterface(String location, String query, String setCookie) {
		String result = "";
		try {
			GetMethod get = new GetMethod(location.trim()+query.trim());
			get.addRequestHeader("Cookie", setCookie);
			get.addRequestHeader("Accept", "application/json");

			client.executeMethod(get);
			String jsessionIdGET = "";

			Header[] responseHeaders = get.getResponseHeaders("Set-Cookie");
			for (int i = 0; i < responseHeaders.length; i++) {
				//System.out.println("responseHeaders GET " + i + " --> " + responseHeaders[i].getName() + " " + responseHeaders[i].getValue());
				jsessionIdGET = responseHeaders[i].getValue().split(";")[0];
			}
			// jsessionIdGET =
			// get.getResponseHeader("Set-Cookie").getValue().split(";")[0];

			result = loadStreamAsString(get.getResponseBodyAsStream());

			if (result.contains("instance(s) updated")) {
				log.info("GET response: " + result);
				System.out.println("GET response: " + result);
				return result;
			}

			if (jsessionIdGET != setCookie) {
				// System.out.println("\n***ENM wrong session returned error***\n***Running GET Again***");
				client.executeMethod(get);
			}

			result = loadStreamAsString(get.getResponseBodyAsStream());

			log.info("GET response: " + result);
			System.out.println("GET response: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private static String loadStreamAsString(InputStream is) {
		BufferedReader reader = null;

		StringBuilder stringBuilder = new StringBuilder();
		try {

			reader = new BufferedReader(new InputStreamReader(is));
			String line = null;

			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
		} catch (Exception ignore) {
		}

		return stringBuilder.toString();
	}
	
	/*private static String parseIdFromResult(String input){
		String[] parts = input.split(":");
		String returnValue=null;
		try{
			String s=parts[3];
			String[] subParts=s.split(",");
			returnValue=subParts[0].substring(1, subParts[0].length()-1);
			System.out.println("parsed ID: " + returnValue);
		}catch (ArrayIndexOutOfBoundsException aioobe){aioobe.getMessage();}
		if(returnValue != null) return returnValue;
		return "ID not parsed";
	}*/
}