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

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.ejb.ProcessInstanceProgressServiceLocal;
import com.ericsson.oss.nfe.persistence.entity.ExtendedProcessInstance;
import com.ericsson.oss.nfe.persistence.entity.ProcessDeploymentEntity;

/*
 * Rest Services 
 *  
 * Resources are served relative to the servlet path specified in the {@link ApplicationPath}
 * annotation.
 * 
 */
@Path("/progress-service")
@RequestScoped
public class WorkflowProcessProgressResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ProcessInstanceProgressServiceLocal processInstanceProgressServiceLocal;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/process-instance/{definitionID}")
	public List<ExtendedProcessInstance> getProcessInstancesForDefinitionId(final @PathParam("definitionID") String wfDefinitionId, @DefaultValue("desc") @QueryParam("sortOrder") String order,
			@QueryParam("instance") String processInstance) {

		return processInstanceProgressServiceLocal.getProcessInstanceForDefinitionId(wfDefinitionId, order);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/process-deployments")
	public List<ProcessDeploymentEntity> getProcessDeployments() {

		return processInstanceProgressServiceLocal.getAllProcessDeployments();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/process-instance/{processInstanceId}/ancestry")
	public List<JSONObject> getProcessAncestry(final @PathParam("processInstanceId") String processInstanceId) {

		List<JSONObject> resultArray = new LinkedList<JSONObject>();
		List<ExtendedProcessInstance> processInstances = processInstanceProgressServiceLocal.getProcessAncestry(processInstanceId);

		// Correct the level and only sent required fields to UI
		int depth = processInstances.size() - 1;
		for (int index = processInstances.size(); index > 0; index--) {
			JSONObject itr = new JSONObject();
			ExtendedProcessInstance currentInst = processInstances.get(index - 1);
			itr.put("level", depth + currentInst.getLevel());
			itr.put("workflowDefinitionId", currentInst.getWorkflowDefinitionId());
			itr.put("workflowDefinitionName", currentInst.getWorkflowDefinitionName());
			itr.put("id", currentInst.getId());
			itr.put("superProcessInstanceId", currentInst.getSuperProcessInstanceId());
			resultArray.add(itr);

		}
		return resultArray;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/process-instance/status/{processInstanceId}")
	public ExtendedProcessInstance getInstanceStatus(final @PathParam("processInstanceId") String processInstanceId) {
		ExtendedProcessInstance result = processInstanceProgressServiceLocal.getInstanceStatus(processInstanceId);

		return result;

	}

}