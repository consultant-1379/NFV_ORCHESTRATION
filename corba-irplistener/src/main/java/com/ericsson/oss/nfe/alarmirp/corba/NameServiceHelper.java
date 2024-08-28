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

import java.util.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

import com.ericsson.oss.nfe.alarmirp.ConfigUtils;

public class NameServiceHelper {
	public static org.omg.CORBA.Object resolve(org.omg.CORBA.ORB orb,
			String path, String name) throws NotFound, CannotProceed,
			InvalidName {
		org.omg.CORBA.Object rootAsObj = orb
				.string_to_object(getNameServiceLocation());

		NamingContext rootCtx = NamingContextHelper.narrow(rootAsObj);
		NameComponent[] pathElements = toNameComponent(path);
		int size = 0;
		if (pathElements != null && pathElements.length > 0) {
			size = pathElements.length;
		}

		NameComponent[] fullName = new NameComponent[size + 1];

		System.arraycopy(pathElements, 0, fullName, 0, pathElements.length);

		fullName[fullName.length - 1] = new NameComponent(name, "");

		return rootCtx.resolve(fullName);
	}

	public static org.omg.CORBA.Object resolve(org.omg.CORBA.ORB orb,
			String path) throws NotFound, CannotProceed, InvalidName {
		org.omg.CORBA.Object rootAsObj = orb
				.string_to_object(getNameServiceLocation());

		NamingContext rootCtx = NamingContextHelper.narrow(rootAsObj);
		NameComponent[] pathElements = toNameComponent(path);
		return rootCtx.resolve(pathElements);
	}

	public static NameComponent[] toNameComponent(String name) {
		NameComponent result[] = new NameComponent[0];
		if (name != null && !name.equals("")) {
			StringTokenizer ncs = new StringTokenizer(name, "/");
			result = new NameComponent[ncs.countTokens()];
			int i = 0;
			while (ncs.hasMoreElements()) {
				String dummy = ncs.nextToken();
				StringTokenizer nc = new StringTokenizer(dummy, ".");
				String id = nc.nextToken();
				String kind = "";
				if (nc.countTokens() >= 1)
					kind = nc.nextToken();
				result[i++] = new NameComponent(id, kind);
			}
		}
		return result;
	}

	private static String getNameServiceLocation() {
		
		System.out.println("getNameServiceLocation resolved is : "+ConfigUtils.instance.getProperty("corbaNameService"));
		
		return ConfigUtils.instance.getProperty("corbaNameService");
	}
}
