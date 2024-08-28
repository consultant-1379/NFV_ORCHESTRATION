package com.ericsson.oss.nfe.poc.tasks.script;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.python.util.PythonInterpreter;

import com.ericsson.oss.nfe.poc.core.ApplicationConstants;

public class PythonExecutor implements JavaDelegate {

	private Expression filePath;

	private Expression output;

	private ScriptEngineManager manager;
	private ScriptEngine engine;

	public PythonExecutor() {
		
		super();
		manager = new ScriptEngineManager();
		
		 //forcefully register jython
		 if(manager.getEngineByName("python")==null)
			 manager.registerEngineName("python", new org.python.jsr223.PyScriptEngineFactory());
		 
		engine = manager.getEngineByName("python");
		
		System.out.println("PythonExecutor constructor engine : "+engine);
	}

	public void execute(DelegateExecution execution) throws Exception {
		if (isValidExpression(filePath, execution)) {

			executeScript(execution);

		} else {
			throw new BpmnError(ApplicationConstants.BPMN_RUNTIME_ERROR, "Python task script filepath is required");

		}
	}

	private void executeScript(DelegateExecution execution) {
		
		String filePathValue = (String) filePath.getValue(execution);
		
 		engine = manager.getEngineByName("python");		
		
		String outputVar = (String) output.getValue(execution);

		StringWriter writer = new StringWriter(); // ouput will be stored here

		ScriptContext context = new SimpleScriptContext();

		context.setWriter(writer); // configures output redirection
		
		Map<String, String> paramsMap = (Map<String, String>) execution.getVariable("scriptInputMap");

		try {

			SimpleBindings vars = new SimpleBindings();

			context.setBindings(vars, ScriptContext.ENGINE_SCOPE);
			
			if(paramsMap!=null && !paramsMap.isEmpty())
			{
					vars.putAll(paramsMap);	
				
			}

			engine.eval(
					new FileReader(new File(filePathValue)),context);
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			  for(String key : vars.keySet())
		        	if(key.startsWith("out"))
		        	{
		        		System.out.println(key +" : "+ vars.get(key));
		        		resultMap.put(key, vars.get(key));
		        	}
			  
			 
			System.out.println("Sricpt out put ----------------------------------> "+writer.toString());
			
			execution.setVariable(outputVar, writer.toString());
			
			execution.setVariable("scriptOutputMap",resultMap);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}

	}

	private void runPy(InputStream pyinput) {
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile(pyinput);

	}

	private boolean isValidExpression(Expression expr, DelegateExecution delegateExecution) {

		return (expr != null && (expr.getValue(delegateExecution) != null || expr.getExpressionText() != null));

	}

}
