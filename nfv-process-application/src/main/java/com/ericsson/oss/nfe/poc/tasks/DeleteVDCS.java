package com.ericsson.oss.nfe.poc.tasks;

import static com.ericsson.oss.nfe.poc.core.ApplicationConstants.BASE_ECM_URL_KEY;

import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.utils.ECMRESTUtil;
import com.ericsson.oss.nfe.poc.utils.Utils;

public class DeleteVDCS extends BaseServiceTask {

	private final Logger log = LoggerFactory.getLogger("activity-tracker");
	
	public void execute(DelegateExecution execution) {

		String inputVDCId = (String) execution.getVariable("delVdcNameId");
		
		log.info("Starting DeleteVDCS task with inputVDCId : "+inputVDCId); 
		
		
		// String vdcId = inputVDCId = ((Map<String,
		// String>)execution.getVariable(ECM_NAMEID_MAP)).get("EPG-VDC");

		try {
			if (!StringUtils.isEmpty(inputVDCId)) {

				// Extract the VDC id from "TestVDC(vdc-11)" string
				String vdcID = Utils.extractVDCID(inputVDCId);
				// Finally delete the VDC
				deleteVDC(vdcID);

			}

		} catch (Exception e) {
			log.warn("Exeception during rest call to get Vms for VDC ", e);

		}

		log.info("Ending DeleteVDCS task with inputVDCId : "+inputVDCId); 

	}

	private void deleteVDC(String vdcID) {

		log.info("Deleting VDC ----" + vdcID);

		try {
			String deleteVM = AppConfigLoader.getProperty(BASE_ECM_URL_KEY) + "/vdcs/" + vdcID;

			String getVNSResponse = new ECMRESTUtil().doDeleteRequest(deleteVM);

			log.info("Deleting VDC success----" + vdcID);

		} catch (Exception e) {
			log.warn("Exeception deleteVDC : {} ,ignoring as its ok", vdcID, e);
		}

	}
}