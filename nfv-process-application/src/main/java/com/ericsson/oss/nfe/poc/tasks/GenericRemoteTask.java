package com.ericsson.oss.nfe.poc.tasks;

//import org.camunda.bpm.engine.delegate.BpmnError;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericRemoteTask implements JavaDelegate {
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
		Object temp = resolveRemote(loggingNode, "");
		
		log.info("Retrieved Object " + temp);
		Class[] cArg = new Class[1];
        cArg[0] = Map.class;
		Class<? extends Object> class1 = temp.getClass();
		Method method = class1.getMethod("execution", cArg);
		method.invoke(temp, execution.getVariables());
		// "java:global/nfv-orchestration-ear-1.0.0/externaltrigger-adapter-ejb-1.0.0/AlarmListPollerBean!com.ericsson.oss.nfe.ejb.service.AlarmListPollerBean"
	}
	
	private  Object resolveRemote(String simpleName, String aviewClassName)
	{
		 final Hashtable jndiProperties = new Hashtable();
	        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	        Context context = null;
			try {
				context = new InitialContext(jndiProperties);
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        // The app name is the application name of the deployed EJBs. This is typically the ear name
	        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
	        // EJB deployment on the server.
	        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
	        final String appName = "";
	        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
	        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
	        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
	        // jboss-as-ejb-remote-app
	        final String moduleName = "jboss-as-ejb-remote-app";
	        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
	        // our EJB deployment, so this is an empty string
	        final String distinctName = "";
	        // The EJB name which by default is the simple class name of the bean implementation class
	        final String beanName = simpleName ; // CalculatorBean.class.getSimpleName();
	        // the remote view fully qualified class name
	        final String viewClassName = aviewClassName; //RemoteCalculator.class.getName();
	        // let's do the lookup
	        Object result = null;
	        try {
				//result = context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	        	result = context.lookup(simpleName);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return result;
	}

}