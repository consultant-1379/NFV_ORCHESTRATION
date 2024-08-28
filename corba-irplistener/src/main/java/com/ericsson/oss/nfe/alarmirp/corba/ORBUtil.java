/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.nfe.alarmirp.corba;

import java.util.Properties;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POAHelper;

public class ORBUtil {
	
	
	private org.omg.CORBA.ORB orb; 
	private org.omg.PortableServer.POA rootPOA;
	public void initORB(){
		Properties props = new Properties();

		props.put("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
		props.put("org.omg.CORBA.ORBSingletonClass",
				"org.jacorb.orb.ORBSingleton");

		orb = org.omg.CORBA.ORB.init(new String[0], props);
		try {
			rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		} catch (InvalidName e) {
			e.printStackTrace();
		}
	}
	public org.omg.CORBA.ORB getOrb() {
		return orb;
	}
	public org.omg.PortableServer.POA getRootPOA() {
		return rootPOA;
	}
}
