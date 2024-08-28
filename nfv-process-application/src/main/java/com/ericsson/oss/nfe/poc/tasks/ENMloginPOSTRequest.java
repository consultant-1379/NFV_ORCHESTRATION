package com.ericsson.oss.nfe.poc.tasks;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ENMloginPOSTRequest implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private Expression endpointURL;
	
	private Expression username;
	
	private Expression password;

	private Expression cookie;

	public void execute(DelegateExecution execution) {

		log.info("------------------------------ENMloginPOSTRequest task started ----------------- ");

		String url = endpointURL.getValue(execution).toString();
		
		String cookieVar = cookie.getValue(execution).toString();
		
		String user = username.getValue(execution).toString();
		
		String pass = password.getValue(execution).toString();
		
		String cookie = null;
		
		try {
			cookie = sendPostLoginRESTinterface(url,user,pass);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		execution.setVariable(cookieVar, cookie);
		
	}

	private String sendPostLoginRESTinterface(String url, String user, String password) throws HttpException, IOException {
		String cookie = null; 
		System.out.println("POSTing login: " + user + " " + password);

		Protocol myhttps = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);

		final PostMethod post = new PostMethod(url);
		final HttpClient client = new HttpClient();

//		Header[] requestHeaders = post.getRequestHeaders();
//		
//		for (int i = 0; i < requestHeaders.length; i++) {
//			System.out.println("requestHeaders " + i + " " + requestHeaders[i].toString() + " " + requestHeaders[i].getName());
//		}
		
//		String refer = "https://apache.vts.com/login/?goto=https://apache.vts.com";
//		String origin = "https://apache.vts.com";
//		String host = "apache.vts.com";
		
		// set headers
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		post.addRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		post.addRequestHeader("User-Agent", "Mozilla/5.0");
//		post.addRequestHeader("Referer", refer);
//		post.addRequestHeader("Origin", origin);
//		post.addRequestHeader("Host", host);
		post.addRequestHeader("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6");
		post.addRequestHeader("Cache-Control", "max-age=0");
		post.addRequestHeader("Accept-Encoding", "gzip, deflate");
		post.addRequestHeader("Connection", "keep-alive");
		
		// set login credentials for POST request
		NameValuePair params[] = new NameValuePair[] { new NameValuePair("IDToken1", user), new NameValuePair("IDToken2", password) };
		post.setRequestBody(EncodingUtil.formUrlEncode(params, "UTF-8"));
		
		client.executeMethod(post);

		ArrayList<String> setCookies = new ArrayList<String>();
		
		Header[] responseHeaders = post.getResponseHeaders("Set-Cookie");
		
		for (int i = 0; i < responseHeaders.length; i++) {
			//System.out.println("responseHeaders " + i + ": " + responseHeaders[i].getValue() + " " + responseHeaders[i].getName());
			String setcookie = responseHeaders[i].getValue().split(";")[0]; 
			setCookies.add(setcookie);
			
			if(setcookie.contains("iPlanetDirectoryPro")){
				cookie=setcookie;
				System.out.println("Setting session cookie - "+ cookie);
			}
		}
		
		return cookie;
		
	}
}