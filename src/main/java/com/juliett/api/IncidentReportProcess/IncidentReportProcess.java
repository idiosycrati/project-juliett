package com.juliett.api.IncidentReportProcess;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.AutoEmailer.AutoEmailer;
import com.juliett.core.IncidentReportModel.IncidentReportModel;
import com.juliett.core.IncidentReportService.IncidentReportService;
import com.juliett.core.Transactions.model.TransactionsModel;
import com.juliett.core.TransactionsService.TransactionsService;
import com.juliett.core.Users.model.UsersModel;
import com.juliett.core.UsersService.UsersService;
import com.xurpas.development.core.logger.Logger;
import com.xurpas.development.core.tools.Util;

public class IncidentReportProcess extends AbstractProcess {
	private IncidentReportService incidentReportService;
	private UsersService usersService;
	private TransactionsService transactionsSerivce;

	public IncidentReportProcess(IncidentReportService incidentReportService, UsersService usersService,
			TransactionsService transactionsSerivce, Logger logger) {

		super(logger);
		this.incidentReportService = incidentReportService;
		this.usersService = usersService;
		this.transactionsSerivce = transactionsSerivce;
	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String subpathEndpoint = request.getPathInfo();

		switch (subpathEndpoint.substring(1)) {
		case "list":
			reportList(request, response);
			return;

		default:
			response.sendRedirect("/project-juliett");
			return;
		}
	}

	public void postMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String subpathEndpoint = request.getPathInfo();

		switch (subpathEndpoint.substring(1)) {
		case "request":
			reportIncident(request, response);
			return;
		case "approve":
			approveReport(request, response);
			return;

		default:
			response.sendRedirect("/project-juliett");
			return;
		}
	}

	public void patchMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {

	}

	public void reportList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = request.getAttribute("tokenInput").toString();

		Boolean isAdmin = usersService.isAdmin(token);
		try {
			if (isAdmin) {
				sendResponse(response, ResponseCode.OK, incidentReportService.list());
			}
			if (!isAdmin) {
				sendResponse(response, ResponseCode.UNAUTHORIZED, "You're not an admin");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void approveReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = request.getAttribute("tokenInput").toString();

		Boolean isAdmin = usersService.isAdmin(token);
		try {
			if (isAdmin) {
				IncidentReportModel approveReport;
				TransactionsModel updateClaimable = new TransactionsModel();
				approveReport = Util.deserialize(request, IncidentReportModel.class);
				List<UsersModel> getAdminName = (List<UsersModel>) usersService.findAccountByToken(token);
				Long id = approveReport.getId();

				List<IncidentReportModel> getTransactionsId = (List<IncidentReportModel>) incidentReportService
						.findReportById(id);
				System.out.println(approveReport.getId() + " id approveeedsaf asdgasdg");
				Long transactionId = getTransactionsId.get(0).getTransactionsId();
				List<TransactionsModel> updateClaims = (List<TransactionsModel>) transactionsSerivce
						.findTransactionsById(transactionId);
				String approvedBy = getAdminName.get(0).getFirstName();
				double sumAssurance = updateClaims.get(0).getSumAssurance();
				int claims = 1;
				double claimableAssurance = sumAssurance * 0.30;
				updateClaimable.setId(transactionId);
				updateClaimable.setClaims(claims);
				updateClaimable.setClaimableAssurance(claimableAssurance);
				transactionsSerivce.updateClaims(updateClaimable);
				approveReport.setApprovedBy(approvedBy);
				approveReport.setIsApproved(true);
				approveReport.setDateApproved(java.time.LocalDate.now().toString());
				incidentReportService.update(approveReport);
				sendResponse(response, ResponseCode.OK, incidentReportService.findReportById(approveReport.getId()));

			}
			if (isAdmin) {
				sendResponse(response, ResponseCode.UNAUTHORIZED, incidentReportService.list());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void reportIncident(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = request.getAttribute("tokenInput").toString();

		Boolean isAdmin = usersService.isAdmin(token);
		try {
			if (!isAdmin) {
				IncidentReportModel reportIncident;
				reportIncident = Util.deserialize(request, IncidentReportModel.class);

				List<UsersModel> getUsersId = (List<UsersModel>) usersService.findAccountByToken(token);
				Long UsersId = getUsersId.get(0).getId();
				reportIncident.setUsersId(UsersId);
				reportIncident.setDateReport(java.time.LocalDate.now().toString());
				sendResponse(response, ResponseCode.OK, incidentReportService.insert(reportIncident));

			}
			if (isAdmin) {
				sendResponse(response, ResponseCode.UNAUTHORIZED, incidentReportService.list());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
