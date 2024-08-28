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

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.ejb.UserTaskCommentServiceLocal;
import com.ericsson.oss.nfe.persistence.entity.UserTaskCommentEntity;


// * Rest Services 
// *  
// * Resources are served relative to the servlet path specified in the {@link ApplicationPath}
// * annotation.
// * 
 
@Path("/task-ext")
@RequestScoped
public class WorkflowUserTaskCommentResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private UserTaskCommentServiceLocal userTaskCommentService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{taskId}/comments")
	public List<UserTaskCommentEntity> getAllUserTaskCommentsForTask(final @PathParam("taskId") String taskId) {

		logger.debug("---------------------~WorkflowUserTaskCommentResource : taskId ~-----------------------------------------"+taskId);
		return userTaskCommentService.getUserTaskComment(taskId);
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{taskId}")
	public void addUserComment(final @PathParam("taskId") String taskId, UserTaskCommentEntity userTaskComment) {

		
		System.out.println("---------------------~WorkflowUserTaskCommentResource : userTaskComment ~-----------------------------------------"+userTaskComment);
		logger.debug("---------------------~WorkflowUserTaskCommentResource : taskId ~-----------------------------------------"+taskId);
		logger.debug("---------------------~WorkflowUserTaskCommentResource : userTaskComment ~-----------------------------------------"+userTaskComment);
		if(userTaskComment!=null)
			userTaskComment.setCreatedTime(new Date());
		//userTaskCommentService.addUserTaskComment(userTaskComment);
		
		String commentAndUser =  "[" + userTaskComment.getUserId() + "]" + userTaskComment.getComment();
		
		userTaskCommentService.addUserTaskComment2(userTaskComment.getTaskId(),userTaskComment.getId(),commentAndUser);
		
	}
	 

}