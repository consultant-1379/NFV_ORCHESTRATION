package com.ericsson.pct;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.matchers.Times.once;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Delay;
import org.mockserver.model.Header;
import org.mockserver.model.Parameter;

public class MockServerSimple {

	private MockServerSimple() {
	}

	static public String loadResource(String fileName) throws IOException {

		InputStream is1 = ClassLoader.getSystemResourceAsStream("./mock/"
				+ fileName);
		return IOUtils.toString(is1, "UTF-8");

	}

	static void initMockServer() {
		ClientAndServer mockServer = startClientAndServer(8888);
		configserver();
	}

	static public void configserver() {

		MockServerClient mockserverclient = new MockServerClient("127.0.0.1",
				8888);

		try {
			//configureVDCVMQuerryForVDC2(mockserverclient);
			//configureVDCVMQuerry(mockserverclient);
			//prepareConsole(mockserverclient);
			updateSwitch(mockserverclient);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		mockserverclient.dumpToLog();

	}

	private static void configureVDCVMQuerry(MockServerClient mockserverclient)
			throws IOException {

		mockserverclient
				.when(request().withMethod("POST").withPath(
						"/ecm_service/vdcs/start"), exactly(4))
				.respond(
						response()
								.withStatusCode(200)
								.withHeaders(
										new Header("Content-Type",
												"application/json; charset=utf-8"))
								.withBody(loadResource("stopvm.json"))
								.withDelay(new Delay(SECONDS, 5)));

		mockserverclient
				.when(request().withMethod("POST").withPath(
						"/ecm_service/vdcs/stop"), exactly(4))
				.respond(
						response()
								.withStatusCode(200)
								.withHeaders(
										new Header("Content-Type",
												"application/json; charset=utf-8"))
								.withBody(loadResource("stopvm.json"))
								.withDelay(new Delay(SECONDS, 5)));

		mockserverclient.when(
				request().withMethod("GET").withPath("/ecm_service/vdcs/*"),
				exactly(4)).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("getvdcs.json"))
						.withDelay(new Delay(SECONDS, 1)));

		/*mockserverclient.when(
				request().withMethod("GET").withPath("/ecm_service/vms/*"),
				exactly(4)).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("getvms.json"))
						.withDelay(new Delay(SECONDS, 1)));*/

		///////////////////////////////////////////////////////////////////////////
		/*   http://localhost:8888/ecm_service/orders {---}   */ 
		/**
		 * POST
		 */
		//ordersuccess_2597192
		mockserverclient
				.when(request().withMethod("POST").withPath(
						"/ecm_service/orders"), once())
				.respond(
						response()
								.withStatusCode(200)
								.withHeader(
										new Header("Content-type",
												"application/json;charset=utf-8"))
								.withBody(loadResource("ordersuccess_2597192.json"))
								.withDelay(new Delay(SECONDS, 1)));
		//ordersuccess_2597193
		mockserverclient
		.when(request().withMethod("POST").withPath(
				"/ecm_service/orders"), once())
		.respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("ordersuccess_2597193.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
		/*   http://localhost:8888/ecm_service/orders/2597192   */
		/**
		 * Async-retry "createVDC"
		 * -------------------VerifyOrderStatus task-----------------
		 * @response 1-test.json(fail) 2-test.json(fail)
		 *           3-vdcordercom.json(success)
		 */
		mockserverclient.when(
				request().withMethod("GET").withPath(
						"/ecm_service/orders/2597192"), once()).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("test.json"))
						.withDelay(new Delay(SECONDS, 1)));
		mockserverclient.when(
				request().withMethod("GET").withPath(
						"/ecm_service/orders/2597192"), once()).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("test.json"))
						.withDelay(new Delay(SECONDS, 1)));
		mockserverclient.when(
				request().withMethod("GET").withPath(
						"/ecm_service/orders/2597192"), once()).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("vdcordercom.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
		/*   http://localhost:8888/ecm_service/orders/2597193   */
		/**
		 * Async-retry "createEPGNFSVMS"
		 * -------------------VerifyOrderStatus task-----------------
		 * @response 1-test.json(fail) 2-test.json(fail)
		 *           3-vdcordercom.json(success)
		 */
		mockserverclient.when(
				request().withMethod("GET").withPath(
						"/ecm_service/orders/2597193"), once()).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("test.json"))
						.withDelay(new Delay(SECONDS, 1)));
		mockserverclient.when(
				request().withMethod("GET").withPath(
						"/ecm_service/orders/2597193"), once()).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("test.json"))
						.withDelay(new Delay(SECONDS, 1)));
		mockserverclient.when(
				request().withMethod("GET").withPath(
						"/ecm_service/orders/2597193"), once()).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("vdcordercom.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
		/**
		 * Offers
		 */
		/*   http://localhost:8888/ecm_service/offers/EPGVDCOFFER   */
		mockserverclient.when(
				request().withMethod("GET").withPath(
						"/ecm_service/offers/EPGVDCOFFER")).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("epgvdcoffer.json"))
						.withDelay(new Delay(SECONDS, 1)));
		/*   http://localhost:8888/ecm_service/offers/epgnfsvmoffer   */
		mockserverclient.when(
				request().withMethod("GET").withPath(
						"/ecm_service/offers/epgnfsvmoffer")).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("epgnfsvmoffer.json"))
						.withDelay(new Delay(SECONDS, 1)));
		/*   http://localhost:8888/ecm_service/offers/epgvmoffer   */
		mockserverclient.when(
				request().withMethod("GET").withPath(
						"/ecm_service/offers/epgvmoffer")).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("epgvmoffer.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
		/*   http://localhost:8888/ecm_service/vdcs?$filter=tenantName%3D%27lmioss%27   */
		/**
		 * get vdc
		 * 
		 * @param $filter
		 *            for tenantName = "lmioss"
		 */
		mockserverclient.when(
				request()
						.withMethod("GET")
						.withPath("/ecm_service/vdcs")
						.withQueryStringParameter(
								new Parameter("$filter",
										"tenantName%3D'lmioss'"))).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("getvdc_lmioss.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
		/*   http://localhost:8888/ecm_service/vns?$filter=tenantName%3D%27lmioss%27+%27and%27+vdcId%3D%27VDC-3616%27   */
		//getvns.json
		mockserverclient.when(
				request()
						.withMethod("GET")
						.withPath("/ecm_service/vns")
						.withQueryStringParameter(
								new Parameter("$filter",
										"tenantName%3D%27lmioss%27+%27and%27+vdcId%3D%27VDC-3616%27"))).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("getvdc_lmioss.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
		/*   http://localhost:8888/ecm_service/vms?$filter=vdcId%3D%27VDC-3616%27+%27and%27+tenantName%3D%27lmioss%27   */
		//getvms.json
		mockserverclient.when(
				request()
						.withMethod("GET")
						.withPath("/ecm_service/vdcs")
						.withQueryStringParameter(
								new Parameter("$filter",
										"tenantName%3D'lmioss'"))).respond(
				response()
						.withStatusCode(200)
						.withHeader(
								new Header("Content-type",
										"application/json;charset=utf-8"))
						.withBody(loadResource("getvdc_lmioss.json"))
						.withDelay(new Delay(SECONDS, 1)));
		
	}
	private static void configureVDCVMQuerryForVDC2(MockServerClient mockserverclient)
			throws IOException {
		
		mockserverclient.when(request()
						.withMethod("POST")
						.withPath("/ecm_service/vms/VM-8174/start"),
						exactly(4))
						.respond(response().withStatusCode(200).withHeaders(
                               new Header("Content-Type", "application/json; charset=utf-8"))
                               .withBody(loadResource("stopvm.json"))
                               .withDelay(new Delay(SECONDS, 5)));
		
		
		mockserverclient.when(request()
				.withMethod("POST")
				.withPath("/ecm_service/vms/VM-8174/stop"),
				 exactly(4))
				.respond(response().withStatusCode(200).withHeaders(
                       new Header("Content-Type", "application/json; charset=utf-8"))
                       .withBody(loadResource("stopvm.json"))
                       .withDelay(new Delay(SECONDS, 5)));
		 
		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vdcs/*"),
				 exactly(4))
				.respond(response().withStatusCode(200).withHeader(
					new Header("Content-type","application/json;charset=utf-8"))
					.withBody(loadResource("getvdcs.json"))
					.withDelay(new Delay(SECONDS, 1)));		
		
		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vms/VM-8174"),
				exactly(4))
				.respond(response().withStatusCode(200).withHeader(
					new Header("Content-type","application/json;charset=utf-8"))
					.withBody(loadResource("singleVM-vdc2.json"))
					.withDelay(new Delay(SECONDS, 1)));
		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vms/*"),
				exactly(4))
				.respond(response().withStatusCode(200).withHeader(
					new Header("Content-type","application/json;charset=utf-8"))
					.withBody(loadResource("getvms-vdc2.json"))
					.withDelay(new Delay(SECONDS, 1)));
		
		 
	}
	
	
	private static void prepareConsole(MockServerClient mockserverclient)
			throws IOException {
		//Query and Populate
		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vdcs*"))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mme_drop5/vdcs.json"))
						.withDelay(new Delay(SECONDS, 5)));
		
		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vms*"))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mme_drop5/vms.json"))
						.withDelay(new Delay(SECONDS, 5)));
		
		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vns*"))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mme_drop5/vns.json"))
						.withDelay(new Delay(SECONDS, 5)));


		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vms/VM-3079/console*"))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mme_drop5/queryConsoleURL.json"))
						.withDelay(new Delay(SECONDS, 5)));
		
		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vms/VM-3078/console*"))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mme_drop5/queryFSVMConsoleURL.json"))
						.withDelay(new Delay(SECONDS, 5)));
		
		// http://10.51.216.10/ecm_service/vms/VM-3078?$expand=vmvnics 
		mockserverclient.when(
                request()
                              .withMethod("GET")
                              .withPath("/ecm_service/vms/VM-3078")
                              .withQueryStringParameter(
                              new Parameter("$expand",
                                            "%3Dvmvnics")))
                              .respond(response()
                              .withStatusCode(200)
                              .withHeader(
                                            new Header("Content-type",
                                                         "application/json;charset=utf-8"))
                              .withBody(loadResource("mme_drop5/FSVMexpandVMVNICs.json"))
                              .withDelay(new Delay(SECONDS, 1)));

	}
	
	private static void updateSwitch(MockServerClient mockserverclient)
			throws IOException {
		//Query and Populate
		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vdcs*"))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mme_drop5/vdcs.json"))
						.withDelay(new Delay(SECONDS, 5)));
		
		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vms*"))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mme_drop5/vms.json"))
						.withDelay(new Delay(SECONDS, 5)));
		
		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vns*"))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mme_drop5/vns.json"))
						.withDelay(new Delay(SECONDS, 5)));
		
		//http://10.70.250.137:8080/ecm_service/vns?$filter=vdcId%3DVDC-3070
		mockserverclient.when(request()
				.withMethod("GET")
				.withPath("/ecm_service/vns")
				 .withQueryStringParameter(
                         new Parameter("$filter",
                                       "vdcId%3DVDC-3070")))
				.respond(response().withStatusCode(200).withHeaders(
						new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody(loadResource("mme_drop5/vns.json"))
						.withDelay(new Delay(SECONDS, 5)));
	}

	public static void main(String[] args) {
		MockServerSimple.initMockServer();
	}

}
