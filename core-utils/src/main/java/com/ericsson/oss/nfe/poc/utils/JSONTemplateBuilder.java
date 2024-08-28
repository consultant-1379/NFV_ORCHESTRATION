package com.ericsson.oss.nfe.poc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minidev.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.jayway.jsonpath.JsonPath;

public class JSONTemplateBuilder {

	private static final String ignoreAttrributes = "itemId,maxCardinality,minCardinality,parentItemRef";
	private static final String handleAttrributes = "affinity,antiaffinity,personalities,metadata,internalIpAddresses";

	private static boolean isIgnoredAttr(String key) {
		List<String> ignoreAttList = Arrays.asList(ignoreAttrributes.split(","));
		return ignoreAttList.contains(key);
	}

	private static boolean isHandleAttr(String key) {
		List<String> handleAttList = Arrays.asList(handleAttrributes.split(","));
		return handleAttList.contains(key);
	}

	private static boolean isPropertyField(JSONObject tempJson) {
		List<String> keys = Arrays.asList("isNull,isImmutable".split(","));
		return tempJson.keySet().containsAll(keys);
	}

	public static String buildOperation(String key) {

		String firstChar = String.valueOf(key.charAt(0));
		return "create" + firstChar.toUpperCase() + key.substring(1);
	}

	public static JSONObject extractJSONObj(JSONObject inTempl, String objKey) {

		JSONObject outPutObj = new JSONObject();
		for (Map.Entry<String, Object> entry : inTempl.entrySet()) {
			if (!isIgnoredAttr(entry.getKey()) && !isHandleAttr(entry.getKey())) {
				Object entryVal = entry.getValue();

				if ((entryVal instanceof JSONObject) && isPropertyField((JSONObject) entryVal)) {
					JSONObject fieldProps = (JSONObject) entryVal;
					boolean isReqd = fieldProps.get("isNull") != null ? (fieldProps.get("isNull").toString().equalsIgnoreCase("false")) : false;

					String defaultValue = "";
					if (fieldProps.containsKey("defaultValue")) {
						defaultValue = (String) fieldProps.get("defaultValue");
					} else {
						if (entry.getKey().equalsIgnoreCase("ipVersion")) {
							defaultValue = "IPv4";
						} else {
							defaultValue = "";
						}
					}

					if (isReqd) {
						if (!StringUtils.isEmpty(defaultValue)) {
							outPutObj.put(entry.getKey(), defaultValue);
						} else {
							outPutObj.put(entry.getKey(), "${" + entry.getKey() + "}");
						}
					} else {
						if (!StringUtils.isEmpty(defaultValue)) {
							outPutObj.put(entry.getKey(), defaultValue);
						}
					}

				} else {
					// This is done to skip the VDC embbeded value in the vmVnic, might be a bug
					if (entry.getKey().equalsIgnoreCase("vdc") && objKey.equalsIgnoreCase("vmVnic"))
						continue;

					if (entry.getKey().equalsIgnoreCase("bootSource")) {
						// "bootSource": {
						// "imageName": {
						// "isNull": false,
						// "isImmutable": false,
						// "defaultValue": "EPG-NFS"
						// },
						// "bbsvName": {
						// "isNull": true,
						// "isImmutable": false
						// }
						// },
						JSONObject fieldProps = (JSONObject) ((JSONObject) entryVal).get("imageName");
						boolean isReqd = fieldProps.get("isNull") != null ? (fieldProps.get("isNull").toString().equalsIgnoreCase("false")) : false;

						String defaultValue = "";
						if (fieldProps.containsKey("defaultValue")) {
							defaultValue = (String) fieldProps.get("defaultValue");
						}
						if (isReqd) {
							if (!StringUtils.isEmpty(defaultValue)) {
								outPutObj.put(entry.getKey(), defaultValue);
							} else {
								outPutObj.put(entry.getKey(), "${" + entry.getKey() + "}");
							}
						} else {
							if (!StringUtils.isEmpty(defaultValue)) {
								outPutObj.put(entry.getKey(), defaultValue);
							}
						}

						JSONObject tempObj = new JSONObject();
						tempObj.put("imageName", defaultValue);

						outPutObj.put(entry.getKey(), tempObj);
						continue;
					}

					// TODO explain what the heck is being done
					if (((JSONObject) entryVal).containsKey("name")) {
						JSONObject fieldProps = (JSONObject) ((JSONObject) entryVal).get("name");
						String defaultValue = (String) fieldProps.get("defaultValue");
						// defaultValue =
						// StringUtils.isEmpty(defaultValue)?"<<>>":defaultValue.trim();
						outPutObj.put("EMBEDED_" + entry.getKey(), defaultValue);
					} else {
						outPutObj.put("EMBEDED_" + entry.getKey(), entry.getKey());
					}
				}
			} else if (isHandleAttr(entry.getKey())) {
				switch (entry.getKey()) {
				case "affinity":
					handleAffinity();
					break;
				case "antiaffinity":
					handleAntiAffinity();
					break;
				case "personalities":
					handlePersonalities();
					break;
				case "metadata":
					handleMetadata();
					break;
				case "internalIpAddresses":
					handleInternalIpAddresses();
					break;

				}
			}
		}
		return outPutObj;
	}

	private static void handleInternalIpAddresses() {
		// TODO Auto-generated method stub

	}

	private static void handleMetadata() {
		// TODO Auto-generated method stub
		// "metadata": [
		// {
		// "tag": {
		// "isNull": true,
		// "isImmutable": false
		// },
		// "value": {
		// "isNull": true,
		// "isImmutable": false
		// }
		// }
		// ]

	}

	private static void handlePersonalities() {
		// TODO Auto-generated method stub
		// "personalities": [
		// {
		// "path": {
		// "isNull": true,
		// "isImmutable": false
		// },
		// "contents": {
		// "isNull": true,
		// "isImmutable": false
		// }
		// }
		// ],

	}

	private static void handleAntiAffinity() {
		// TODO Auto-generated method stub
		// "antiaffinity": {
		// "isNull": true,
		// "isImmutable": false
		// },
	}

	private static void handleAffinity() {
		// TODO Auto-generated method stub
		// "affinity": {
		// "isNull": true,
		// "isImmutable": false
		// },
	}

	public static String buildTemplateResponse(String jsonStr) throws RESTInvokeException {

		JSONObject orderParent = null;
		try {
			List<JSONObject> offersItems = JsonPath.read(jsonStr, "$.offer.offerItems");

			List<JSONObject> orderItems = new ArrayList<JSONObject>();

			for (JSONObject itr : offersItems) {

				String key = itr.keySet().iterator().next();
				// String methodName = "extract"+key.toUpperCase();
				// Method method =
				// BeanUtils.findMethod(JSONTemplateBuilder.class,
				// methodName, JSONObject.class);

				JSONObject temp = JSONTemplateBuilder.extractJSONObj((JSONObject) itr.get(key), key);
				temp.put("key", key);

				// Add for the vdc name override
				if ("vdc".equals(key)) {
					temp.remove("name");
					temp.put("name", "${" + ApplicationConstants.VDC_NAME_PLACHEHOLDER + "}");

					//if (temp.containsKey("description"))
						temp.put("description", "Created by NFV Automation application by ${tor-user-id}");

				}
				if ("vm".equals(key)) {

					/*if (temp.containsKey("description"))
						temp.put("description", "Created by NFV Automation application");*/

					// temporary fix for NRJ wrong image name in offer error
					if (temp.get("imageName") != null && temp.get("imageName").equals("EPG_COMPUTE")) {
						temp.put("imageName", "EPG-COMPUTE");
					}

					if ("vapp".equals(key)) {

						if (temp.containsKey("description"))
							temp.put("description", "Created by NFV Automation application");

						 
						}
				}
				orderItems.add(temp);
			}

			JSONObject createTemplate = new JSONObject();

			JSONObject order = new JSONObject();

			order.put("tenantName", "${tenantName}");

			List<JSONObject> jsonOrderItems = new ArrayList<JSONObject>();

			order.put("orderItems", jsonOrderItems);

			Map<String, String> embededIDMap = new HashMap<String, String>();
			// Post process the orders
			for (int i = 0; i < orderItems.size(); i++) {

				JSONObject temp = orderItems.get(i);
				embededIDMap.put(temp.get("key").toString(), String.valueOf(i));
				// temp.put("orderItemId", String.valueOf(i));
				JSONObject outputObj = (JSONObject) temp.clone();

				for (String objKey : temp.keySet()) {
					// TODO explain what the heck is being done
					if (objKey.startsWith("EMBEDED_")) {

						String embededKey = objKey.substring(8);
						String embededKeyVal = (String) temp.get(objKey);

						JSONObject innerObj = new JSONObject();
						if (embededIDMap.containsKey(embededKey)) {
							innerObj.put("orderItemRef", embededIDMap.get(embededKey));
						} else {
							if (StringUtils.isEmpty(embededKeyVal))
								innerObj.put("id", "${" + embededKey + "}");
							else
								innerObj.put("id", "${" + embededKeyVal + "}");
						}

						outputObj.remove("key");
						outputObj.remove(objKey);
						outputObj.put(embededKey, innerObj);
					} else if (objKey.startsWith("key"))
						outputObj.remove("key");

				}

				String methodName = JSONTemplateBuilder.buildOperation(temp.get("key").toString());
				JSONObject orderJSON = new JSONObject();
				orderJSON.put("orderItemId", String.valueOf(i));
				orderJSON.put(methodName, outputObj);
				jsonOrderItems.add(orderJSON);

			}

			// After all the order items populate the master order
			orderParent = new JSONObject();
			orderParent = order;
			// orderParent.put("order", order);

		} catch (Exception e) {
			e.printStackTrace();
			// throw new RESTInvokeException(500, "Failed to build temaplte",
			// e);
		}
		//System.out.println(orderParent.toString());
		return orderParent != null ? orderParent.toString() : null;
	}
	
	public static void addPersonatlity(){}
	
	public static void addMetaData(){}

}
