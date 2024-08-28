package com.ericsson.oss.nfe.wfs.extn;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMSUtil {

	private static final Logger logger = LoggerFactory.getLogger(JMSUtil.class);

	private static  String DEFAULT_CONNECTION_FACTORY = "java:/JmsXA";// "java:jms/RemoteConnectionFactory";java:jboss/exported/jms/RemoteConnectionFactory
	private static  String DEFAULT_DESTINATION = "java:jms/queue/wfsInternalDeploymentEventQueue";

	private static  String DEFAULT_USERNAME = "jmsuser";
	private static  String DEFAULT_PASSWORD = "password123!";

	public void sendMessage(String jsmDesitination, String messageString)
			throws Exception {

		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		Destination destination = null;
		TextMessage message = null;
		Context context = null;
		
		Properties loadProps = loadProps();

		try {
			// Set up the context for the JNDI lookup
			final Properties env = new Properties();

			context = new InitialContext(loadProps);

			logger.debug("Intial context found : " + context);
  
			// Perform the JNDI lookups
			String connectionFactoryString = System.getProperty(
					"connection.factory", DEFAULT_CONNECTION_FACTORY);

			logger.debug("Attempting to acquire destination \""
					+ jsmDesitination + "\"");
			destination = (Destination) context.lookup(jsmDesitination);

			logger.debug("Found destination \"" + jsmDesitination
					+ "\" in JNDI");

			logger.debug("Attempting to acquire connection factory \""
					+ connectionFactoryString + "\"");
			connectionFactory = (ConnectionFactory) context
					.lookup(connectionFactoryString);
			logger.debug("Found connection factory \""
					+ connectionFactoryString + "\" in JNDI");

			// Create the JMS connection, session, producer, and consumer
			connection = connectionFactory.createConnection(DEFAULT_USERNAME,
					DEFAULT_PASSWORD);
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(destination);
			connection.start();

			logger.debug("Sending messages with content: " + messageString);

			// Send the specified number of messages

			message = session.createTextMessage(messageString);
			producer.send(message);
			
			logger.debug("message sent successfully!!");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			if (context != null) {
				context.close();
			}

			// closing the connection takes care of the session, producer, and
			// consumer
			if (connection != null) {
				connection.close();
			}

		}
	}
	
	private Properties loadProps()
	{
		Properties pros = null;
		try {
			InputStream settingsStream = this.getClass().getClassLoader().getResourceAsStream("jndi.properties");
			pros = new Properties();
			pros.load(settingsStream);
			
			System.out.println("JNDI Pros : "+pros);
			
			DEFAULT_CONNECTION_FACTORY=pros.getProperty("DEFAULT_CONNECTION_FACTORY")!=null?
					pros.getProperty("DEFAULT_CONNECTION_FACTORY"):DEFAULT_CONNECTION_FACTORY;
					
			DEFAULT_USERNAME=pros.getProperty("JMS_USERNAME")!=null?
					pros.getProperty("JMS_USERNAME"):DEFAULT_USERNAME;
					
			DEFAULT_PASSWORD=pros.getProperty("JMS_PASSWORD")!=null?
					pros.getProperty("JMS_PASSWORD"):DEFAULT_PASSWORD;
					
		} catch (IOException e) {
			System.err.println("Error in loading jndi.props"+e);
		}
		return pros;
	}
	
	public static void main(String[] args) throws Exception {

		/*JSONObject compositeJSONObj = buildDeploymentCommand();
		new JMSUtil().sendMessage("java:jms/queue/wfsInternalDeploymentEventQueue",
				compositeJSONObj.toJSONString());*/
		
		JSONObject compositeJSONObj = buildTriggerCommand();
		new JMSUtil().sendMessage("java:jms/queue/wfsTiggerEventQueue",
				compositeJSONObj.toJSONString());
	}

	private static JSONObject buildTriggerCommand() {
		JSONObject compositeJSONObj = new JSONObject();

		compositeJSONObj.put("triggerName", "sampleAlarm");
		compositeJSONObj.put("triggerType", "FMAlarm");
		compositeJSONObj.put("filterString", "viggsfILTER");
		return compositeJSONObj;
	}
	
	private static JSONObject buildDeploymentCommand() {

		JSONObject compositeJSONObj = new JSONObject();

		compositeJSONObj.put("workflow-key", "wf-2");
		JSONArray triggers = new JSONArray();
		JSONObject trigger = new JSONObject();
		trigger.put("triggerName", "vigTrigger");
		trigger.put("triggerType", "FMAlarm");
		trigger.put("filterString", "test-1");
		triggers.add(trigger);
		
		/*JSONObject trigger2 = new JSONObject();
		trigger2.put("triggerName", "vigTrigger");
		trigger2.put("triggerType", "FMAlarm");
		trigger2.put("filterString", "test2");
		triggers.add(trigger2);*/
		
		compositeJSONObj.put("triggers", triggers);
		return compositeJSONObj;
	}

}
