package com.juliett.api.InsuranceTypeController;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.InsuranceTypeProcess.InsuranceTypeProcess;

import com.juliett.commons.servlet.AbstractServlet;

/**
 * Servlet implementation class ProductsController
 */
@WebServlet("/InsuranceType")
public class InsuranceTypeController extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private final InsuranceTypeProcess insuranceTypeProcess;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsuranceTypeController() {
		super();
		this.insuranceTypeProcess = new InsuranceTypeProcess(getInsuranceTypeService(), getLogger());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		insuranceTypeProcess.getMethod(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
