package com.ericsson.pct;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.ericsson.oss.nfe.poc.utils.ECMRESTUtil;
import com.ericsson.oss.nfe.poc.utils.FileUtils;
import com.ericsson.oss.nfe.poc.utils.JSONTemplateBuilder;
import com.floreysoft.jmte.Engine;

public class TestECMTemplate {

	@Test
	public void testTemplating() {

		Engine engine = new Engine();
		ECMRESTUtil restUtil = new ECMRESTUtil();

		InputStream is = null;
		String jsonSource = "";
		try {
			is = FileUtils.loadFile("C:\\CloudPoC\\Documents\\ECM2.0\\offers", "ECM1_EPGVDCOFFER.txt");
			jsonSource = FileUtils.loadStreamAsString(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String json = jsonSource.toString().split("\"data\":")[1].replaceAll("(\\n|\\r|\\s)", "");
		String template = "";
		try {
			template = JSONTemplateBuilder.buildTemplateResponse(json);
		} catch (RESTInvokeException e) {
			e.printStackTrace();
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("vimZoneName", "myVzId");
		model.put("tenantName", "lmioss");
		model.put("ipAddressRange","#########");
		model.put("VDC_NAME","xxxxxx");
		String transformed = engine.transform(template, model);
		System.out.println("Rest Request is --> " + transformed);
	}
}
