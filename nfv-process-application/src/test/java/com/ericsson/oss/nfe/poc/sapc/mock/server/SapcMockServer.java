package com.ericsson.oss.nfe.poc.sapc.mock.server;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import org.mockserver.client.server.MockServerClient;

public class SapcMockServer {
	
	private static MockServerClient client;
	
	private static final int DEFAULT_PORT = 7777;
	
	private static boolean isStarted = false;

	private SapcMockServer() {
	}

	static public void configureServer(final int port) {
		client = new MockServerClient("localhost", port);
		configure();
		client.dumpToLog();
	}

	private static void configure() {
		try {
			SapcMockBuilder.buildExpectedBehaviour(client);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	public static void start() {
		start(DEFAULT_PORT);
	}
	
	public static void start(int port) {
		startClientAndServer(port);
		configureServer(port);
		isStarted = true;
	}
	
	public static void stop() {
		client.stop();
		isStarted = false;
	}
	
	public static boolean isStarted() {
		return isStarted;
	}
	
	public static void main(String[] args) {
		System.out.println("Starting SAPC MockServer.");
		start();
		System.out.println("Started SAPC MockServer.");
	}


}
