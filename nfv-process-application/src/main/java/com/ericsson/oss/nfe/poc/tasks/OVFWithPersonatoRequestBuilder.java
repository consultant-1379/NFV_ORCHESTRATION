package com.ericsson.oss.nfe.poc.tasks;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.impl.javax.el.ExpressionFactory;
import org.camunda.bpm.engine.impl.javax.el.ValueExpression;
import org.camunda.bpm.engine.impl.juel.SimpleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.utils.FileUtils;
import com.floreysoft.jmte.Engine;

public class OVFWithPersonatoRequestBuilder extends BaseServiceTask {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private Expression ovfTemplateFileName;
	
	private Expression initialConfigtemplateName;
	
	private Expression initialConfigString;

	private Expression outPutVariable;

	public void execute(DelegateExecution execution) {
		
		try {

			this.buildTaskParamsLog(execution, new Expression[]{initialConfigtemplateName,ovfTemplateFileName});
			
			log.info("------------------------------OVFWithPersonatoRequestBuilder task started ----------------- ");
	
			// Get the map of VDC,VNs,VMS etc populated from previous get
			Map<String, String> templatePropsMap = (Map<String, String>) execution.getVariable("templateMap");
				
			templatePropsMap = (templatePropsMap != null ? templatePropsMap : new HashMap<String, String>());				
			
			String inputOVFTemplate = ovfTemplateFileName.getValue(execution).toString().trim();
			
			String ovfTemplateJSON = FileUtils.loadOvfAsString(inputOVFTemplate);

			//If String value with initalConfig given then use that else use the template file
			if(isValidExpression(initialConfigString, execution))
			{	
				String initialConfigStr = initialConfigString.getValue(execution).toString().trim();
				
				System.out.println("Initial Config String being used   :");
				
				byte[] encoded = Base64.encodeBase64(initialConfigStr.getBytes());
				templatePropsMap.put("ovfFileId", "file2");
				templatePropsMap.put("basicCFG", new String(encoded));
			
			}
			else if(isValidExpression(initialConfigtemplateName, execution))
			{
				String initialConfigTemplate = initialConfigtemplateName.getValue(execution).toString().trim();
				
				System.out.println("Initial Config Template   :"+initialConfigTemplate);			
	
				String personalityBase64Str = buildPersonalityFromTemplate(initialConfigTemplate,templatePropsMap);
					
				templatePropsMap.put("ovfFileId", "file2");
				templatePropsMap.put("basicCFG", personalityBase64Str);
			}
	
			
			String filledReq = null;
			if (ovfTemplateJSON != null)
				filledReq = buildAndPopulateTemplates(ovfTemplateJSON, templatePropsMap);
	
			log.info("filledReq : " + filledReq);
			
			String outputVarName = outPutVariable.getExpressionText();
	
			execution.setVariable(outputVarName, filledReq);
			
		} catch (Exception e) {
			log.info("Exception occured in OVF template Builder");
			// see exception stacktrace
			e.printStackTrace();
		}
		log.info("------------------------------OVFWithPersonatoRequestBuilder task Ended ----------------- ");

	}

	private String buildAndPopulateTemplates(String jsonTemplate, Map<String, String> execVars) {
		Engine engine = new Engine();
		Map<String, Object> tokens = new HashMap<String, Object>();

		if (execVars != null)
			tokens.putAll(execVars);

		String transformed = engine.transform(jsonTemplate, tokens);

		return transformed;
	}
	
	private String buildPersonalityFromTemplate(String inputFileName,Map<String, String> templateValueMap){

		String returnStr = "";

		try {
 
			String fileContets = FileUtils.loadStreamAsString(FileUtils.loadFileFromAppConfig(inputFileName));			

			log.info(" Contetns of template file : " + inputFileName + " loaded size : " + fileContets.length());

			if (fileContets != null) {
				ExpressionFactory factory = ExpressionFactory.newInstance();

				SimpleContext context = new SimpleContext(); // more on this
						 
				for (Map.Entry<String, String> entry : templateValueMap.entrySet())
					context.setVariable(entry.getKey(), factory.createValueExpression(entry.getValue(), String.class));

				ValueExpression e = factory.createValueExpression(context, fileContets, String.class);

				String ubencodedString = e.getValue(context).toString();
				
				log.debug("------------------------------initialConfigTemplate built is   ----------------- " +ubencodedString);
				
				log.debug("------------------------------initialConfigTemplate built is  ended ----------------- ");
				
				byte[] encoded = Base64.encodeBase64(ubencodedString.getBytes());
				returnStr = new String(encoded);

			}
		} catch (Throwable e) {
			log.error("Error in buildPersonalityFromTemplate,but ignoring ", e.getMessage());
			//throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Exception in BuildStringFromTemplate");
		}
		
		return returnStr;
	}
	  

	private boolean isValidExpression(Expression expr, DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr.getExpressionText() != null));
	}
}