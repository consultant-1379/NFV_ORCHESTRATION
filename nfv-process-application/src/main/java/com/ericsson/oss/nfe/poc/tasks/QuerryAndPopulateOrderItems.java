package com.ericsson.oss.nfe.poc.tasks;

import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.ACTIVE_PROVISIONING;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.DEFAULT_VDCNAME_KEY;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.ECM_NAMEID_MAP;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.GET_VAPPS_FOR_VDC_URL_KEY;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.GET_VDC_URL_KEY;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.GET_VMS_FOR_VDC_URL_KEY;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.GET_VNS_FOR_VDC_URL_KEY;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.ID_JSON_KEY;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.NAME_JSON_KEY;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.PROVISION_STATUS_KEY;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minidev.json.JSONObject;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.ECMRESTUtil;
import com.jayway.jsonpath.JsonPath;

public class QuerryAndPopulateOrderItems extends BaseServiceTask {

	private final Logger log = LoggerFactory.getLogger("activity-tracker");
	
	private Expression inputcreateOrderResponse;

	private Expression outPutVariable;

	public void execute(DelegateExecution execution) {
 
		log.info("Starting QuerryAndPopulateOrderItems  task with Params : "+buildTaskParamsLog(execution, new Expression[]{inputcreateOrderResponse}));
		
		ECMRESTUtil restutil = new ECMRESTUtil();

		Map<String, String> ecmProcessIDMap = (Map<String, String>) execution.getVariable(ECM_NAMEID_MAP);

		// If the process Param is not already set and its first time
		ecmProcessIDMap = (ecmProcessIDMap == null || ecmProcessIDMap.isEmpty()) ? new HashMap<String, String>() : ecmProcessIDMap;

		String orderStatus = null;
		try {

			JSONObject vdcObject = this.extractVDC(restutil, execution);

			log.debug("VDC ID Obtained is --------------------> " + getStringVal(vdcObject, ID_JSON_KEY));

			ecmProcessIDMap.put(getStringVal(vdcObject, NAME_JSON_KEY), getStringVal(vdcObject, ID_JSON_KEY));

			ecmProcessIDMap.put("vdc", getStringVal(vdcObject, ID_JSON_KEY));

			log.debug("VDC obtained is --->" + vdcObject.toJSONString());

			boolean isSuccess = this.getAndPopulateVNS(restutil, ecmProcessIDMap, vdcObject);
		    
			//If get VN's succeeds only then try get VM's as mostly this will be case of empty VDC
			if(isSuccess)
		    	this.getAndPopulateVMS(restutil, ecmProcessIDMap, vdcObject);
			
			if(isSuccess)
				this.getAndPopulatevApps(restutil, ecmProcessIDMap, vdcObject);

			//System.out.println(" ecmProcessIDMap populated is --> " + ecmProcessIDMap);

			execution.setVariableLocal(ECM_NAMEID_MAP, ecmProcessIDMap);

			// Later will add VMS when needed

		} catch (Exception e) {
			log.error("Exeception Querrying VDC and VMS");
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Exeception Querrying VDC and VMS:" + e);
		}

		if (outPutVariable != null) {

			String outputVarName = outPutVariable.getExpressionText();
			execution.setVariable(outputVarName, orderStatus);

		}

		log.info("Ending  QuerryAndPopulateOrderItems task with orderStatus : "+orderStatus);
 

	}

	private JSONObject extractVDC(ECMRESTUtil restutil, DelegateExecution execution) {

		JSONObject vdcObject = null;
		try {
			// First querry the VDC

			String vdcNameProcVariable = this.getVDCNameforFlow(execution);

			log.debug(" FLow vDC Name is -------------------------> " + vdcNameProcVariable);

			String getVDCURL = AppConfigLoader.getProperty(GET_VDC_URL_KEY);

			String tenantName = AppConfigLoader.getProperty("ecm.props.map.tenantName");
			String filter = "tenantName='" + tenantName + "'";

			String getVDCRespJsonStr = restutil.doGETRequest(getVDCURL + URLEncoder.encode(filter));

			List<JSONObject> vdcs = JsonPath.read(getVDCRespJsonStr, "$.vdcs");

			// VDC is a must cannot proceed without a provisioned VDC
			if (vdcs == null || vdcs.isEmpty())
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "NO VDC's defined for the tenant : " + tenantName + " in response " + getVDCRespJsonStr);

			for (JSONObject vdc : vdcs) {
				String status = (String) vdc.get(PROVISION_STATUS_KEY);
				String vdcName = (String) vdc.get("name");
				if (ACTIVE_PROVISIONING.equalsIgnoreCase(status) && vdcNameProcVariable.equalsIgnoreCase(vdcName)) {
					vdcObject = vdc;
					break;
				}
			}
			// VDC is a must cannot proceed without a provisioned VDC
			if (vdcObject == null)
				throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "NO VDC's defined for the tenant : " + tenantName);

			log.debug("VDC obtained is --->" + vdcObject.toJSONString());

		} catch (Exception e) {
			log.error("Exeception building and extracting VDCS");
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Exeception building and extracting VDCs:" + e);
		}

		return vdcObject;
	}

	private boolean getAndPopulateVNS(ECMRESTUtil restutil, Map<String, String> ecmProcessIDMap, JSONObject vdcObject) throws RESTInvokeException {

		boolean successful = false;
		// then VNS for VDC for now+ "'" + vdcObject.get(ID_JSON_KEY)+"'"
		try {
			String getVNSURL = AppConfigLoader.getProperty(GET_VNS_FOR_VDC_URL_KEY);

			// Build the filter key and then encode
			String filter = "tenantName='" + AppConfigLoader.getProperty("ecm.props.map.tenantName") + "' 'and' vdcId='" + getStringVal(vdcObject, ID_JSON_KEY) + "'";

			String getVNSResponseJsonStr = restutil.doGETRequest(getVNSURL + URLEncoder.encode(filter));

			if(getVNSResponseJsonStr!=null && getVNSResponseJsonStr.trim().length()>0 )
			{
				List<JSONObject> vns = JsonPath.read(getVNSResponseJsonStr, "$.vns");

				// Not throwing exception when VN or VMS are not present for VDC as
				// 	it might be possible
				if (vns != null && !vns.isEmpty()) {
					for (JSONObject vn : vns) {
						String status = (String) vn.get(PROVISION_STATUS_KEY);
						if (ACTIVE_PROVISIONING.equalsIgnoreCase(status)) 
							ecmProcessIDMap.put(getStringVal(vn, NAME_JSON_KEY), getStringVal(vn, ID_JSON_KEY));						 
					}
				}					
				successful = true;
			}
			
		} catch (Exception e) {
			log.warn("Exeception building and extracting VNS for VDCS,ignoring as its ok", e);
			e.printStackTrace();
		}
		
		return successful;
	}

	private void getAndPopulateVMS(ECMRESTUtil restutil, Map<String, String> ecmProcessIDMap, JSONObject vdcObject) throws RESTInvokeException {

		// then VNS for VDC for now+ "'" + vdcObject.get(ID_JSON_KEY)+"'"
		try {
			String getVMSURL = AppConfigLoader.getProperty(GET_VMS_FOR_VDC_URL_KEY);

			// Build the filter key and then encode
			String filter = "vdcId='" + getStringVal(vdcObject, ID_JSON_KEY) + "' 'and' tenantName='" + AppConfigLoader.getProperty("ecm.props.map.tenantName").trim() + "'";

			String getVMSResponseJsonStr = restutil.doGETRequest(getVMSURL + URLEncoder.encode(filter));

			if(getVMSResponseJsonStr!=null && getVMSResponseJsonStr.trim().length()>0 )
			{
				List<JSONObject> vms = JsonPath.read(getVMSResponseJsonStr, "$.vms");
	
				// Not throwing exception when VN or VMS are not present for VDC as
				// it might be possible
				if (vms != null && !vms.isEmpty()) {
					for (JSONObject vm : vms) {
						String status = (String) vm.get(PROVISION_STATUS_KEY);
						if (ACTIVE_PROVISIONING.equalsIgnoreCase(status))
							ecmProcessIDMap.put(getStringVal(vm, NAME_JSON_KEY), getStringVal(vm, ID_JSON_KEY));					
					}
				}
			}
		} catch (Exception e) {
			log.warn("Exeception building and extracting VMS for VDCS,ignoring as its ok", e);
			e.printStackTrace();
		}
	}
	
	
	private void getAndPopulatevApps(ECMRESTUtil restutil, Map<String, String> ecmProcessIDMap, JSONObject vdcObject) throws RESTInvokeException {

		// then VNS for VDC for now+ "'" + vdcObject.get(ID_JSON_KEY)+"'"
		try {
			String getVMSURL = AppConfigLoader.getProperty(GET_VAPPS_FOR_VDC_URL_KEY);

			// Build the filter key and then encode
			String filter = "vdcId='" + getStringVal(vdcObject, ID_JSON_KEY) + "' 'and' tenantName='" + AppConfigLoader.getProperty("ecm.props.map.tenantName").trim() + "'";

			String getVappsResponseJsonStr = restutil.doGETRequest(getVMSURL + URLEncoder.encode(filter));

			if(getVappsResponseJsonStr!=null && getVappsResponseJsonStr.trim().length()>0 )
			{
				List<JSONObject> vapps = JsonPath.read(getVappsResponseJsonStr, "$.vapps");
	
				// Not throwing exception when VN or VMS are not present for VDC as
				// it might be possible
				if (vapps != null && !vapps.isEmpty()) {
					for (JSONObject vapp : vapps) {
						String status = (String) vapp.get(PROVISION_STATUS_KEY);
						if (ACTIVE_PROVISIONING.equalsIgnoreCase(status))
							{
								ecmProcessIDMap.put("VAPP_NAME", getStringVal(vapp, NAME_JSON_KEY));
								ecmProcessIDMap.put("VAPP_ID", getStringVal(vapp, ID_JSON_KEY));
							}
					}
				}
			}
		} catch (Exception e) {
			log.warn("Exeception building and extracting VMS for VDCS,ignoring as its ok", e);
			e.printStackTrace();
		}
	}

	private String getVDCNameforFlow(DelegateExecution execution) {
		// ####
		if (execution.getVariable("VDCName") != null)
			System.out.println("VDC name execution- " + execution.getVariable("VDCName"));
		else
			System.out.println("VDC name execution is null");

		String vdcName = execution.getVariable("VDCName") != null ? execution.getVariable("VDCName").toString() : AppConfigLoader.getProperty(DEFAULT_VDCNAME_KEY);
		// ####
		System.out.println("VDC name set- " + vdcName);
		return vdcName;
	}

	private String getStringVal(JSONObject json, String key) {
		Object val = json.get(key);
		return val != null ? val.toString() : "";
	}

}