package com.ericsson.oss.nfe.poc.sapc.mock.server;

import java.util.Map;

import org.junit.Test;

import com.ericsson.oss.nfe.poc.tasks.FileDetector;
import com.ericsson.oss.nfe.poc.tasks.MockExecutionEnvironment;

public class TestFileDetector {
	
	@Test
	public void shouldTestFileDetectionWhenAA() throws Exception{
		FileDetector fileDetector = new FileDetector();
		MockExecutionEnvironment mockExecutionEnvironment = new MockExecutionEnvironment();
		mockExecutionEnvironment.setVariable("file.detector.type", "sapc");
		fileDetector.execute(mockExecutionEnvironment);
		Map<String, Object> variableMap = mockExecutionEnvironment.getVariables();
		for(Map.Entry<String, Object> entry : variableMap.entrySet()){
			System.out.println(entry.getKey() + " --> " + entry.getValue());
		}
	}
}
