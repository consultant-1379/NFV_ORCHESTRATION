package com.ericsson.oss.nfe.poc.servlets;

import java.util.HashMap;
import java.util.Map;

import net.minidev.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.ECMRESTUtil;
import com.ericsson.oss.nfe.poc.utils.RESTUtil;
import com.jayway.jsonpath.JsonPath;

public class Tokens {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String endpointURL;
	private String jsonPath1;
	private String jsonPath2;
	private String varName;
	private String filter;

	@SuppressWarnings("static-access")
	public Map<String, Object> getFormTokens(String requestForm) {

		Map<String, Object> tokens = new HashMap<String, Object>();

		try {
			// VIMZONE
			endpointURL = AppConfigLoader.einstance.getProperty("GET_VIM_URL");
			jsonPath1 = "$.vimZones[*].name";
			varName = "VIMZONES";
			filter = null;
			tokens.putAll(getFromECM(endpointURL, jsonPath1, varName, filter));

		} catch (Exception e) {
			logger.info("Error in getting VIMZONE tokens!");
			e.printStackTrace();
		}

		// VFD
		try {
			tokens.putAll(getVFDTokens());
		} catch (Exception e) {
			logger.info("Error in getting VFD tokens!");
			e.printStackTrace();
		}

		// newWFS.json : create new instance of WFS
		if (requestForm.equalsIgnoreCase("newWFS.json")) {

			// VDCs Name-ID
			endpointURL = AppConfigLoader.einstance.getProperty("GET_VEPC_VDCs_URL");
			varName = "VDC_NAMES";
			jsonPath1 = "$.vdcs[*].name";
			jsonPath2 = "$.vdcs[*].id";
			filter = null;
			tokens.putAll(getFromECM2(endpointURL, jsonPath1, jsonPath2, varName, filter));

			// VM Images
			endpointURL = AppConfigLoader.einstance.getProperty("GET_VM_IMAGES_URL");
			jsonPath1 = "$.images[*].name";
			varName = "VM_IMAGES";
			filter = AppConfigLoader.einstance.getProperty("VM_IMAGE_FILTER");
			tokens.putAll(getFromECM(endpointURL, jsonPath1, varName, filter));

		}

		return tokens;
	}

	private Map<String, Object> getVFDTokens() {

		Map<String, Object> tokens = new HashMap<String, Object>();
		// EPG
		endpointURL = AppConfigLoader.einstance.getProperty("GET_VFD_BASE_URL") + "/epg";
		jsonPath1 = "$.apps[*].name";
		jsonPath2 = "$.apps[*].parent_net";
		varName = "epgInputRanges";
		filter = null;
		tokens.putAll(getFromVFDvm(endpointURL, jsonPath1, jsonPath2, varName, filter));

		// MME
		endpointURL = AppConfigLoader.einstance.getProperty("GET_VFD_BASE_URL") + "/mme";
		jsonPath1 = "$.apps[*].name";
		jsonPath2 = "$.apps[*].parent_net";
		varName = "mmeInputRanges";
		filter = null;
		tokens.putAll(getFromVFDvm(endpointURL, jsonPath1, jsonPath2, varName, filter));

		// SAPC
		endpointURL = AppConfigLoader.einstance.getProperty("GET_VFD_BASE_URL") + "/sapc";
		jsonPath1 = "$.apps[*].name";
		jsonPath2 = "$.apps[*].parent_net";
		varName = "sapcInputRanges";
		filter = null;
		tokens.putAll(getFromVFDvm(endpointURL, jsonPath1, jsonPath2, varName, filter));

		return tokens;
	}

	private Map<String, Object> getFromECM(String endpointURL, String jsonPath, String varName, String filter) {

		Map<String, Object> tokens = new HashMap<String, Object>();

		String respvoStr = doECMRestCall(endpointURL);

		JSONArray values = JsonPath.read(respvoStr, jsonPath);

		String valuesStr = "\"";

		for (Object x : values) {
			System.out.println(x);
			if (filter != null && ((String) x).contains(filter))
				valuesStr += x + "\", \"";
			else if (filter == null)
				valuesStr += x + "\", \"";
		}
		valuesStr += "\"";

		tokens.put(varName, valuesStr);

		return tokens;
	}

	private Map<String, Object> getFromECM2(String endpointURL, String jsonPath1, String jsonPath2, String varName, String filter) {

		Map<String, Object> tokens = new HashMap<String, Object>();

		String respvoStr = doECMRestCall(endpointURL);

		JSONArray names = JsonPath.read(respvoStr, jsonPath1);
		JSONArray value = JsonPath.read(respvoStr, jsonPath2);

		String valuesStr = "\"";
		for (int i = 0; i < names.size(); i++) {
			String x = (String) names.get(i);
			if (filter != null && ((String) x).contains(filter))
				valuesStr += names.get(i) + " (" + value.get(i) + ")\", \"";
			else if (filter == null)
				valuesStr += names.get(i) + " (" + value.get(i) + ")\", \"";
		}
		valuesStr += "\"";

		System.out.println(valuesStr);

		tokens.put(varName, valuesStr);

		return tokens;
	}

	private Map<String, Object> getFromVFDvm(String endpointURL, String jsonPath1, String jsonPath2, String varName, String filter) {

		Map<String, Object> tokens = new HashMap<String, Object>();

		String respvoStr = doRESTCall(endpointURL);

		JSONArray names = JsonPath.read(respvoStr, jsonPath1);
		JSONArray value = JsonPath.read(respvoStr, jsonPath2);

		String valuesStr = "\"";
		for (int i = 0; i < names.size(); i++) {
			String x = (String) names.get(i);
			if (filter != null && ((String) x).contains(filter))
				valuesStr += names.get(i) + " (" + value.get(i) + ")\", \"";
			else if (filter == null)
				valuesStr += names.get(i) + " (" + value.get(i) + ")\", \"";
		}
		valuesStr += "\"";

		System.out.println(valuesStr);

		tokens.put(varName, valuesStr);

		return tokens;
	}

	private String doECMRestCall(String url) {
		String response = null;
		try {
			response = new ECMRESTUtil().doGETRequest(url);
		} catch (RESTInvokeException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			logger.warn("Error occured (reason could be - not an ecm url");
			response = doRESTCall(url);
		}
		return response;
	}

	private String doRESTCall(String url) {
		String response = null;
		try {
			response = new RESTUtil().doGETRequest(url, null);
		} catch (RESTInvokeException e) {
			e.printStackTrace();
		}
		return response;
	}
}
