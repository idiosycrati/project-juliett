package com.juliett.api.PaymentProcess;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.ApplicationForm.model.ApplicationFormModel;
import com.juliett.core.ApplicationFormService.ApplicationFormService;
import com.juliett.core.DateCalucator.DateCalculator;
import com.juliett.core.Payment.model.PaymentModel;
import com.juliett.core.PaymentService.PaymentService;
import com.juliett.core.Transactions.model.TransactionsModel;
import com.juliett.core.TransactionsService.TransactionsService;
import com.juliett.core.Users.model.UsersModel;
import com.juliett.core.UsersService.UsersService;
import com.xurpas.development.core.logger.Logger;
import com.xurpas.development.core.tools.Util;

public class PaymentProcess extends AbstractProcess {

	private PaymentService paymentService;
	private UsersService usersService;
	private ApplicationFormService applicationFormService;
	private TransactionsService transactionsService;

	public PaymentProcess(PaymentService paymentService, UsersService usersService,
			ApplicationFormService applicationFormService, TransactionsService transactionsService, Logger logger) {
		super(logger);
		this.paymentService = paymentService;
		this.usersService = usersService;
		this.applicationFormService = applicationFormService;
		this.transactionsService = transactionsService;

	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String subpathEndpoint = request.getPathInfo();
		switch (subpathEndpoint.substring(1)) {
		case "history":
			usersPaymentHistory(request, response);
			return;
		case "all":
			allPayments(request, response);
			return;
		default:
			response.sendRedirect("/project-juliett");
			return;
		}
	}

	public void postMethod(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getAttribute("tokenInput").toString();

		Boolean checkUser = usersService.foundAccount(token);
		Boolean isAdmin = usersService.isAdmin(token);

		if (checkUser && !isAdmin) {
			try {
				PaymentModel paymentModel;
				paymentModel = Util.deserialize(request, PaymentModel.class);

				Long transactionId = paymentModel.getTransactionId();
				List<UsersModel> usersModel = (List<UsersModel>) usersService.findAccountByToken(token);
				Long usersId = usersModel.get(0).getId();

				paymentModel.setUsersId(usersId);
				paymentModel.setPaymentDate(java.time.LocalDate.now().toString());
				List<TransactionsModel> transactionsModel = (List<TransactionsModel>) transactionsService
						.findTransactionsById(transactionId);
				Double prevTotalAmountPaid = transactionsModel.get(0).getTotalAmountPaid();
				Double prevRemainingBalance = transactionsModel.get(0).getRemainingBalance();
				String prevDueDatePayment = transactionsModel.get(0).getDueDatePayment();
				String prevDueDateTermination = transactionsModel.get(0).getDueDateTermination();
				Long id = transactionsModel.get(0).getApplicationFormId();
				Double amount = paymentModel.getAmount();
				DateCalculator dateCalcu = new DateCalculator();

				Double newTotalAmountPaid = prevTotalAmountPaid + amount;
				Double newRemainingBalance = prevRemainingBalance - amount;

				List<ApplicationFormModel> paymentUpdateDueDate = (List<ApplicationFormModel>) applicationFormService
						.findApplicationById(id);
				DateCalculator d1 = new DateCalculator();
				TransactionsModel transactionsUpdate = new TransactionsModel();
				if (paymentUpdateDueDate.get(0).getPlanId() == 1) {

					String newDueDateMonthly = d1.addMonthsToMonth(prevDueDatePayment, 1);
					transactionsUpdate.setDueDatePayment(newDueDateMonthly);
					String newDueDateTermination = dateCalcu.addMonthsToMonth(prevDueDateTermination, 2);
					transactionsUpdate.setDueDateTermination(newDueDateTermination);
				}
				if (paymentUpdateDueDate.get(0).getPlanId() == 2) {
					String newDueDateQuarterly = d1.addMonthsToMonth(prevDueDatePayment, 4);
					transactionsUpdate.setDueDatePayment(newDueDateQuarterly);
					String newDueDateTermination = dateCalcu.addMonthsToMonth(prevDueDateTermination, 6);
					transactionsUpdate.setDueDateTermination(newDueDateTermination);
				}
				if (paymentUpdateDueDate.get(0).getPlanId() == 3) {
					transactionsUpdate.setDueDatePayment("f");
				}

				transactionsUpdate.setId(transactionId);
				transactionsUpdate.setRemainingBalance(newRemainingBalance);
				transactionsUpdate.setTotalAmountPaid(newTotalAmountPaid);

				transactionsService.update(transactionsUpdate);
				sendResponse(response, ResponseCode.OK, paymentService.insert(paymentModel) + "Payment sucessfuly");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void patchMethod(HttpServletRequest request, HttpServletResponse response) {

	}

	public void usersPaymentHistory(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getAttribute("tokenInput").toString();
		Boolean checkUser = usersService.foundAccount(token);

		if (checkUser) {
			try {
				List<UsersModel> usersModel = (List<UsersModel>) usersService.findAccountByToken(token);
				Long usersId = usersModel.get(0).getId();

				sendResponse(response, ResponseCode.OK, paymentService.findPaymentByUsersId(usersId));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void allPayments(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getAttribute("tokenInput").toString();
		Boolean isAdmin = usersService.isAdmin(token);

		if (isAdmin) {
			try {

				sendResponse(response, ResponseCode.OK, paymentService.list());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!isAdmin) {
			try {

				sendError(response, ResponseCode.UNAUTHORIZED, "you're not an admin");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
