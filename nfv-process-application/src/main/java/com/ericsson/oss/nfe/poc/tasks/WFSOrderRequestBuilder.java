package com.ericsson.oss.nfe.poc.tasks;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.minidev.json.JSONArray;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.ECMRESTUtil;
import com.ericsson.oss.nfe.poc.utils.FileUtils;
import com.floreysoft.jmte.Engine;
import com.jayway.jsonpath.JsonPath;

public class WFSOrderRequestBuilder implements JavaDelegate {

	private Expression inputTemplate;

	private Expression outPutVariable;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		System.out.println("******************************* WFSOrderRequestBuilder task started ********************************************");

		try {

			String inputTemplateName = (String) inputTemplate.getValue(execution);

			ClassLoader classloader = Thread.currentThread().getContextClassLoader();

			InputStream is = classloader.getResourceAsStream(inputTemplateName);

			String templateContents = FileUtils.loadStreamAsString(is);

			Engine engine = new Engine();

			Map<String, Object> tokens = new HashMap<String, Object>();

			String VDCName = execution.getVariable("VDCName").toString();

			String vdcId = VDCName.substring(VDCName.indexOf("(") + 1, VDCName.indexOf(")"));

			System.out.println("vdc id obtained is -------------> " + vdcId);

			String vmName = execution.getVariable("VMName").toString();

			String imageName = execution.getVariable("imageName").toString();

			String vmhd = AppConfigLoader.einstance.getProperty("VMHD");

			String endpointURL = AppConfigLoader.einstance.getProperty("VN_FOR_VDCID") + vdcId;

			String jsonPath = "$.vns[*].id";

			String vnId = null;

			String response = null;
			try {
				response = new ECMRESTUtil().doGETRequest(endpointURL);
			} catch (RESTInvokeException ignore) {
			}
			JSONArray values = JsonPath.read(response, jsonPath);

			for (Object x : values) {
				vnId = (String) x;
				break;
			}
			System.out.println("vn id obtained is -------------> " + vnId);

			tokens.put("vdcId", vdcId);
			tokens.put("imageName", imageName);
			tokens.put("vmhd", vmhd);
			tokens.put("VMName", vmName);
			tokens.put("vnId", vnId);

			String transformed = engine.transform(templateContents, tokens);

			System.out.println("*************************** Order Request *************************************");
			System.out.println(transformed);

			String outPutVariableStr = outPutVariable.getValue(execution).toString();

			execution.setVariable(outPutVariableStr, transformed);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
