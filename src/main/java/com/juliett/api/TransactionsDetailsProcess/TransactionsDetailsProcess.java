package com.juliett.api.TransactionsDetailsProcess;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.ApplicationForm.model.ApplicationFormModel;
import com.juliett.core.Transactions.model.TransactionsModel;
import com.juliett.core.TransactionsDetailsModel.TransactionsDetailsModel;
import com.juliett.core.TransactionsDetailsService.TransactionsDetailsService;
import com.juliett.core.TransactionsService.TransactionsService;
import com.juliett.core.UsersService.UsersService;
import com.xurpas.development.core.logger.Logger;
import com.xurpas.development.core.tools.Util;

public class TransactionsDetailsProcess extends AbstractProcess {
	private TransactionsDetailsService transactionsDetailsService;
	private TransactionsService transactionsService;
	private UsersService usersService;

	public TransactionsDetailsProcess(TransactionsDetailsService transactionsDetailsService,
			TransactionsService transactionsService, UsersService usersService, Logger logger) {
		super(logger);
		this.transactionsDetailsService = transactionsDetailsService;
		this.transactionsService = transactionsService;
		this.usersService = usersService;
	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String subpathEndpoint = request.getPathInfo();
		switch (subpathEndpoint.substring(1)) {
		case "history":

			return;
		case "all":

			return;
		default:
			response.sendRedirect("/project-juliett");
			return;
		}

	}

	public void postMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String subpathEndpoint = request.getPathInfo();
		switch (subpathEndpoint.substring(1)) {
		case "claim":
			getClaims(request, response);
			return;
	
		default:
			response.sendRedirect("/project-juliett");
			return;
		}

	}

	public void getClaims(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String token = request.getAttribute("tokenInput").toString();
		Boolean isAdmin = usersService.isAdmin(token);
		Boolean checkUser = usersService.foundAccount(token);
		if (checkUser && !isAdmin) {
			try {
				TransactionsDetailsModel getClaims = new TransactionsDetailsModel();
				getClaims = Util.deserialize(request, TransactionsDetailsModel.class);
				List<TransactionsModel> getClaimable = (List<TransactionsModel>) transactionsService
						.findTransactionsById(getClaims.getTransactionsId());
				Double claimableAssurance = getClaimable.get(0).getClaimableAssurance();
				Integer claims = getClaimable.get(0).getClaims();
				if (claimableAssurance != 0 && claims != 0) {
					Double updatedClaimableAssurance = claimableAssurance - getClaims.getAmount_claim();
					getClaims.setDate_claim(java.time.LocalDate.now().toString());
					transactionsDetailsService.insert(getClaims);
					TransactionsModel transactionsModel = new TransactionsModel();
					transactionsModel.setClaims(getClaimable.get(0).getClaims() - 1);
					transactionsModel.setClaimableAssurance(updatedClaimableAssurance);
					transactionsModel.setId(getClaims.getTransactionsId());
					transactionsService.updateClaims(transactionsModel);
				}
				if (claims == 0) {
					sendResponse(response, ResponseCode.NOT_ACCEPTABLE, "You don't have any claims");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
