package com.ericsson.oss.nfe.poc.tasks;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import org.mockserver.client.server.MockServerClient;

public class MockServer {
	
	private static MockServerClient client;
	
	private static final int DEFAULT_PORT = 8888;
	
	private static boolean isStarted = false;

	private MockServer() {
	}

	static public void configureServer(final int port) {
		client = new MockServerClient("localhost", port);
		configure();
		client.dumpToLog();
	}

	private static void configure() {
		try {
			MockBuilder.buildExpectedBehaviour(client);
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
		System.out.println("Starting..");
		start();
		System.out.println("Started");
	}

}
