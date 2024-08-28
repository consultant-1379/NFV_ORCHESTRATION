package com.ericsson.oss.nfe.ejb.service;

public abstract class BaseAdapter {
	
	private String triggerType;	
	
	public abstract void setTriggerType(String triggerType);	
	
	public abstract void sendMessage();

}
