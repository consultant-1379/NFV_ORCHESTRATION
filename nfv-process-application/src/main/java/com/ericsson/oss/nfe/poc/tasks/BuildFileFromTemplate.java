package com.ericsson.oss.nfe.poc.tasks;

import java.io.InputStream;
import java.util.Map;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.javax.el.ExpressionFactory;
import org.camunda.bpm.engine.impl.javax.el.ValueExpression;
import org.camunda.bpm.engine.impl.juel.SimpleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.core.AppConfigLoader;
import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.utils.FileUtils;

public class BuildFileFromTemplate implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private Expression inputFile;

	//private Expression templateVariableMap;

	private Expression outputFileName;

	public void execute(DelegateExecution execution) {

		log.info("------------------------------BuildFileFromTemplate task started ----------------- ");
		
		
		if(!isValidExpression(inputFile,execution) || !isValidExpression(outputFileName,execution))
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Input or Output template file is null");

		String inputFileName = inputFile.getValue(execution).toString();
		
		String outputFileNameVal = outputFileName.getValue(execution).toString();	
		
		try
		{
		

			//String templateMap = templateVariableMap.getExpressionText();
	
			InputStream is = FileUtils.loadFileFromWorkDir(inputFileName);
	
			String fileContets = FileUtils.loadStreamAsString(is);
			
			log.info(" Contetns of file : "+ inputFileName+ " loaded size : "+fileContets.length());
			
			if(fileContets!=null)
			{
				String workDir = FileUtils.createWorkSubDir(execution.getProcessInstanceId());
				
				log.info("Temp work directory for instance created : "+workDir);
				
				ExpressionFactory factory = ExpressionFactory.newInstance();
	
				SimpleContext context = new SimpleContext(); // more on this here...
				
				Map<String,Object> templateValueMap = (Map<String,Object>)execution.getVariable("templateMap");
				
				for(Map.Entry<String,Object> entry:templateValueMap.entrySet()){
					log.info(entry.getKey() +"->> "+entry.getValue());
					context.setVariable(entry.getKey(),
							factory.createValueExpression(
							entry.getValue().toString(),String.class));

				}				
			 
				ValueExpression e = factory
						.createValueExpression(context,fileContets,String.class);
				
				execution.setVariable("workDirFolder", "workspace/"+execution.getProcessInstanceId());
				
	 			boolean result = FileUtils.writeFileToDir(workDir+"/"+outputFileNameVal, e.getValue(context).toString());
	 			
	 			
				String fullOpFileName=AppConfigLoader.getAppConfigDir()+"/workspace/"+execution.getProcessInstanceId()+"/"+outputFileNameVal;

				
	 			// set outputFileName for later use
 	 			execution.setVariable("outputFileName", fullOpFileName);
	 			 
	 			
	 			log.info("Result of writing file"+inputFileName +" is "+result); // --> 1
	 			
			}
		}catch (Throwable e) {
			log.error("Error in BuildFileFromTemplate task",e.getMessage());
			e.printStackTrace();
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Exception in BuildFileFromTemplate");
		}
		 

		log.info("------------------------------BuildFileFromTemplate task Ended ----------------- ");

	}

	 
	private boolean isValidExpression(Expression expr,
			DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr
				.getExpressionText() != null));
	}
}