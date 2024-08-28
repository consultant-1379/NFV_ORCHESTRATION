package com.ericsson.oss.nfe.poc.model.sapc;

import com.ericsson.oss.nfe.poc.model.Line;

public class SapcSubscriberLine implements Line{
	
	private String subscriberId;
	private String groupId;

	public SapcSubscriberLine(){
	}
	
	public SapcSubscriberLine(String subscriberId, String groupId){
		this.subscriberId = subscriberId.trim();
		this.groupId = groupId.trim();
	}
	
	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public boolean isValid() {
		return isSubscriberIdNumeric() && isGroupIdValid();
	}

	private boolean isGroupIdValid() {
		return groupId != null && !groupId.isEmpty();
	}

	private boolean isSubscriberIdNumeric() {
		return subscriberId != null && !subscriberId.isEmpty() && isNumber();
	}

	private boolean isNumber() {
		try {
			Long.parseLong(subscriberId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SapcSubscriberLine [subscriberId=" + subscriberId
				+ ", groupId=" + groupId + "]";
	}
	
	
}
