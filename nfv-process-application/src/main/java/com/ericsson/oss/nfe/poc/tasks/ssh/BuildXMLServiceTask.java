package com.ericsson.oss.nfe.poc.tasks.ssh;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.utils.FileUtils;
import com.floreysoft.jmte.Engine;


public class BuildXMLServiceTask implements JavaDelegate {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public Expression filename;
	public Expression vmip;
	public Expression epgNameKey;
	public Expression nodeType;
	
	public void execute(DelegateExecution delegateExecution) throws Exception {

		String fileNameStr = (String) filename.getValue(delegateExecution);
		String vmIP_Str = "";
		String epgNameKeyStr = "";
		String nodeTypeStr ="";
		
		if(vmip!=null)
		{
			vmIP_Str = (String) vmip.getValue(delegateExecution);
		if(isValidExpression(nodeType, delegateExecution) ){
			nodeTypeStr = (String) nodeType.getValue(delegateExecution);
		}
		
		if(isValidExpression(epgNameKey,delegateExecution))
			epgNameKeyStr = (String) epgNameKey.getValue(delegateExecution);
		else
			epgNameKeyStr =(String) delegateExecution.getVariable("VDCName");
		
		if("sapc".equals(nodeTypeStr)){
			_getfileForSAPC(fileNameStr, vmIP_Str, epgNameKeyStr);
		}
		else{
			_getfile(fileNameStr, vmIP_Str,epgNameKeyStr);
		}
		}
	}
//	private void _getfile(String fileNameStr, String vmIPStr,String epgNameKeyStr) {
//		{
//			oamIP = (String) oamip.getValue(delegateExecution);
//		}
//		else
//		{
//			log.info("OAMIP not set using default " +  oamIP);
//		}
// 		log.info("Searching the file: " + fileNameStr + " " + vmIP_Str + " " + oamIP);
//		_getfile(fileNameStr, vmIP_Str,oamIP);
//	}
	
	private void _getfileForSAPC(String fileNameStr, String vmIP_Str, String VDCName){
		Engine engine = new Engine();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("SAPC_IP", vmIP_Str);
		model.put("VDC-NAME", VDCName);
		
		InputStream is =   FileUtils.loadFileFromAppConfig(fileNameStr);
		String template = null;
		if(is!=null)
		{
			template = FileUtils.loadStreamAsString(is);
			String output = engine.transform(template, model);
			System.out.println("==================================");
			System.out.println(output);
			System.out.println("==================================");
			_buildXMLFile(output);
		}
		else
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Error getting the file :"+fileNameStr +" from appconfig dir");
		
	}
	
	private void _getfile(String fileNameStr, String vmIP_Str, String epgNameKeyStr) { 

		Engine engine = new Engine();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("VMIP", vmIP_Str);
		String epgName = "vEPG-"+epgNameKeyStr;
		epgName = epgName.replace(".","-");
		model.put("EPG-NAME", epgName);
		
		InputStream is =   FileUtils.loadFileFromAppConfig(fileNameStr);
		String template = null;
		if(is!=null)
		{
			template = FileUtils.loadStreamAsString(is);
			String output = engine.transform(template, model);
			//System.out.println(output);
			_buildXMLFile(output);
		}
		else
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Error getting the file :"+fileNameStr +" from appconfig dir");
		
	}

	private void _buildXMLFile(String data) {
		FileWriter fw = null;
		try {
			
			String tempDir = AppConfigLoader.getAppConfigDir();
			File f = new File(tempDir+"/sample.xml");
			fw = new FileWriter(f);
			fw.write(data.replaceAll("\r\n", "\n"));
		}
		catch (IOException e) {
			log.error("Failed to build the file: sample.xml",e);
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Failed to build the ORNM Input file");
		}
		finally {
			if (fw != null)
				try {
					fw.close();
				}
				catch (IOException ignore) {
				}
		}
		log.info("sample.xml created successfully");
	}
	
	private boolean isValidExpression(Expression expr,
			DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr
				.getExpressionText() != null));
	}
}
