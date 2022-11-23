package com.juliett.api.AdminController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.AdminProcess.AdminProcess;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.juliett.api.SampleProcess.SampleProcess;
import com.juliett.api.UsersProcess.UsersProcess;
import com.juliett.commons.servlet.AbstractServlet;
import com.juliett.core.Users.model.UsersModel;
import com.juliett.core.UsersRepositoryImpl.UsersRepositoryImpl;
import com.xurpas.development.core.tools.Util;

/**
 * Servlet implementation class UsersController
 */
@WebServlet("/AdminController")
public class AdminController extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private final AdminProcess adminProcess;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public AdminController() {
		super();
		this.adminProcess = new AdminProcess(getUsersService(), getProductsService(), getFinanceEntityService(),
				getInsuranceEntityService(), getLogger());

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		adminProcess.getMethod(request, response);

//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		adminProcess.postMethod(request, response);

	}

	@Override
	protected void doPatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		adminProcess.patchMethod(request, response);
	}

}
