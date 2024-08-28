package com.ericsson.oss.nfe.poc.tasks;

//import org.camunda.bpm.engine.delegate.BpmnError;
import java.util.HashMap;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
public class GenericLogTask implements JavaDelegate {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private static HashMap <String,Integer> potentialLeak = new HashMap <String,Integer>();
	// ${name}
	/*
	 * @Override public void executeTask(final TaskExecution execution) throws
	 * WorkflowServiceException {
	 * 
	 */
	
	private Expression nodeName;
	
	public void execute(DelegateExecution execution) throws Exception{

		String loggingNode = "";
		if(nodeName!=null)
		{
			loggingNode =  nodeName.getValue(execution).toString();
		}
		
		log.info("------------------------------GenericLogTask task "+loggingNode +" started ----------------- "
				+ execution.getProcessDefinitionId()
				+ "[ "
				+ execution.getProcessInstanceId() + " ]");
		

		System.out.println("------------------------------GenericLogTask task "+loggingNode +" started ----------------- "
				+ execution.getProcessDefinitionId()
				+ "[ "
				+ execution.getProcessInstanceId() + " ]");
		 

		System.out.println("Time[ " +System.currentTimeMillis() + " ] Variables : "+ execution.getVariable("counter"));

		log.info("------------------------------GenericLogTask task "+loggingNode +" ended ----------------- ");
		
		int method = this.getIntVar(execution,"method"); 
		
		int counter = 1;
		if(potentialLeak.get("counter")==null)
		{
			potentialLeak.put("counter",1);
		}
		else
		{
			counter = (Integer) potentialLeak.get("counter");
			potentialLeak.put("counter",counter+1);			
		}
		
		if(method==1) //No error so throw error for 2 cycles and pass at 3
		{
			System.out.println("Method 1 pass at first shot");
			return;
		}
		else if(method==2)  // throw bpmn for 2 cycles and then at 3 throw irrecoverable error
		{
			System.out.println("Method 2 fail irrec "+counter);
			//if(counter==2)
				throw new BpmnError("IRREC");
			//else
			//	throw new BpmnError("Retry");
		}
		else  //throw bpmn for 2 cycles and then success
		{
			System.out.println("Method 3 retry 2 and then pass"+counter);
			if(counter<2)
				throw new BpmnError("Retry");
		}
	
		/*Integer counter = (Integer)potentialLeak.get(execution.getProcessInstanceId()+"-counter");
		if (counter == null)
		{
			counter = new Integer(0);
		}
		counter = counter + 1;
		potentialLeak.put(execution.getProcessInstanceId()+"-counter",counter);
		if (counter < 4)
		{
			throw new Exception("exception  " + counter);
		}*/
	}
	
	
	private int getIntVar(DelegateExecution execution, String name)
	{
		
		int counter = 1;
		String temp = (String) execution.getVariable(name);
		try
		{
			counter=Integer.parseInt(temp);
			
		}catch(Exception e){}
		
		return counter;
		
	}

}