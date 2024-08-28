/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.nfe.rest.resources;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.ConnectionFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Rest Services 
 *  
 * Resources are served relative to the servlet path specified in the {@link ApplicationPath}
 * annotation.
 * 
 */
@Path("/resource")
@RequestScoped
public class DummyMessageRestResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "java:/queue/wfsInternalDeploymentEventQueue")
	private Queue wfsdeploymentTopic;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("sendMessage")
	public String sendMessage() {
		this.logger.debug("Send message...");
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			final Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			final MessageProducer messageProducer = session
					.createProducer(wfsdeploymentTopic);
			connection.start();
			 
			final Message message = session.createTextMessage("Test message sent at : "+System.currentTimeMillis());
			messageProducer.send(message);
			

		} catch (JMSException e) {
			logger.error("Failed to send work flow event via JMS.", e);
		} finally {
			try {
				connection.close();
			} catch (JMSException e) {
				logger.error("Failed to close JMS Connection", e);
			}
		}
		return "ok";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getStats")
	public String getStats() {
		return "Sent " + 0;
	}

}