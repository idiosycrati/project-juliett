package com.juliett.api.TransactionsDetailsController;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.juliett.api.TransactionsDetailsProcess.TransactionsDetailsProcess;
import com.juliett.commons.servlet.AbstractServlet;

@WebServlet("/TransactionsDetailsController")
/**
 * Servlet implementation class ProductsController
 */
public class TransactionsDetailsController extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private final TransactionsDetailsProcess transactionsDetailsProcess;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public TransactionsDetailsController() {
		super();
		this.transactionsDetailsProcess = new TransactionsDetailsProcess(getTransactionsDetailsService(),
				getTransactionsService(), getUsersService(), getLogger());

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		transactionsDetailsProcess.getMethod(request, response);

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
			transactionsDetailsProcess.postMethod(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}
