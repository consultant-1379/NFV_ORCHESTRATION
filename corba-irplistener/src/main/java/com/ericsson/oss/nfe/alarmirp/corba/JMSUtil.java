package com.ericsson.oss.nfe.alarmirp.corba;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.alarmirp.ConfigUtils;

public class JMSUtil {

	private static final Logger logger = LoggerFactory.getLogger(JMSUtil.class);

	private static String jmsConnectionFactory = "java:jms/RemoteConnectionFactory";// "java:jms/RemoteConnectionFactory";
	private static String appServerUser;
	private static String appServerPassword;
	private static Map<String,String> propsMap;
	private static JMSUtil instance; 

	private JMSUtil() {
		System.out
				.println("---------------------called the singleton JMSUtil-----------------------------");

	}

	public static JMSUtil init(Map<String,String> connectionProps) {
		instance = new JMSUtil();
		JMSUtil.jmsConnectionFactory = connectionProps.get("jmsConnectionFactory");;
		JMSUtil.appServerUser = connectionProps.get("appServerUser");
		JMSUtil.appServerPassword = connectionProps.get("appServerPassword");
		propsMap = new HashMap<>();
		propsMap.putAll(connectionProps);
		return instance;
	}

	public void sendMessage(String jsmDesitination, String messageString)
			throws Exception {

		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		Destination destination = null;
		TextMessage message = null;
		Context context = null;

		try {
			// Set up the context for the JNDI lookup
			final Properties jndiProps = new Properties();
			
			jndiProps.put(Context.PROVIDER_URL,propsMap.get("java.naming.provider.url"));
			jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,propsMap.get("java.naming.factory.initial"));		
			jndiProps.put(Context.SECURITY_PRINCIPAL,propsMap.get("appServerUser"));		
			jndiProps.put(Context.SECURITY_CREDENTIALS,propsMap.get("appServerPassword"));		
			
			//jndiProps.put("jboss.naming.client.ejb.context", true);
			context = new InitialContext(jndiProps);

			System.out.println("Intial context found : " + context);

			// Perform the JNDI lookups

			System.out.println("Attempting to acquire destination \""
					+ jsmDesitination + "\"");
			destination = (Destination) context.lookup(jsmDesitination);

			System.out.println("Found destination \"" + jsmDesitination
					+ "\" in JNDI");

			System.out.println("Attempting to acquire connection factory \""
					+ jmsConnectionFactory + "\"");

			connectionFactory = (ConnectionFactory) context
					.lookup(jmsConnectionFactory);

			System.out.println("Found connection factory \"" + jmsConnectionFactory
					+ "\" in JNDI");

			// Create the JMS connection, session, producer, and consumer
			connection = connectionFactory.createConnection(appServerUser,
					appServerPassword);

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(destination);
			connection.start();

			System.out.println("Sending messages with content: " + messageString);

			// Send the specified number of messages

			message = session.createTextMessage(messageString);
			producer.send(message);

			System.out.println("message sent successfully!!");

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
 	
	public static void main(String[] args) throws Exception {

		JSONObject compositeJSONObj = new JSONObject();

		compositeJSONObj.put("triggerName", "sampleAlarm");
		compositeJSONObj.put("triggerType", "FMAlarm");
		compositeJSONObj.put("filterString", "all=*");
		JMSUtil util = JMSUtil.init(ConfigUtils.instance.getConfigAsMap());
		util.sendMessage("java:jms/queue/wfsTiggerEventQueue",
				"testMessage");
	}
	 
}
