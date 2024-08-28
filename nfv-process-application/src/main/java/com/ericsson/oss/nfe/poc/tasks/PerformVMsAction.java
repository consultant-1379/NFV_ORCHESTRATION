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
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.utils.ECMRESTUtil;
import com.jayway.jsonpath.JsonPath;

public class PerformVMsAction implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public Expression action;
	
	public Expression vmID;

	public void execute(DelegateExecution execution) {

		log.info("------------------------------PerformVMsAction task started ----------------- ");

		String vdcName = (String) execution.getVariable("VDCName");

		String actionStr = (String) action.getValue(execution);

		if (isValidExpression(action, execution)) {
			try {
				if (!StringUtils.isEmpty(vdcName)) {

					String getVMSURL = AppConfigLoader.getProperty(GET_VMS_FOR_VDC_URL_KEY);
					String filter = "vdcName='" + vdcName + "' 'and' tenantName='" + AppConfigLoader.getProperty("ecm.props.map.tenantName").trim() + "'";
					String getVMSResponse = new ECMRESTUtil().doGETRequest(getVMSURL + URLEncoder.encode(filter));
					List<JSONObject> vms = null;

					if (getVMSResponse != null && !("".equalsIgnoreCase(getVMSResponse))) {
						vms = JsonPath.read(getVMSResponse, "$.vms");
					}

					if (vms != null && !vms.isEmpty()) {
						for (JSONObject vm : vms) {
							String status = (String) vm.get(PROVISION_STATUS_KEY);
							if (ACTIVE_PROVISIONING.equalsIgnoreCase(status)) {
								performVMaction((String) vm.get(ID_JSON_KEY), actionStr);
							}
						}
					}

				} else {
					log.error("VDC Name not found!");
				}
			} catch (Exception e) {
				log.warn("Exeception during rest call to get Vms for VDC", e);

			}
		} else {
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Error performing the " + action + " action on the VMs");
		}

		log.info("------------------------------PerformVMsAction task Ended ----------------- ");

	}

	private void performVMaction(String vmID, String action) {

		log.info("performing " + action + " action on VM ----" + vmID);

		try {
			String actionURL = AppConfigLoader.getProperty(BASE_ECM_URL_KEY) + "/vms/" + vmID + "/" + action.trim();
			String getVMSResponse = new ECMRESTUtil().doPOSTRequest(" ", actionURL);

			log.info(action + " VM success----" + vmID);
		} catch (Exception e) {
			log.warn("Exeception performVMaction : {} ,ignoring as its ok", vmID, e);
		}

	}

	private boolean isValidExpression(Expression expr, DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr.getExpressionText() != null));
	}

}