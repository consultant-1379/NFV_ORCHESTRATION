package com.ericsson.oss.nfe.ejb.service;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.ericsson.oss.nfe.persistence.entity.ExtendedProcessDefinition;
import com.ericsson.oss.nfe.poc.utils.RESTUtil;
import com.ericsson.oss.nfe.poc.utils.vo.HeaderTupple;

public class ProcessDefinitionCallable implements Callable<ExtendedProcessDefinition> {

	private String baseCamundaURL;
	
	private ExtendedProcessDefinition self;
	
	private static final String BPMN_DOC_TAG="<bpmn2:documentation>";
	
	public ProcessDefinitionCallable(String baseURL,ExtendedProcessDefinition defn)
	{
		super();
		this.baseCamundaURL=baseURL;
		this.self = defn;
	}
	
	@Override
	public ExtendedProcessDefinition call() throws Exception {
		
		//System.out.println("Executing callable !!");
		
		RESTUtil restUtil = new RESTUtil();
		//String xmlURL=baseCamundaURL+"/process-definition/"+self.getId()+"/xml";
		String instanceURL=baseCamundaURL+"/process-definition/"+self.getId();
		
		//String responseStr = restUtil.doGETRequest(xmlURL, new ArrayList<HeaderTupple>());
		String responseStr = restUtil.doGETRequest(instanceURL, new ArrayList<HeaderTupple>());
		
		//JSONObject processDefnxml = (JSONObject) JSONValue.parse(responseStr);
		JSONObject processDefn = (JSONObject) JSONValue.parse(responseStr);
		
		//self.setDescription(this.parseOutDocumentation(processDefnxml.get("bpmn20Xml").toString()));
		if(processDefn.get("description") != null && processDefn.get("description").toString().trim() != "")
			self.setDescription(processDefn.get("description").toString());
		else
			self.setDescription("no description");
				
		///System.out.println("Executing callable ended well !!");
		
		return self;
	}

	@Deprecated
	private String parseOutDocumentation(String defintionXMLStr)
	{
		
		String compositePattern= "(\\<bpmn2:documentation\\>)([\\w\\s]*)(\\<\\/bpmn2:documentation\\>)";
	    Pattern p = Pattern.compile(compositePattern,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(defintionXMLStr);
	    if (m.find())
	    {
	        String word1=m.group(1);
	        String word2=m.group(2);
	        //System.out.print("("+word2.toString()+")"+"\n");
	        return word2.toString();
	    } 
			return "";
	}
	
	 
}
