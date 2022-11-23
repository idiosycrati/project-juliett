
package com.juliett.api.InsuranceEntityController;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.InsuranceEntityProcess.InsuranceEntityProcess;

import com.juliett.commons.servlet.AbstractServlet;

/**
 * Servlet implementation class ProductsController
 */
@WebServlet("/InsuranceEntity")
public class InsuranceEntityController extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private final InsuranceEntityProcess insuranceEntityProcess;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsuranceEntityController() {
		super();
		this.insuranceEntityProcess = new InsuranceEntityProcess(getInsuranceEntityService(), getLogger());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		insuranceEntityProcess.getMethod(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
