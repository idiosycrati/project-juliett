package com.juliett.api.TransactionsController;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.TransactionsProcess.TransactionsProcess;
import com.juliett.commons.servlet.AbstractServlet;

@WebServlet("/TransactionsController")
/**
 * Servlet implementation class ProductsController
 */
public class TransactionsController extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private final TransactionsProcess transactionsProcess;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public TransactionsController() {
		super();
		this.transactionsProcess = new TransactionsProcess(getTransactionsService(), getUsersService(),
				getApplicationFormService(), getLogger());

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		transactionsProcess.getMethod(request, response);

//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		transactionsProcess.postMethod(request, response);

	}

	@Override
	protected void doPatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		transactionsProcess.patchMethod(request, response);
	}

}
