package com.ericsson.oss.nfe.ejb;

import java.util.Map;

import javax.ejb.Stateless;

@Stateless
public class SampleRemoteServiceBean implements SampleRemoteServiceRemote {

	@Override
	public void execution(Map<String, Object> executionVariables) {
		System.out.println("SampleRemoteServiceBean " + executionVariables );

	}

}
