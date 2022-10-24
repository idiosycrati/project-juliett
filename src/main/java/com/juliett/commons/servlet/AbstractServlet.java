package com.juliett.commons.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;

import com.juliett.core.ProductsService.ProductsService;
import com.juliett.core.ProductsServiceImpl.ProductsServiceImpl;
import com.juliett.core.SampleService.SampleService;
import com.juliett.core.SampleServiceImpl.SampleServiceImpl;
import com.juliett.core.UsersService.UsersService;
import com.juliett.core.UsersServiceImpl.UsersServiceImpl;
import com.xurpas.development.core.commons.ProjectProperties;
import com.xurpas.development.core.db.DatabaseManager;
import com.xurpas.development.core.db.impl.DatabaseManagerImpl;
import com.xurpas.development.core.logger.Logger;
import com.xurpas.development.core.logger.XDevDefaultLogger;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;

/**
 * Servlet implementation class AbstractServlet
 */
@WebServlet("/AbstractServlet")
public class AbstractServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected static AbstractServlet instance;
	private static Logger logger;
	private static ProjectProperties projectProperties;
	private static DatabaseManager databaseManager;
	private static SampleService sampleService;
	private static UsersService usersService;
	private static ProductsService productsService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public AbstractServlet() {
		super();

		if (logger == null) {
			logger = new XDevDefaultLogger();
		}

		projectProperties = ProjectProperties.getInstance();
		logger.info(projectProperties);
		System.out.println(projectProperties);

		if (databaseManager == null) {
			String datasource = projectProperties.getProperty("default.datasource");
			System.out.println(datasource);
			logger.info("datasource=" + datasource);

			if (datasource != null) {
				try {
					databaseManager = new DatabaseManagerImpl(datasource);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}

		if (sampleService == null) {

			sampleService = new SampleServiceImpl(databaseManager);
		}

		if (usersService == null) {

			usersService = new UsersServiceImpl(databaseManager);
		}
		if (productsService == null) {
			productsService = new ProductsServiceImpl(databaseManager);
		}
		System.out.println("sample checking " + (sampleService == null) + "users checking " + (usersService == null)
				+ "products checking " + (productsService == null));
		

	}

	public static AbstractServlet getInstance() {
		if (instance == null) {
			instance = new AbstractServlet();
		}

		return instance;
	}

	protected void doPatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "Method PATCH is not implemented by this servlet for this URI";

		if (request.getProtocol().endsWith("1.1")) {
			response.sendError(SC_METHOD_NOT_ALLOWED, message);
		} else {
			response.sendError(SC_BAD_REQUEST, message);
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Hello World");
		if ("PATCH".equalsIgnoreCase(request.getMethod())) {
			doPatch(request, response);
		} else {
			super.service(request, response);
		}
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		AbstractServlet.logger = logger;
	}

	public static SampleService getSampleService() {
		return sampleService;
	}

	public static UsersService getUsersService() {
		return usersService;
	}

	public static ProductsService getProductsService() {

		return productsService;
	}

	public static void setProductsService(ProductsService productsService) {
		AbstractServlet.productsService = productsService;
	}

	public static void setUsersService(UsersService usersService) {
		AbstractServlet.usersService = usersService;
	}

	public static void setSampleService(SampleService sampleService) {
		AbstractServlet.sampleService = sampleService;
	}

}
