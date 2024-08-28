package com.ericsson.oss.nfe.poc.utils;

import org.junit.Ignore;
import org.junit.Test;

import com.ericsson.oss.nfe.poc.core.RESTInvokeException;
import com.jayway.jsonpath.JsonPath;

public class JSONTemplateBuilderTest {
	
	@Ignore @Test
	public void testVDCOderBuilder()
	{
		
		String offerjsonStr = FileUtils.loadFileAsString("epg-vdcoffer.json");
		try {
			System.out.println(JSONTemplateBuilder.buildTemplateResponse(offerjsonStr));
		} catch (RESTInvokeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testepgvAPPOderBuilder()
	{
		
		String offerjsonStr = FileUtils.loadFileAsString("epgVappOffer.json");
		
		try {
			System.out.println(JSONTemplateBuilder.buildTemplateResponse(JsonPath.read(offerjsonStr, "$.data").toString()));
		} catch (RESTInvokeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
