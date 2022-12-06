package com.juliett.commons.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.core.InsuranceEntityServiceImpl.InsuranceEntityServiceImpl;
import com.juliett.core.InsuranceTypeService.InsuranceTypeService;
import com.juliett.core.InsuranceTypeServiceImpl.InsuranceTypeServiceImpl;
import com.juliett.core.PaymentService.PaymentService;
import com.juliett.core.PaymentServiceImpl.PaymentServiceImpl;
import com.juliett.core.PolicyService.PolicyService;
import com.juliett.core.PolicyServiceImpl.PolicyServiceImpl;
import com.juliett.core.ApplicationFormService.ApplicationFormService;
import com.juliett.core.ApplicationFormServiceImpl.ApplicationFormServiceImpl;
import com.juliett.core.AutoEmailService.AutoEmailService;
import com.juliett.core.AutoEmailServiceImpl.AutoEmailServiceImpl;
import com.juliett.core.FinanceEntityService.FinanceEntityService;
import com.juliett.core.FinanceEntityServiceImpl.FinanceEntityServiceImpl;
import com.juliett.core.IncidentReportService.IncidentReportService;
import com.juliett.core.IncidentReportServiceImpl.IncidentReportServiceImpl;
import com.juliett.core.InsuranceEntityService.InsuranceEntityService;
import com.juliett.core.ProductsService.ProductsService;
import com.juliett.core.ProductsServiceImpl.ProductsServiceImpl;
import com.juliett.core.SampleService.SampleService;
import com.juliett.core.SampleServiceImpl.SampleServiceImpl;
import com.juliett.core.TransactionsService.TransactionsService;
import com.juliett.core.TransactionsServiceImpl.TransactionsServiceImpl;
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
	private static InsuranceEntityService insuranceEntityService;
	private static FinanceEntityService financeEntityService;
	private static PolicyService policyService;
	private static InsuranceTypeService insuranceTypeService;
	private static ApplicationFormService applicationFormService;
	private static TransactionsService transactionsService;
	private static PaymentService paymentService;
	private static IncidentReportService incidentReportService;
	private static AutoEmailService autoEmailService;

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
		if (insuranceEntityService == null) {

			insuranceEntityService = new InsuranceEntityServiceImpl(databaseManager);
		}

		if (financeEntityService == null) {

			financeEntityService = new FinanceEntityServiceImpl(databaseManager);
		}
		if (policyService == null) {
			policyService = new PolicyServiceImpl(databaseManager);
		}

		if (insuranceTypeService == null) {

			insuranceTypeService = new InsuranceTypeServiceImpl(databaseManager);
		}

		if (applicationFormService == null) {

			applicationFormService = new ApplicationFormServiceImpl(databaseManager);
		}
		if (transactionsService == null) {
			transactionsService = new TransactionsServiceImpl(databaseManager);
		}
		if (paymentService == null) {
			paymentService = new PaymentServiceImpl(databaseManager);
		}
		if (incidentReportService == null) {
			incidentReportService = new IncidentReportServiceImpl(databaseManager);
		}
		if (autoEmailService == null) {
			autoEmailService = new AutoEmailServiceImpl(databaseManager);
		}

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

	public static InsuranceEntityService getInsuranceEntityService() {
		return insuranceEntityService;
	}

	public static void setInsuranceEntityService(InsuranceEntityService insuranceEntityService) {
		AbstractServlet.insuranceEntityService = insuranceEntityService;
	}

	public static FinanceEntityService getFinanceEntityService() {
		return financeEntityService;
	}

	public static void setFinanceEntityService(FinanceEntityService financeEntityService) {
		AbstractServlet.financeEntityService = financeEntityService;
	}

	public static PolicyService getPolicyService() {
		return policyService;
	}

	public static void setPolicyService(PolicyService policyService) {
		AbstractServlet.policyService = policyService;
	}

	public static InsuranceTypeService getInsuranceTypeService() {
		return insuranceTypeService;
	}

	public static void setInsuranceTypeService(InsuranceTypeService insuranceTypeService) {
		AbstractServlet.insuranceTypeService = insuranceTypeService;
	}

	public static ApplicationFormService getApplicationFormService() {
		return applicationFormService;
	}

	public static void setApplicationFormService(ApplicationFormService applicationFormService) {
		AbstractServlet.applicationFormService = applicationFormService;
	}

	public static TransactionsService getTransactionsService() {
		return transactionsService;
	}

	public static void setTransactionsService(TransactionsService transactionsService) {
		AbstractServlet.transactionsService = transactionsService;
	}

	public static PaymentService getPaymentService() {
		return paymentService;
	}

	public static void setPaymentService(PaymentService paymentService) {
		AbstractServlet.paymentService = paymentService;
	}

	public static IncidentReportService getIncidentReportService() {
		return incidentReportService;
	}

	public static void setIncidentReportService(IncidentReportService incidentReportService) {
		AbstractServlet.incidentReportService = incidentReportService;
	}

	public static AutoEmailService getAutoEmailService() {
		return autoEmailService;
	}

	public static void setAutoEmailService(AutoEmailService autoEmailService) {
		AbstractServlet.autoEmailService = autoEmailService;
	}

}
