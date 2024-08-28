package com.ericsson.oss.nfe.poc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.poc.utils.FileUtils;
import com.floreysoft.jmte.Engine;

@WebServlet("/forms/*")
public class JsonFormsServlet extends HttpServlet {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		String requestForm = request.getRequestURI().split("/forms/")[1];
		System.out.println("************************* " + requestForm + " *************************");

		if (requestForm.contains("html")) {

			out.println(FileUtils.loadStreamAsString(FileUtils.loadFileFromAppConfig(requestForm)));

		} else {

			Engine engine = new Engine();
			Map<String, Object> tokens = new HashMap<String, Object>();
			tokens = new Tokens().getFormTokens(requestForm);
			String jsonTemplate = FileUtils.loadStreamAsString(this.getServletContext().getResourceAsStream("/forms/" + requestForm));
			String transformed = engine.transform(jsonTemplate, tokens);
			out.println(transformed);

		}

	}

	public void destroy() {
		// do nothing.
	}

}
