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

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.config.ConfigurationServiceLocal;
import com.ericsson.oss.nfe.ejb.ProcessInstanceProgressServiceLocal;
import com.ericsson.oss.nfe.persistence.entity.ExtendedProcessDefinition;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity.ProcessTriggerWorkflowEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerEventEntity;

/*
 * Rest Services 
 *  
 * Resources are served relative to the servlet path specified in the {@link ApplicationPath}
 * annotation.
 * 
 */
@Path("/nfv_resource")
@RequestScoped
public class WorkflowDeploymentExtensionResource {

	private final Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Inject
	private ProcessInstanceProgressServiceLocal processInstanceProgressServiceLocal;
	
	@Inject
	private ConfigurationServiceLocal configBean;

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/process-definition-ext")
	public Response getAllProcessDefinitions(@DefaultValue("0") @QueryParam("firstResult") String firstResult,
			@DefaultValue("100") @QueryParam("maxResults") String maxResults, @QueryParam("user") String userType, @HeaderParam("X-Tor-UserID") String torUserId) {

		logger.debug(torUserId + " - invoking get definitions for /process-definition-ext ");
		
		if(torUserId == null)
			torUserId="default";

		if (userType == null) {
			userType = "admin";
			//userType = getUserType(torUserId);
			logger.debug(" user ***********************"+ torUserId +" user type ****************************  "+ userType);
		}
		
		List<ExtendedProcessDefinition> definitions = processInstanceProgressServiceLocal
				.getProcessDefinitions("asc", firstResult, maxResults, userType);
		
		ResponseBuilder builder = Response.ok(definitions);		
		CacheControl cc = new CacheControl();
		cc.setMaxAge(3*60);
		cc.setPrivate(true);
		cc.setNoStore(false);
		builder.cacheControl(cc).build();
		
		return builder.build();

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getTORUserId")
	public Response getTORUserId(@HeaderParam("X-Tor-UserID") String torUserId) {

		logger.debug(torUserId + " - invoking getTORUserId ");
		
		
		String defaultUser = configBean.getConfigByKey("default.tor.user");
		
		defaultUser= defaultUser!=null?defaultUser:"wfs-user";
		
		String response = (torUserId!=null && torUserId.trim().length()>0)?torUserId: "wfs-user";
		
 		return Response.ok(response).build();
	}
	
	
	private String getUserType(String torUserId) {
		return configBean.getUserType(torUserId);
	}


	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/AllProcessTriggerDefinitions")
	public List<ProcessTriggerDefinitionEntity> getAllProcessTriggerDefinitions() {
		
		logger.debug("invoking getAllProcessTriggerDefinitions for /AllProcessTriggerDefinitions");
		return processInstanceProgressServiceLocal.getAllTriggerDefinitions();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/AllTriggerEvents")
	public List<ProcessTriggerEventEntity> getAllTriggeredEvnets() {
		
		logger.debug("invoking getAllTriggeredEvnets for /AllTriggerEvents");
		return processInstanceProgressServiceLocal.getAllTriggerEvents();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ProcessTriggerDefinitions/{triggerType}")
	public List<ProcessTriggerWorkflowEntity> getMappedWorkFlowDefinitionsForFilter(final @PathParam("triggerType") String triggerType,
			@DefaultValue("%%") @QueryParam("filterString") String filterString) { 
		return processInstanceProgressServiceLocal.getMappedWorkFlowDefinitions(triggerType, filterString) ;
	}*/

}