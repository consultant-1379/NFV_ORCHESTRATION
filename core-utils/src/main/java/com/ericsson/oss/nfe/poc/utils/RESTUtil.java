package com.ericsson.oss.nfe.poc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.vo.HeaderTupple;

 

/**
 * 
 * This is the adapter class for calling the REST service. 
 * For every REST call a public method would exist.
 * And other utility methods common across operations are private.
 * 
 * @author evigkum
 *
 */

public class RESTUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(RESTUtil.class); 

	private final static Logger REQUESTLOGGER = LoggerFactory.getLogger("rio");

	private boolean accessAdmin = false;
	
	private boolean noAuth = false;
	/**
	 * This the method which calls the REST POST operation.
	 * 
	 *  @param jsonReqStr containing the complete JSON to be sent in request body.
	 *  
	 *  @param endpointURL the endpoint URL for the REST call
	 * 
	 * @return a string containing json response for successful 
	 *         or runtime exception for the creation request.
	 */
	public RESTUtil(){
		
	}
	
	public RESTUtil(boolean adminaccess){
		this.accessAdmin = adminaccess;
	}
	
	public RESTUtil(String noAuth){
		this.noAuth = true;
	}
	
	public String doPOSTRequest(String jsonReqStr,String endpointURL,List<HeaderTupple> headers)throws RESTInvokeException
	{

		LOGGER.debug("entering doPOSTRequest");		 

 
		LOGGER.debug("POST Endpoint URL build --> : "+endpointURL + " header " + headers);

		HttpURLConnection urlConnection = null;
		String returnResponse = ""; 
 		try {

			URL url = new URL(endpointURL);
			
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("Content-Type", "application/json");		
			
			//REQUESTLOGGER.info("REQUEST :"+jsonReqStr);   
			
			 
			//Set the generic header string
			//setHeaderRequestProperty("rest.server.header1",urlConnection);			 
			urlConnection.setDoOutput(true);
			
			if(!noAuth) {
				//Set the authentication header
				if(this.accessAdmin)
					urlConnection.setRequestProperty("Authorization", RESTUtil.buildAdminAuthHeader());
				else
					urlConnection.setRequestProperty("Authorization", RESTUtil.buildAuthHeader());
			}
			
			//set the headers Passed
			if(headers!=null && !headers.isEmpty())
			{
				for(HeaderTupple ht : headers)
					RESTUtil.setHeaderRequestProperty(ht, urlConnection);
			}
			
			
			OutputStream os = urlConnection.getOutputStream();
			os.write(jsonReqStr.toString().getBytes());
			os.flush();

			if (!(urlConnection.getResponseCode() >= HttpURLConnection.HTTP_OK && urlConnection.getResponseCode() <= HttpURLConnection.HTTP_ACCEPTED)) {
				
				LOGGER.info("Failed Post REST call for  HTTP error code :"+urlConnection.getResponseCode());
				throw new RuntimeException("Post Failed : HTTP error code : "+ urlConnection.getResponseCode());
			}

			returnResponse = readResponse(urlConnection.getInputStream());

			LOGGER.debug("Post rest call succeed with response code 200");

			//REQUESTLOGGER.info("RESPONSE :"+returnResponse);    
			
 
		} catch (MalformedURLException  e) { 
			LOGGER.error("Failed REST call for MalformedURLException : "+e.getMessage());
			throw new RuntimeException("Failed REST call for MalformedURLException",e);

		} catch (IOException e) { 
			LOGGER.error("Failed REST call for IOException : "+e.getMessage());
			throw new RuntimeException("Failed REST call for IOException",e);
		}

		finally
		{
		 
			urlConnection.disconnect();
		} 

		LOGGER.debug(" exiting doPOSTRequest");

		return returnResponse;
	}


	/**
	 * This the method which calls the REST GET operation.
	 * 
	 *  @param endpointURL the endpoint URL for the REST call
	 * 
	 * @return a string containing json response for successful 
	 *         or runtime exception for the creation request.
	 */
	public String doGETRequest(String endpointURL,List<HeaderTupple> headers)throws RESTInvokeException
	{

		LOGGER.debug("entering doGETRequest"); 
		LOGGER.info("GET REST endpoint url built to : "+endpointURL);
		LOGGER.info("REST endpoint header built to : "+headers);

		HttpURLConnection urlConnection = null;
		String outJSONStr = null;
  		try {

			URL url = new URL(endpointURL);
			
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Accept", "application/json");
			
			if(!noAuth) {
				//Set the authentication header
				if(this.accessAdmin)
					urlConnection.setRequestProperty("Authorization", RESTUtil.buildAdminAuthHeader());
				else
					urlConnection.setRequestProperty("Authorization", RESTUtil.buildAuthHeader()); 
				
				//REQUESTLOGGER.info("REQUEST :");
				
				//set the headers Passed
				if(headers!=null && !headers.isEmpty())
				{
					for(HeaderTupple ht : headers)
						RESTUtil.setHeaderRequestProperty(ht, urlConnection);
				}
			}
			
			if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("GET Failed : HTTP error code : "+ urlConnection.getResponseCode());
			}

			if (urlConnection.getContentType().equalsIgnoreCase( "application/octet-stream")) {
				outJSONStr = readFileResponse(urlConnection.getInputStream());
			}
			else
			{
				outJSONStr = readResponse(urlConnection.getInputStream());
	
			}

			LOGGER.debug("GET rest call succeed with response code 200 " + outJSONStr + " contentType " + urlConnection.getContentType());

			//REQUESTLOGGER.info("RESPONSE :"+outJSONStr); 
			
			 

		} catch (MalformedURLException  e) { 
			LOGGER.error("Failed GET REST call for MalformedURLException : "+e.getMessage());
			throw new RESTInvokeException(500,"Failed  GET REST call for MalformedURLException");

		} catch (IOException e) { 
			LOGGER.error("Failed GET REST call for IOException : "+e.getMessage());
			throw new RESTInvokeException(500,"Failed GET REST call for IOException");
		}

		finally
		{
			urlConnection.disconnect();
		}


		LOGGER.debug(" exiting doGETRequest");

		return outJSONStr;
	} 
	
	
	/**
	 * This the method which calls the REST GET operation.
	 * 
	 *  @param endpointURL the endpoint URL for the REST call
	 * 
	 * @return a string containing json response for successful 
	 *         or runtime exception for the creation request.
	 */
	public String doDeleteRequest(String endpointURL,List<HeaderTupple> headers)throws RESTInvokeException
	{

		LOGGER.debug("entering doDeleteRequest"); 
 

		LOGGER.info("Delete REST endpoint url built to : "+endpointURL);
		LOGGER.info("REST endpoint header built to : "+headers);
		//return doDeleteRequest_apacheVersion(endpointURL,headers);
		HttpURLConnection urlConnection = null;
		String outJSONStr = null;
  		try {

			URL url = new URL(endpointURL);
			
			urlConnection = (HttpURLConnection) url.openConnection();
			 urlConnection.setRequestMethod("DELETE");
			//urlConnection.setRequestMethod("POST");
			// We have to override the post method so we can send data
			//urlConnection.setRequestProperty("X-HTTP-Method-Override", "DELETE");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("Content-Type", "application/json");	
			
			if(!noAuth) {
				//Set the authentication header
				if(this.accessAdmin)
					urlConnection.setRequestProperty("Authorization", RESTUtil.buildAdminAuthHeader());
				else
					urlConnection.setRequestProperty("Authorization", RESTUtil.buildAuthHeader()); 		
				
				//set the headers Passed
				if(headers!=null && !headers.isEmpty())
				{
					for(HeaderTupple ht : headers)
						RESTUtil.setHeaderRequestProperty(ht, urlConnection);
				}
			}
			//urlConnection.setDoOutput(true);
			//OutputStream os = urlConnection.getOutputStream();
			//os.write("{}".getBytes());
			//os.flush();
			
			if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Delete Failed : HTTP error code : "+ urlConnection.getResponseCode());
			}

			
				outJSONStr = readResponse(urlConnection.getInputStream());
	
			LOGGER.debug("rest call succeed with response code 200");		
				
			 

		} catch (MalformedURLException  e) { 
			LOGGER.error("Failed  Delete REST call for MalformedURLException : "+e.getMessage());
			throw new RESTInvokeException(500,"Failed Delete REST call for MalformedURLException");

		} catch (IOException e) { 
			LOGGER.error("Failed Delete REST call for IOException : "+e.getMessage());
			throw new RESTInvokeException(500,"Failed Delete REST call for IOException");
		}

		finally
		{
			urlConnection.disconnect();
		}


		LOGGER.debug(" exiting doDeleteRequest");

		return outJSONStr;
	}

 
	
	/**
	 * Inputstream to String converter.
	 * @param is Inputsream of data.
	 * @return a string extracted from Inputstream.
	 */

	private static String readFileResponse(InputStream is) {
		File file = new File("dummy");
		try
		{
		 file = File.createTempFile( "tempfile", ".qcow2");
		file.deleteOnExit();

		
		
		byte[] buffer = new byte[4096];
		int n = - 1;

		OutputStream output = new FileOutputStream( file );
		while ( (n = is.read(buffer)) != -1)
		{
		    if (n > 0)
		    {
		        output.write(buffer, 0, n);
		    }
		}
		output.close();

		is.close();
		}
		catch (Exception e)
		{
			LOGGER.error("Problem reading big input", e);
		}
		return "{\"filename\":\""+ file.getAbsolutePath() +"\"}";

	}
	/**
	 * Inputstream to String converter.
	 * @param is Inputsream of data.
	 * @return a string extracted from Inputstream.
	 */

	private static String readResponse(InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";

	}
	/**
	 * This method is used to build the authentication header.
	 * 
	 * @return authString "Basic "+Base64Encoded(user:password)
	 */
	public static String buildAuthHeader() {

		String name = AppConfigLoader.getProperty("rest.server.auth.username");
		String password = AppConfigLoader
				.getProperty("rest.server.auth.password");

		LOGGER.debug("Authentication user Name  " + name
				+ " Password (only for debug to be removed) --> " + password);

		String authString = name + ":" + password;

		String authStringEnc = Base64Coder.encodeString(authString);

		LOGGER.debug("Authentication authStringEnc --> "
				+ ("Basic " + authStringEnc));

		return "Basic " + authStringEnc;
	}
	
	/**
	 * This method is used to build the admin authentication header.
	 * 
	 * @return authString "Basic "+Base64Encoded(user:password)
	 */
	public static String buildAdminAuthHeader() {

		String name = AppConfigLoader.getProperty("rest.server.admin.auth.username");
		String password = AppConfigLoader
				.getProperty("rest.server.admin.auth.password");

		LOGGER.debug("Authentication user Name  " + name
				+ " Password (only for debug to be removed) --> " + password);

		String authString = name + ":" + password;

		String authStringEnc = Base64Coder.encodeString(authString);

		LOGGER.debug("Authentication authStringEnc --> "
				+ ("Basic " + authStringEnc));

		return "Basic " + authStringEnc;
	}

	public static void setHeaderRequestProperty(HeaderTupple headerProperty,
			HttpURLConnection urlConnection) {
		if (headerProperty != null) {
			LOGGER.info("Adding Header --> " + headerProperty.getHeaderName()
					+ (" " + headerProperty.getHeaderValue()));
			urlConnection.setRequestProperty(headerProperty.getHeaderName(),
					headerProperty.getHeaderValue());

		}
	}
  
}
