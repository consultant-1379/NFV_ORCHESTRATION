package com.ericsson.oss.nfe.poc.tasks;

import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.ACTIVE_PROVISIONING;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.BASE_ECM_URL_KEY;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.GET_VMS_FOR_VDC_URL_KEY;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.ID_JSON_KEY;
import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.PROVISION_STATUS_KEY;

import java.net.URLEncoder;
import java.util.List;

import net.minidev.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.utils.ECMRESTUtil;
import com.ericsson.oss.nfe.poc.utils.Utils;
import com.jayway.jsonpath.JsonPath;

public class DeleteVMS extends BaseServiceTask {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public Expression vmID;

	public void execute(DelegateExecution execution) {

		String inputVDCId = (String) execution.getVariable("delVdcNameId");
		
		log.info("Starting DeleteVMS task with inputVDCId : "+inputVDCId);

		String resultString = ""; 
		// String vdcId = inputVDCId = ((Map<String,
		// String>)execution.getVariable(ECM_NAMEID_MAP)).get("EPG-VDC");

		try {
			if (!StringUtils.isEmpty(inputVDCId)) {

				// Extract the VDC id from "TestVDC(vdc-11)" string
				String vdcID = Utils.extractVDCID(inputVDCId);

				String getVMSURL = AppConfigLoader.getProperty(GET_VMS_FOR_VDC_URL_KEY);

				// Build the filter key and then encode
				String filter = "vdcId='" + vdcID + "' 'and' tenantName='" + AppConfigLoader.getProperty("ecm.props.map.tenantName").trim() + "'";

				String getVMSResponseJsonStr = new ECMRESTUtil().doGETRequest(getVMSURL + URLEncoder.encode(filter));

				List<JSONObject> vms = null;
				if (getVMSResponseJsonStr != null && !("".equalsIgnoreCase(getVMSResponseJsonStr))) {
					vms = JsonPath.read(getVMSResponseJsonStr, "$.vms");
				}
				
				if (vms != null && !vms.isEmpty()) {
					for (JSONObject vn : vms) {
						String status = (String) vn.get(PROVISION_STATUS_KEY);
						if (ACTIVE_PROVISIONING.equalsIgnoreCase(status)) {
							String deleteVM = deleteVM((String) vn.get(ID_JSON_KEY));
							resultString = resultString + deleteVM + ";";
						}
					}

				}

			}

		} catch (Exception e) {
			log.warn("Exeception during rest call to get Vms for VDC", e);

		}

		execution.setVariable("deleteResultString", resultString);
		log.info("Ending DeleteVMS taskv " + resultString);		

	}

	private String deleteVM(String vmID) {

		log.info("Deleting VM ----" + vmID);
		String getVNSResponse ="";
		try {
			String deleteVM = AppConfigLoader.getProperty(BASE_ECM_URL_KEY) + "/vms/" + vmID;

			 getVNSResponse = new ECMRESTUtil().doDeleteRequest(deleteVM);

			log.info("Deleting VM success----" + vmID);
			

		} catch (Exception e) {
			log.warn("Exeception deleteVM : {} ,ignoring as its ok", vmID, e);
		}

		return getVNSResponse;
	}

}