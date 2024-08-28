package com.ericsson.oss.nfe.init;

import static org.camunda.bpm.BpmPlatform.APP_JNDI_NAME;
import static org.camunda.bpm.BpmPlatform.JNDI_NAME_PREFIX;
import static org.camunda.bpm.BpmPlatform.MODULE_JNDI_NAME;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;

/**
 * Servlet implementation class ProcessApplicationInitalisation
 */
@WebServlet(name="ProcessApplicationInitalisation",value="/init",loadOnStartup = 1)
public class ProcessApplicationInitalisation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String JNDI_DEFAULT_PROCESS_ENGINE = JNDI_NAME_PREFIX
			+ "/" + APP_JNDI_NAME + "/" + MODULE_JNDI_NAME + "/default";

	@Resource(mappedName = JNDI_DEFAULT_PROCESS_ENGINE)
	private ProcessEngine processEngine;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessApplicationInitalisation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		System.out
				.println(" ------------------------ProcessApplicationInitalisation started--------------------");

		RepositoryService repositoryService = processEngine
				.getRepositoryService();

		DeploymentBuilder createDeployment = repositoryService
				.createDeployment();
		createDeployment.name("AFR");

		File appConfigDir = new File(getAppConfigDir() + "/workflows");

		if (appConfigDir.listFiles() != null)
		{
			for (File file : appConfigDir.listFiles()) {
			try {
				createDeployment.addInputStream(file.getName(),
						new FileInputStream(file));
			} catch (Exception ignore) {
			}
		}
		}
		Deployment deploy = createDeployment.deploy();

		String deploymentId = deploy.getId();

		System.out.println("Deployed Workflows  " + deploymentId);

		System.out
				.println(" ------------------------ProcessApplicationInitalisation ended--------------------");

	}

	public static String getAppConfigDir() {
		return System
				.getProperty("APPCONFIGDIR", "/opt/nfv_apps/nfv_appconfig");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		out.println("<h1> ---------TEST PAGE --------------</h1>");
	}

}
