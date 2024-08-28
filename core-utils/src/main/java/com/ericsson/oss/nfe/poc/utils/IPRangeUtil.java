package com.ericsson.oss.nfe.poc.utils;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

public class IPRangeUtil {

	private static String ip;
	private static int prefix;

	public static String getSubnetMask(String ipRange) {

		if (!verifyIPRange(ipRange)) {
			return "invalid ip range provided, ip range should be in cidr notation form - n.n.n.n/m";
		}

		String netmask = "";
		int value = 0xffffffff << (32 - prefix);
		byte[] bytes = new byte[] { (byte) (value >>> 24), (byte) (value >> 16 & 0xff), (byte) (value >> 8 & 0xff), (byte) (value & 0xff) };
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			netmask += (b < 0 ? 256 + b : b);
			if (i < 3)
				netmask += ".";
		}

		return netmask;
	}
	
	public static String getDefaultGateway(String ipRange) {
		if (!verifyIPRange(ipRange)) {
			return "invalid ip range provided, ip range should be in cidr notation form - n.n.n.n/m";
		}
		
		String[] ipSplit = ip.split("\\.");
		Integer assignable = Integer.parseInt(ipSplit[3]) + 1;
		ipSplit[3] = assignable.toString();
		
		return StringUtils.join(ipSplit, ".");
	}
	
	public static String getNextAssignableIP(String ipRange){
		if (!verifyIPRange(ipRange)) {
			return "invalid ip range provided, ip range should be in cidr notation form - n.n.n.n/m";
		}

		String[] ipSplit = ip.split("\\.");
		Integer assignable = Integer.parseInt(ipSplit[3]) + 2;
		ipSplit[3] = assignable.toString();

		return StringUtils.join(ipSplit, ".");
	}

	public static String getAddedIpValue(String ipRange, int add) {
		if (!verifyIPRange(ipRange)) {
			return "invalid ip range provided, ip range should be in cidr notation form - n.n.n.n/m";
		}

		String[] ipSplit = ip.split("\\.");
		Integer route = Integer.parseInt(ipSplit[3]) + add;
		ipSplit[3] = route.toString();

		return StringUtils.join(ipSplit, ".");
	}
	
	
	public static String[] getBasePrefix(String ipRange) { 

		 
		String[] ipSplit = ipRange.split("/")[0].split("\\.");  

		return new String[]{StringUtils.join( Arrays.copyOf(ipSplit,3), "."),ipSplit[3]};
	}

	private static boolean verifyIPRange(String ipRange) {

		String[] parts = ipRange.split("/");
		ip = parts[0];

		if (parts.length < 2) {
			return false;
		} else {
			prefix = Integer.parseInt(parts[1]);
		}

		return true;
	}
	
	public static String getRouteIP(String ipRange) {
		if (!verifyIPRange(ipRange)) {
			return "invalid ip range provided, ip range should be in cidr notation form - n.n.n.n/m";
		}
		String[] splitSubnet = ipRange.split("/");
		String[] routeIPparts = splitSubnet[0].split("\\.");
		return "255.255.255."+routeIPparts[3];
	}

	
	public static void main(String[] args) {
		System.out.println("10.10.12.13/25 ==> "+getBasePrefix("10.10.12.13/25")[0]);
		
		System.out.println("10.10.12.13/25 ==> "+getAddedIpValue("10.70.242.128/25",33));
	}
}
