package com.ericsson.oss.nfe.api;

import java.util.List;

import com.ericsson.oss.nfe.persistence.entity.UserTaskCommentEntity;

/**
 * @author ejamcud
 *
 */
public interface UserTaskCommentService {
	
	/**
	 * gets a list of all comments for a given usertask
	 * 
	 * @param taskId - id of the usertask
	 * @return a list of comments
	 */
	public List<UserTaskCommentEntity> getUserTaskComment(String taskId);
	
	/**
	 * post comment to a usertask
	 * 
	 * @param userTaskComment - object containing info to post id, user, comment, etc
	 */
	public void addUserTaskComment(UserTaskCommentEntity userTaskComment);
	
	/**
	 * post comment to a usertask
	 * 
	 * @param id - the task id
	 * @param user - the user id
	 * @param comment - the comment to post
	 */
	public void addUserTaskComment2(String id, String user, String comment); 
}
