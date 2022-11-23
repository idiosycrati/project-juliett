package com.juliett.api.PaymentController;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.PaymentProcess.PaymentProcess;
import com.juliett.commons.servlet.AbstractServlet;

/**
 * Servlet implementation class ProductsController
 */
@WebServlet("/PaymentController")
public class PaymentController extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private final PaymentProcess paymentProcess;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentController() {
		super();
		this.paymentProcess = new PaymentProcess(getPaymentService(), getUsersService(), getApplicationFormService(),
				getTransactionsService(), getLogger());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		paymentProcess.getMethod(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		paymentProcess.postMethod(request, response);
	}

}
