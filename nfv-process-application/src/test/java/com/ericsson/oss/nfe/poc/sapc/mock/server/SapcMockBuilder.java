package com.ericsson.oss.nfe.poc.sapc.mock.server;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.matchers.Times.unlimited;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.Delay;
import org.mockserver.model.Header;

public class SapcMockBuilder {
	
	static public String loadResource(String fileName) throws IOException {
		InputStream is1 = ClassLoader.getSystemResourceAsStream(fileName);
		return IOUtils.toString(is1, "UTF-8");
	}

	public static void buildExpectedBehaviour(final MockServerClient client) throws IOException {
		client.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vdcs*"),
				unlimited())
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mock/sapc/vdcsQuery.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
		client.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vms*"),
				unlimited())
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mock/sapc/vmQuery.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
		client.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vns*"),
				unlimited())
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mock/sapc/vnsQuery.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
		//http://localhost:7777/ecm_service/offers/SAPCvmOffer
		client.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/offers/SAPCvmOffer*"),
				unlimited())
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mock/sapc/SAPCvmOffer.json"))
						.withDelay(new Delay(SECONDS, 1)));
		//http://localhost:7777/ecm_service/offers/SAPCvdcOffer
				client.when(request()
						.withMethod("GET")
						.withPath("/ecm_service/offers/SAPCvdcOffer*"),
						unlimited())
						.respond(response().withStatusCode(200).withHeaders(
								new Header("Content-Type", "application/json; charset=utf-8"))
								.withBody(loadResource("mock/sapc/SAPCvdcOffer.json"))
								.withDelay(new Delay(SECONDS, 1)));
		//http://localhost:7777/ecm_service/offers/SAPCvappOffer
		client.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/offers/SAPCvappOffer*"),
				unlimited())
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mock/sapc/SAPCvappOffer.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
		client.when(request().
				withMethod("POST")
				.withPath("/ecm_service/orders"),
				unlimited())
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type","application/json; charset=utf-8"))
						.withBody(loadResource("mock/sapc/createOrder.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
		client.when(
				request().withMethod("GET").withPath(
						"/ecm_service/orders/2448259"), unlimited()).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("mock/sapc/verify_active_2448259.json"))
						.withDelay(new Delay(SECONDS, 1)));
 
 
 
	} 

}
