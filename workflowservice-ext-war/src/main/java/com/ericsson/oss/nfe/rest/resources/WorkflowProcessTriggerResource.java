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

import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.ejb.ProcessInstanceProgressServiceLocal;
import com.ericsson.oss.nfe.ejb.TriggerDefinitionServiceBeanLocal;
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
@Path("/ProcessTrigger")
@RequestScoped
public class WorkflowProcessTriggerResource {

	private final Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Inject
	private ProcessInstanceProgressServiceLocal processInstanceProgressServiceLocal;
	
	@Inject
	private TriggerDefinitionServiceBeanLocal triggerDefinitionBeanLocal;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Definitions")
	public List<ProcessTriggerDefinitionEntity> getAllProcessTriggerDefinitions() {
		
		logger.debug("invoking getAllProcessTriggerDefinitions for /Definitions");
		return processInstanceProgressServiceLocal.getAllTriggerDefinitions();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/Definitions")
	public Response addProcessTriggerDefinition(ProcessTriggerDefinitionEntity entity) {
		
		logger.debug("invoking addProcessTriggerDefinition for /Definitions");
		
		triggerDefinitionBeanLocal.insertTriggerDefinition(entity);
		return Response.ok("Added Process Trigger Definition into database").build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/Definitions")
	public Response updateProcessTriggerDefinition(ProcessTriggerDefinitionEntity entity){
		logger.debug("invoking updateProcessTriggerDefinition for /Definitions");
		triggerDefinitionBeanLocal.updateTriggerDefinition(entity);
		return Response.ok("Added Process Trigger Definition into database").build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Definitions/{definitionId}")
	public String getProcessTriggerDefinitions(final @PathParam("definitionId") String definitionId) {
		
		logger.debug("invoking getAllProcessTriggerDefinitions for /Definitions/{definitionId} " + definitionId);
		
		if(definitionId!=null && ! isInteger(definitionId))
		{
			return "definitionId should be numeric";
		}
		
		int definitionIdInt = Integer.parseInt(definitionId);
				
		List<ProcessTriggerDefinitionEntity> allTriggerDefinitions = processInstanceProgressServiceLocal.getAllTriggerDefinitions();
		for (Iterator iterator = allTriggerDefinitions.iterator(); iterator
				.hasNext();) {
			ProcessTriggerDefinitionEntity processTriggerDefinitionEntity = (ProcessTriggerDefinitionEntity) iterator
					.next();
			if ( processTriggerDefinitionEntity.getId() == definitionIdInt )
			{
				return processTriggerDefinitionEntity.toString();
			}
			
		}
		return "{Doesn't Exist}";
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json")
	@Path("/Definitions/{definitionId}/filter")
	public String postProcessTriggerDefinitionsFilter(Filter input, final @PathParam("definitionId") String definitionId) {
		
		logger.info("invoking postProcessTriggerDefinitionsFilter for /Definitions/{definitionId}/filter " + definitionId + " input " + input);
		
		if(definitionId!=null && ! isInteger(definitionId))
		{
			return "{definitionId should be numeric}";
		}
		
		int definitionIdInt = Integer.parseInt(definitionId);
				
		List<ProcessTriggerDefinitionEntity> allTriggerDefinitions = processInstanceProgressServiceLocal.getAllTriggerDefinitions();
		for (Iterator iterator = allTriggerDefinitions.iterator(); iterator
				.hasNext();) {
			ProcessTriggerDefinitionEntity processTriggerDefinitionEntity = (ProcessTriggerDefinitionEntity) iterator
					.next();
			
			
			if ( processTriggerDefinitionEntity.getId() == definitionIdInt )
			{
				processTriggerDefinitionEntity.setFilterString(input.getFilter());
				ProcessTriggerDefinitionEntity setProcessTriggerDefinitionEntity = processInstanceProgressServiceLocal.setProcessTriggerDefinitionEntity(processTriggerDefinitionEntity);
				return setProcessTriggerDefinitionEntity.toString();
			}
			
		}
		return "{Doesn't Exist}";
	}
	

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/TriggerEvents")
	public List<ProcessTriggerEventEntity> getAllTriggeredEvnets() {
		
		logger.debug("invoking getAllTriggeredEvnets for /TriggerEvents");
		return processInstanceProgressServiceLocal.getAllTriggerEvents();
	}
	
	//@QueryParam("firstResult") String firstResult,
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ProcessTriggerDefinitions/{triggerType}")
	public List<ProcessTriggerWorkflowEntity> getMappedWorkFlowDefinitionsForFilter(final @PathParam("triggerType") String triggerType,
			@DefaultValue("%%") @QueryParam("filterString") String filterString) { 
		return processInstanceProgressServiceLocal.getMappedWorkFlowDefinitions(triggerType, filterString) ;
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Definitions/{definitionId}")
	public String deleteProcessTriggerDefinition(final @PathParam("definitionId") String definitionId)
	{
		logger.debug("invoking deleteProcessTriggerDefinition for /Definitions id : "+definitionId);
		
		if(definitionId!=null && ! isInteger(definitionId))
		{
			return "definitionId should be numeric";
		} 	
		
		return processInstanceProgressServiceLocal.
				deleteProcessTriggerDefinition(definitionId); 		 
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Definitions/WorkflowMapping/{triggerId}")
	public String deleteProcessTriggerWorkflowEntity(final @PathParam("triggerId") String triggerId)
	{
		logger.debug("invoking deleteProcessTriggerWorkflowEntity for /TriggerEvents id : "+triggerId);	
		
		if(triggerId!=null && ! isInteger(triggerId))
		{
			return "WorkflowMapping Id should be numeric";
		}
		
		return processInstanceProgressServiceLocal.
				deleteProcessTriggerWorkflowEntity(triggerId); 
		 
	}
	
	private boolean isInteger(String value)
	{
		boolean isInteger = false;		
		try
		{
			Integer.parseInt(value);
			isInteger = true;
		}catch(Exception ex){isInteger = false;}
		return isInteger;
	}

}