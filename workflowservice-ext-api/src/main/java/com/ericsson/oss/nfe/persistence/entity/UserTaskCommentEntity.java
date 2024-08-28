package com.ericsson.oss.nfe.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class UserTaskCommentEntity implements Serializable {
	
	
	/**
	 * @Auto generated
	 */
	private static final long serialVersionUID = 3218633797816879858L;
	
	String id;
	String taskId;
	String userId;
	Date createdTime;
	String comment;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void addComment(String comment) {
		this.comment = comment;
	}
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "UserTaskCommentEntity [id=" + id + ", taskId=" + taskId
				+ ", userId=" + userId + ", createdTime=" + createdTime
				+ ", comment=" + comment + "]";
	}
	
}
