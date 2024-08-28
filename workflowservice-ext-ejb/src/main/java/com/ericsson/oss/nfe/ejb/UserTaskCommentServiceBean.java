package com.ericsson.oss.nfe.ejb;

import static org.camunda.bpm.BpmPlatform.APP_JNDI_NAME;
import static org.camunda.bpm.BpmPlatform.JNDI_NAME_PREFIX;
import static org.camunda.bpm.BpmPlatform.MODULE_JNDI_NAME;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.task.Comment;

import com.ericsson.oss.nfe.persistence.entity.UserTaskCommentEntity;

@Stateless
public class UserTaskCommentServiceBean implements UserTaskCommentServiceLocal {
	
//	@Inject
//	private WorkflowServiceHelper helper;
	
	// A reference to the Camunda default process engine
    private static final String JNDI_DEFAULT_PROCESS_ENGINE =  JNDI_NAME_PREFIX + "/" + APP_JNDI_NAME + "/" + MODULE_JNDI_NAME + "/default";
    
    @Resource(mappedName = JNDI_DEFAULT_PROCESS_ENGINE)
    private ProcessEngine processEngine;

	
	@Override
	public List<UserTaskCommentEntity> getUserTaskComment(String taskId) {
		List<UserTaskCommentEntity> list = new ArrayList<UserTaskCommentEntity>();
		List<Comment> comments = processEngine.getTaskService().getTaskComments(taskId);
		for(Comment comment: comments){
			UserTaskCommentEntity userTaskCommentEntity = new UserTaskCommentEntity();
			if(comment.getFullMessage().contains("[")){
				userTaskCommentEntity.setComment(comment.getFullMessage().split("]")[1]);
			}else{
				userTaskCommentEntity.setComment(comment.getFullMessage());
			}
			userTaskCommentEntity.setTaskId(taskId);
			userTaskCommentEntity.setCreatedTime(comment.getTime());
			userTaskCommentEntity.setId(comment.getProcessInstanceId());
			if(comment.getFullMessage().contains("[")){
				userTaskCommentEntity.setUserId(comment.getFullMessage().split("]")[0].substring(1));
			}else{
				userTaskCommentEntity.setUserId("wfs-user");
			}
			list.add(userTaskCommentEntity);
		} 
 		
		return list;
	}

	@Override
	public void addUserTaskComment(UserTaskCommentEntity userTaskComment) {
		processEngine.getTaskService().addComment(userTaskComment.getTaskId(), userTaskComment.getId(), userTaskComment.getComment());
	}
	
	@Override
	public void addUserTaskComment2(String taskId, String processInstanceId, String comment) {
		processEngine.getTaskService().addComment(taskId, processInstanceId, comment);
	}
}
