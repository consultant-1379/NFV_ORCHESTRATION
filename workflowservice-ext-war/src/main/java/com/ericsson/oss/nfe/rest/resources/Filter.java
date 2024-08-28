package com.ericsson.oss.nfe.rest.resources;

public class Filter
{
	private String filter;
	
	public Filter() {
		super();
		this.filter = "";
	}
	public Filter(String filter) {
		super();
		this.filter = filter;
	}
	
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	
};