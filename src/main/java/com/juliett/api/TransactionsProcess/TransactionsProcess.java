package com.juliett.api.TransactionsProcess;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.ApplicationForm.model.ApplicationFormModel;
import com.juliett.core.ApplicationFormService.ApplicationFormService;
import com.juliett.core.Transactions.model.TransactionsModel;
import com.juliett.core.TransactionsService.TransactionsService;
import com.juliett.core.Users.model.UsersModel;
import com.juliett.core.UsersService.UsersService;
import com.juliett.core.commons.ApiCall.ApiCall;
import com.xurpas.development.core.logger.Logger;

public class TransactionsProcess extends AbstractProcess {

	private TransactionsService transactionsService;
	private UsersService usersService;
	private ApplicationFormService applicationFormService;

	public TransactionsProcess(TransactionsService transactionsService, UsersService usersService,
			ApplicationFormService applicationFormService, Logger logger) {

		super(logger);
		this.transactionsService = transactionsService;
		this.usersService = usersService;
		this.applicationFormService = applicationFormService;
	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String subpathEndpoint = request.getPathInfo();
		switch (subpathEndpoint.substring(1)) {
		case "history":
			transactionHistory(request, response);
			return;
		case "all":
			allTransactions(request, response);
			return;
		default:
			response.sendRedirect("/project-juliett");
			return;
		}
	}

	public void postMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {

	}

	public void patchMethod(HttpServletRequest request, HttpServletResponse response) {

	}

	public void transactionHistory(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getAttribute("tokenInput").toString();
		Boolean isAdmin = usersService.isAdmin(token);
		Boolean checkUser = usersService.foundAccount(token);

		if (checkUser && !isAdmin) {
			try {
				List<UsersModel> usersModel = (List<UsersModel>) usersService.findAccountByToken(token);
				Long usersId = usersModel.get(0).getId();
				List<ApplicationFormModel> applicationFormModel = (List<ApplicationFormModel>) applicationFormService
						.findApplicationByUsersId(usersId);
				Long applicationId = applicationFormModel.get(0).getId();
				sendResponse(response, ResponseCode.OK,
						transactionsService.findTransactionByApplicationId(applicationId));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void allTransactions(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getAttribute("tokenInput").toString();
		Boolean isAdmin = usersService.isAdmin(token);

		if (isAdmin) {
			try {

				sendResponse(response, ResponseCode.OK, transactionsService.list());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!isAdmin) {
			try {
				sendResponse(response, ResponseCode.UNAUTHORIZED, "You're not an Admin");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
