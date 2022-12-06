package com.juliett.api.ApplicationFormController;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.ApplicationFormProcess.ApplicationFormProcess;
import com.juliett.commons.servlet.AbstractServlet;

@WebServlet("/ApplicationFormController")
/**
 * Servlet implementation class ProductsController
 */
public class ApplicationFormController extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private final ApplicationFormProcess applicationFormProcess;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public ApplicationFormController() {
		super();
		this.applicationFormProcess = new ApplicationFormProcess(getApplicationFormService(), getUsersService(),
				getInsuranceTypeService(), getTransactionsService(), getProductsService(), getPolicyService(),
				getAutoEmailService(), getLogger());

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		applicationFormProcess.getMethod(request, response);

//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			applicationFormProcess.postMethod(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		applicationFormProcess.patchMethod(request, response);
	}

}
