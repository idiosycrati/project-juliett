package com.juliett.api.IncidentReportController;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.IncidentReportProcess.IncidentReportProcess;
import com.juliett.commons.servlet.AbstractServlet;

@WebServlet("/IncidentReport")
public class IncidentReportController extends AbstractServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final IncidentReportProcess incidentReportProcess;

	public IncidentReportController() {

		super();
		this.incidentReportProcess = new IncidentReportProcess(getIncidentReportService(), getUsersService(),
				getTransactionsService(), getLogger());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		incidentReportProcess.getMethod(request, response);

//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		incidentReportProcess.postMethod(request, response);

//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
