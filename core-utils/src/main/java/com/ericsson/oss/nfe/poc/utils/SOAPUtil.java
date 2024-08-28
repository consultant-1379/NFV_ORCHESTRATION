package com.ericsson.oss.nfe.poc.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.vo.HeaderTupple;

 

/**
 * 
 * This is the adapter class for calling the SOAP/XML WebService.  
 * @author evigkum
 *
 */

public class SOAPUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(SOAPUtil.class); 

	private final static Logger REQUESTLOGGER = LoggerFactory.getLogger("rio");

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


	public String doPOSTRequest(String requestBody,String endpointURL,List<HeaderTupple> headers)throws RESTInvokeException
	{

		LOGGER.debug("entering SOAP#doPOSTRequest");
  
		LOGGER.info("Endpoint URL build --> : "+endpointURL);

		HttpURLConnection urlConnection = null;
		String returnResponse = ""; 
 		try {

			URL url = new URL(endpointURL);
			
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Accept", "text/xml");
			urlConnection.setRequestProperty("Content-Type", "text/xml");		
			
			REQUESTLOGGER.info("REQUEST :"+requestBody);   
			
			 
			//Set the generic header string
			//setHeaderRequestProperty("rest.server.header1",urlConnection);			 
			urlConnection.setDoOutput(true);
			
			//Set the authentication header
			//urlConnection.setRequestProperty("Authorization", SOAPUtil.buildAuthHeader()); 
			
			//set the headers Passed
			if(headers!=null && !headers.isEmpty())
			{
				for(HeaderTupple ht : headers)
					SOAPUtil.setHeaderRequestProperty(ht, urlConnection);
			}
 
			OutputStream os = urlConnection.getOutputStream();
			os.write(requestBody.toString().getBytes());
			os.flush();

			if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				
				LOGGER.info("Failed SOAP call for  HTTP error code :"+urlConnection.getResponseCode());
				throw new RuntimeException("Failed SOAP: HTTP error code : "+ urlConnection.getResponseCode());
			}

			returnResponse = readResponse(urlConnection.getInputStream());

			LOGGER.info("SOAP call succeed with response code 200");

			REQUESTLOGGER.info("RESPONSE :"+returnResponse);   
			
			LOGGER.info("SUCCESS returned for POST : ");

		} catch (MalformedURLException  e) { 
			LOGGER.error("Failed SOAP call for MalformedURLException : "+e.getMessage());
			throw new RuntimeException("Failed SOAP call for MalformedURLException",e);

		} catch (IOException e) { 
			LOGGER.error("Failed SOAP call for IOException : "+e.getMessage());
			throw new RuntimeException("Failed SOAP call for IOException",e);
		}

		finally
		{
		 
			urlConnection.disconnect();
		} 

		LOGGER.info(" exiting SOAP#doPOSTRequest");

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
 

		LOGGER.info("REST endpoint url built to : "+endpointURL);
		LOGGER.info("REST endpoint header built to : "+headers);

		HttpURLConnection urlConnection = null;
		String outJSONStr = null;
  		try {

			URL url = new URL(endpointURL);
			
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Accept", "application/json");
			
			//Set the authentication header
			urlConnection.setRequestProperty("Authorization", SOAPUtil.buildAuthHeader());
			
			REQUESTLOGGER.info("REQUEST :");
			
			//set the headers Passed
			if(headers!=null && !headers.isEmpty())
			{
				for(HeaderTupple ht : headers)
					SOAPUtil.setHeaderRequestProperty(ht, urlConnection);
			}

			if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "+ urlConnection.getResponseCode());
			}

			outJSONStr = readResponse(urlConnection.getInputStream());

			LOGGER.info("rest call succeed with response code 200");

			REQUESTLOGGER.info("RESPONSE :"+outJSONStr); 
			
			 

		} catch (MalformedURLException  e) { 
			LOGGER.error("Failed REST call for MalformedURLException : "+e.getMessage());
			throw new RESTInvokeException(500,"Failed REST call for MalformedURLException");

		} catch (IOException e) { 
			LOGGER.error("Failed REST call for IOException : "+e.getMessage());
			throw new RESTInvokeException(500,"Failed REST call for IOException");
		}

		finally
		{
			urlConnection.disconnect();
		}


		LOGGER.info(" exiting doGETRequest");

		return outJSONStr;
	}  
	 
 
	public static String extractXPath(String xpathExpr,String xmlResponse)
	{
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodes = null;
		InputSource inputSource = new InputSource(new ByteArrayInputStream(xmlResponse.getBytes()));
		try {
			
			xpath.setNamespaceContext(new SOAPUtil.SimpleNameSpaceContext());
			nodes = (NodeList) xpath.evaluate(xpathExpr, inputSource, XPathConstants.NODESET);
			
		} catch (XPathExpressionException e) { e.printStackTrace();	}
		
		if(nodes!=null && nodes.getLength()>0)
		{
			return nodes.item(0).getTextContent();
		}
		return "";
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

		LOGGER.info("Authentication user Name  " + name
				+ " Password (only for debug to be removed) --> " + password);

		String authString = name + ":" + password;

		String authStringEnc = Base64Coder.encodeString(authString);

		LOGGER.info("Authentication authStringEnc --> "
				+ ("Basic " + authStringEnc));

		return "Basic " + authStringEnc;
	}

	public static void setHeaderRequestProperty(HeaderTupple headerProperty,
			HttpURLConnection urlConnection) {
		if (headerProperty != null) {
			urlConnection.setRequestProperty(headerProperty.getHeaderName(),
					headerProperty.getHeaderValue());

		}
	}
	/**
	 * Class used for setting the NameSpaceContext for xpath.
	 * @author evigkum
	 *
	 */
	static class SimpleNameSpaceContext implements NamespaceContext {
		
		
		//xos="urn:xapi xmlns:xoscfg="urn:xapi/cfgmgmt/cfgmgr xmlns:upm="http://www.extremenetworks.com/XMLSchema/xos/upm"
		@Override
		public Iterator getPrefixes(String namespaceURI) { 
			
			List nsList = new ArrayList<>();
			if ( namespaceURI.equals( "http://schemas.xmlsoap.org/soap/envelope/")) 
				nsList.add("SOAP-ENV");
			else  if(namespaceURI.equals( "http://www.extremenetworks.com/XMLSchema/xos/common")) 
				nsList.add("com");
			else  if(namespaceURI.equals( "http://www.extremenetworks.com/XMLSchema/xos/upm")) 
				nsList.add("upm");
			else  if(namespaceURI.equals( "urn:xapi")) 
				nsList.add("xos");
			else  if(namespaceURI.equals( "urn:xapi/cfgmgmt/cfgmgr")) 
				nsList.add("xoscfg");
			
			return nsList.iterator();
			
		}
		
		@Override
		public String getPrefix(String namespaceURI) {
			if ( namespaceURI.equals( "http://schemas.xmlsoap.org/soap/envelope/")) 
		        return "SOAP-ENV";		      
			else  if(namespaceURI.equals( "http://www.extremenetworks.com/XMLSchema/xos/common")) 
				return "com";
			else  if(namespaceURI.equals( "http://www.extremenetworks.com/XMLSchema/xos/upm")) 
				return "upm";
			else  if(namespaceURI.equals( "urn:xapi")) 
				return "xos";
			else  if(namespaceURI.equals( "urn:xapi/cfgmgmt/cfgmgr")) 
				return "xoscfg";
			else   
		         return "";
		      
		}
		
		@Override
		public String getNamespaceURI(String prefix) {
			if ( prefix.equals( "SOAP-ENV"))
		        return "http://schemas.xmlsoap.org/soap/envelope/";
		    else if ( prefix.equals( "com"))
		        return "http://www.extremenetworks.com/XMLSchema/xos/common";
			else  if(prefix.equals("upm")) 
				return "http://www.extremenetworks.com/XMLSchema/xos/upm";
			else  if(prefix.equals("xos")) 
				return "urn:xapi";
			else  if(prefix.equals( "xoscfg")) 
				return "urn:xapi/cfgmgmt/cfgmgr";
		      
		      return "";
		}
	}
  
}
