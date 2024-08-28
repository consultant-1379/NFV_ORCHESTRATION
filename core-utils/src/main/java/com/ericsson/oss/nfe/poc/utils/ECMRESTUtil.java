package com.ericsson.oss.nfe.poc.utils;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.vo.HeaderTupple;
import com.ericsson.oss.nfe.poc.utils.vo.ResponseVO;
import com.ericsson.oss.nfe.poc.utils.vo.ResponseVO.ResponseStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * 
 * This is the adapter class for calling the REST service. For every REST call a public method would exist. And other utility methods common across
 * operations are private.
 * 
 * @author evigkum
 * 
 */

public class ECMRESTUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(ECMRESTUtil.class);

	private final static Logger REQUESTLOGGER = LoggerFactory.getLogger("rio");
	
	// add in the admin flag
	private boolean adminAccess;

	private List<HeaderTupple> setheaders = new ArrayList<HeaderTupple>();
	public ECMRESTUtil() {
		this.adminAccess = false;
	}

	public ECMRESTUtil(boolean adminAccess) {
		this.adminAccess = adminAccess;
	}
	
	/**
	 * This the method which calls the REST POST operation.
	 * 
	 * @param jsonReqStr
	 *            containing the complete JSON to be sent in request body.
	 * 
	 * @param endpointURL
	 *            the endpoint URL for the REST call
	 * 
	 * @return a string containing json response for successful or runtime exception for the creation request.
	 */

	public String doPOSTRequest(String jsonReqStr, String endpointURL) throws RESTInvokeException {

		LOGGER.debug(" entering doPOSTRequest " + endpointURL + " " + jsonReqStr);

		HttpURLConnection urlConnection = null;
		String returnResponse = "";
		ResponseVO respVo = null;
		
		REQUESTLOGGER.info("POST Endpoint URL : " + endpointURL);
		
		REQUESTLOGGER.info("POST REQUEST : " + jsonReqStr);
		
		if(adminAccess)
			returnResponse = new RESTUtil(true).doPOSTRequest(jsonReqStr, endpointURL, this.getHearders());
		else
			returnResponse = new RESTUtil().doPOSTRequest(jsonReqStr, endpointURL, this.getHearders());

		LOGGER.debug("rest call succeed with response code 200");

		REQUESTLOGGER.info("POST RESPONSE :" + returnResponse);

		// This is done to avoid the serializtion exception in camunda cause
		// of jsonobject non serializable erro
		respVo = this.unMarshallResponse(returnResponse);
		
		if(respVo.getData()!=null)
			respVo.setJsonStr(respVo.getData()+"");
		//respVo.setData(null); 
		
		// TODO get the messages from
		if (!(respVo != null && "SUCCESS".equalsIgnoreCase(respVo.getStatus().getReqStatus())))
			throw new RESTInvokeException(500, "Failed REST call for Error returned");

		LOGGER.debug(" exiting doPOSTRequest");

		return ((respVo.getData()!=null)?respVo.getData().toString():"");
	}

	/**
	 * This the method which calls the REST GET operation.
	 * 
	 * @param endpointURL
	 *            the endpoint URL for the REST call
	 * 
	 * @return a string containing json response for successful or runtime exception for the creation request.
	 */
	public String doGETRequest(String endpointURL) throws RESTInvokeException {

		LOGGER.debug(" entering doGETRequest");

		String outJSONStr = null;
		
		ResponseVO respVo = null;
		
		REQUESTLOGGER.debug("GET Endpoint URL : " + endpointURL);
		 
		if(adminAccess)
			outJSONStr = new RESTUtil(true).doGETRequest(endpointURL, this.getHearders());
		else
			outJSONStr = new RESTUtil().doGETRequest(endpointURL, this.getHearders());

		REQUESTLOGGER.info("GET RESPONSE :" + outJSONStr);

		respVo = this.unMarshallResponse(outJSONStr);
		if (respVo.getData() != null)
			respVo.setJsonStr(respVo.getData().toString());
		//respVo.setData(null);

		// TODO get the messages from
		if (!(respVo != null && "SUCCESS".equalsIgnoreCase(respVo.getStatus().getReqStatus())))
			throw new RESTInvokeException(500, "Failed REST call for Error returned");

		LOGGER.debug(" exiting doGETRequest");

		return ((respVo.getData()!=null)?respVo.getData().toString():"");
	}

	/**
	 * This the method which calls the REST GET operation.
	 * 
	 * @param endpointURL
	 *            the endpoint URL for the REST call
	 * 
	 * @return a string containing json response for successful or runtime exception for the creation request.
	 */
	public String doDeleteRequest(String endpointURL) throws RESTInvokeException {

		LOGGER.debug(" entering doDeleteRequest");

		String outJSONStr = null;
		ResponseVO respVo = null;

		REQUESTLOGGER.debug("DELETE endpointURL :" + endpointURL);
		
 		if(adminAccess)
 			outJSONStr = new RESTUtil(true).doDeleteRequest(endpointURL, this.getHearders());
 		else
 			outJSONStr = new RESTUtil().doDeleteRequest(endpointURL, this.getHearders());
 		
		LOGGER.debug(" Checking Delete outJSONStr " + outJSONStr);
		
		REQUESTLOGGER.info("RESPONSE :" + outJSONStr);

		respVo = this.unMarshallResponse(outJSONStr);
		if (respVo.getData() != null)
			respVo.setJsonStr(respVo.getData().toString());
		
		// respVo.setData(null);

		LOGGER.debug(" Checking Delete Status " + respVo);
		// TODO get the messages from
		if (!(respVo != null && "SUCCESS".equalsIgnoreCase(respVo.getStatus().getReqStatus())))
			throw new RESTInvokeException(500, "Failed REST call for Error returned");

		LOGGER.debug(" exiting doDeleteRequest");

		return ((respVo.getData()!=null)?respVo.getData().toString():"");
	}

	public ResponseVO unMarshallResponse(String jsonStr) {
		ResponseVO responseJson =  new ResponseVO();

		try {
			GsonBuilder builder = new GsonBuilder().registerTypeAdapter(JsonObject.class, new JsonObjectTypeAdapter());

			Gson gson = builder.create();

			if (jsonStr == null || StringUtils.isEmpty(jsonStr) || jsonStr.equalsIgnoreCase("{}")) {
				LOGGER.info(" building a dummy success response " + jsonStr + " ; ");

				//responseJson = new ResponseVO();
				ResponseVO.ResponseStatus status = new ResponseStatus();
				status.setReqStatus("SUCCESS");
				responseJson.setStatus(status);
			}
			else
				responseJson = (ResponseVO) gson.fromJson(jsonStr, ResponseVO.class);

		}
		catch (Exception e) {
			responseJson.setStatus(new ResponseStatus("FAILED"));
		}

		return responseJson;
	}

	/**
	 * This method is used to build the authentication header.
	 * 
	 * @return authString "Basic "+Base64Encoded(user:password)
	 */

	private List<HeaderTupple> getHearders() {
		List<HeaderTupple> headers = new ArrayList<HeaderTupple>();
		if(adminAccess)
			headers.add(new HeaderTupple("TenantId", AppConfigLoader.getProperty("rest.server.admin.header.tenantId")));
		else
			headers.add(new HeaderTupple("TenantId", AppConfigLoader.getProperty("rest.server.header.tenantId")));
		
		headers.addAll(setheaders);
		
		return headers;
	}

	public void setHeader(String header,String value)
	{
		setheaders.add(new HeaderTupple(header,value));
		
	}
	private static class JsonObjectTypeAdapter implements JsonDeserializer<JsonElement> {
		public JsonObject deserialize(JsonElement json, Type JsonObject, JsonDeserializationContext context) throws JsonParseException {
			return (JsonObject) json;
		}
	}
}
