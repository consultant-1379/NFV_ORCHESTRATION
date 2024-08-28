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

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;
import com.ericsson.oss.nfe.poc.utils.FileUtils;

public class BuildStringFromTemplate implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private Expression inputFile;

	// private Expression templateVariableMap;

	private Expression outputVariableName;

	public void execute(DelegateExecution execution) {

		log.info("------------------------------BuildStringFromTemplate task started ----------------- ");

		if (!isValidExpression(inputFile, execution) || !isValidExpression(outputVariableName, execution))
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Input or Output template file is null");

		String inputFileName = inputFile.getValue(execution).toString();

		String outputNameVal = outputVariableName.getValue(execution).toString();

		// String templateMap = templateVariableMap.getExpressionText();
		try {
			InputStream is = FileUtils.loadFileFromWorkDir(inputFileName);

			String fileContets = FileUtils.loadStreamAsString(is);

			log.info(" Contetns of template file : " + inputFileName + " loaded size : " + fileContets.length());

			if (fileContets != null) {
				ExpressionFactory factory = ExpressionFactory.newInstance();

				SimpleContext context = new SimpleContext(); // more on this
																// here...

				Map<String, Object> templateValueMap = (Map<String, Object>) execution.getVariable("templateMap");

				for (Map.Entry<String, Object> entry : templateValueMap.entrySet())
					context.setVariable(entry.getKey(), factory.createValueExpression(entry.getValue().toString(), String.class));

				ValueExpression e = factory.createValueExpression(context, fileContets, String.class);

				execution.setVariable(outputNameVal, e.getValue(context).toString());

			}
		} catch (Throwable e) {
			log.error("Error in BuildStringFromTemplate task", e.getMessage());
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Exception in BuildStringFromTemplate");
		}

		log.info("------------------------------BuildStringFromTemplate task Ended ----------------- ");

	}

	private boolean isValidExpression(Expression expr, DelegateExecution delegateExecution) {
		return (expr != null && (expr.getValue(delegateExecution) != null || expr.getExpressionText() != null));
	}
}