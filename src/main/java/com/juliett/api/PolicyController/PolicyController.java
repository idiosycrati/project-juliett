package com.juliett.api.PolicyController;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.PolicyProcess.PolicyProcess;

import com.juliett.commons.servlet.AbstractServlet;

/**
 * Servlet implementation class ProductsController
 */
@WebServlet("/Policy")
public class PolicyController extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private final PolicyProcess policyProcess;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PolicyController() {
		super();
		this.policyProcess = new PolicyProcess(getPolicyService(), getLogger());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		policyProcess.getMethod(request, response);
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
