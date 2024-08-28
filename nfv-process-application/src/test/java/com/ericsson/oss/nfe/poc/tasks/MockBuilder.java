package com.ericsson.oss.nfe.poc.tasks;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.Delay;
import org.mockserver.model.Header;

public class MockBuilder {
	
	static public String loadResource(String fileName) throws IOException {
		InputStream is1 = ClassLoader.getSystemResourceAsStream(fileName);
		return IOUtils.toString(is1, "UTF-8");
	}

	public static void buildExpectedBehaviour(final MockServerClient client) throws IOException {
		client.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vdcs*"),
				exactly(4))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("getMMEvdcs.json"))
						.withDelay(new Delay(SECONDS, 5)));
		
		client.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vms*"),
				exactly(4))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("vms.json"))
						.withDelay(new Delay(SECONDS, 5)));
		
		client.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vns*"),
				exactly(4))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("vms.json"))
						.withDelay(new Delay(SECONDS, 5)));


		client.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vms/VM-7653*"),
				exactly(4))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("queryConsoleURL.json"))
						.withDelay(new Delay(SECONDS, 5)));
 
	} 

}
