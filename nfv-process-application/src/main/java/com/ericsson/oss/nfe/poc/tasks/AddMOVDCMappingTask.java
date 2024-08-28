package com.ericsson.oss.nfe.poc.tasks;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.utils.FileUtils;

public class AddMOVDCMappingTask implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String MO_VDC_MAPPING_FILE = "MO_VDCID.properties";

	private Expression moName;
 
	private Expression vdcId;

	public void execute(DelegateExecution execution) {

		log.info("------------------------------AddMOVDCMappingTask task started ----------------- ");
		
		
		if(!isValidExpression(vdcId,execution) || !isValidExpression(moName,execution))
		{
			//log and return
			log.info(" AddMOVDCMappingTask missing input params, just returning without any action !!");
			return;
		}

			
		String moNameVal = moName.getValue(execution).toString();
		
		String vdcIDVal = vdcId.getValue(execution).toString();	
		
		try
		{
		
			boolean result = FileUtils.addKeyValuetoAppConfig(MO_VDC_MAPPING_FILE, moNameVal, vdcIDVal);
			
				 
  			log.info("Result of writing file"+MO_VDC_MAPPING_FILE +"is "+result); 
			
		}catch (Throwable e) {
			log.error("Error in AddMOVDCMappingTask task",e.getMessage());
			//Not throwing as its ok to fail and still overall process can run fine			
		}		 

		log.info("------------------------------AddMOVDCMappingTask task Ended ----------------- ");

	}

	 
	private boolean isValidExpression(Expression expr,
			DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr
				.getExpressionText() != null));
	}
}