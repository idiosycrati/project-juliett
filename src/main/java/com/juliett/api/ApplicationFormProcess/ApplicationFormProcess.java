package com.juliett.api.ApplicationFormProcess;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.ApplicationForm.model.ApplicationFormModel;
import com.juliett.core.ApplicationFormService.ApplicationFormService;
import com.juliett.core.AutoEmailModel.AutoEmailModel;
import com.juliett.core.AutoEmailService.AutoEmailService;
import com.juliett.core.AutoEmailer.AutoEmailer;
import com.juliett.core.DateCalucator.DateCalculator;
import com.juliett.core.InsuranceType.model.InsuranceTypeModel;
import com.juliett.core.InsuranceTypeService.InsuranceTypeService;
import com.juliett.core.Policy.model.PolicyModel;
import com.juliett.core.PolicyService.PolicyService;
import com.juliett.core.Products.model.ProductsModel;
import com.juliett.core.ProductsService.ProductsService;
import com.juliett.core.Transactions.model.TransactionsModel;
import com.juliett.core.TransactionsService.TransactionsService;
import com.juliett.core.Users.model.UsersModel;
import com.juliett.core.UsersService.UsersService;
import com.juliett.core.commons.ApiCall.ApiCall;
import com.juliett.core.model.enums.Status;
import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.logger.Logger;
import com.xurpas.development.core.tools.Util;

public class ApplicationFormProcess extends AbstractProcess {

	private ApplicationFormService applicationFormService;
	private UsersService usersService;
	private InsuranceTypeService insuranceTypeService;
	private TransactionsService transactionsService;
	private ProductsService productsService;
	private PolicyService policyService;
	private AutoEmailService autoEmailService;

	public ApplicationFormProcess(ApplicationFormService applicationFormService, UsersService usersService,
			InsuranceTypeService insuranceTypeService, TransactionsService transactionsService,
			ProductsService productsService, PolicyService policyService, AutoEmailService autoEmailService,
			Logger logger) {
		super(logger);
		this.applicationFormService = applicationFormService;
		this.usersService = usersService;
		this.insuranceTypeService = insuranceTypeService;
		this.transactionsService = transactionsService;
		this.productsService = productsService;
		this.policyService = policyService;
		this.autoEmailService = autoEmailService;

	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String subpathEndpoint = request.getPathInfo();

		switch (subpathEndpoint.substring(1)) {
		case "history":
			applicationHistory(request, response);
			return;
		default:
			response.sendRedirect("/project-juliett");
			return;
		}
	}

	public void postMethod(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {

		String subpathEndpoint = request.getPathInfo();

		switch (subpathEndpoint.substring(1)) {
		case "apply":
			sendEmailToTerminated(request, response);
			return;
		case "approve":
			approveApplication(request, response);
			return;
		default:
			response.sendRedirect("/project-juliett");
			return;
		}
	}

	public void patchMethod(HttpServletRequest request, HttpServletResponse response) {

	}

	public void applicationForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String token = request.getAttribute("tokenInput").toString();

		Boolean checkUser = usersService.foundAccount(token);

		Boolean isAdmin = usersService.isAdmin(token);
		if (isAdmin) {
			sendResponse(response, ResponseCode.BAD_REQUEST, "You are an admin");
		}
		if (checkUser && !isAdmin) {

			try {
				ApplicationFormModel applicationForm;
				applicationForm = Util.deserialize(request, ApplicationFormModel.class);

				List<UsersModel> usersModel = (List<UsersModel>) usersService.findAccountByToken(token);
				applicationForm.setUsersId(usersModel.get(0).getId());
				applicationForm.setDateApplied(java.time.LocalDate.now().toString());
				applicationForm.setApprovedBy(null);
				applicationForm.setDateApproved(null);
				applicationForm.setIsApproved(false);

				Long insuranceType = applicationForm.getInsuranceTypeId();
				List<InsuranceTypeModel> insuranceTypeModel = (List<InsuranceTypeModel>) insuranceTypeService
						.findTypeById(insuranceType);
				Integer eligibility = insuranceTypeModel.get(0).getAcceptanceEligibility();
				Integer itemHealth = applicationForm.getItemHealth();
				if (itemHealth >= eligibility) {
					sendResponse(response, ResponseCode.OK, applicationFormService.insert(applicationForm));
				} else if (itemHealth < eligibility) {
					sendError(response, ResponseCode.NOT_ACCEPTABLE,
							"Your item health is " + itemHealth + "% we only accept " + eligibility + "% and above");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!checkUser) {
				sendResponse(response, ResponseCode.UNAUTHORIZED, "No user found");
			}
		}

	}

	public void applicationHistory(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getAttribute("tokenInput").toString();
		Boolean isAdmin = usersService.isAdmin(token);
		Boolean checkUser = usersService.foundAccount(token);

		if (checkUser && !isAdmin) {
			try {

				List<UsersModel> usersModel = (List<UsersModel>) usersService.findAccountByToken(token);
				Long usersId = usersModel.get(0).getId();

				sendResponse(response, ResponseCode.OK, applicationFormService.findApplicationByUsersId(usersId));

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!checkUser) {
				try {
					sendResponse(response, ResponseCode.UNAUTHORIZED, "No user found");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (isAdmin) {
				try {
					sendResponse(response, ResponseCode.BAD_REQUEST, "You are an admin");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void approveApplication(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		String token = request.getAttribute("tokenInput").toString();
		Boolean isAdmin = usersService.isAdmin(token);
		List<UsersModel> usersModel = (List<UsersModel>) usersService.findAccountByToken(token);
		String insurerName = usersModel.get(0).getFirstName();

		if (isAdmin) {
			try {
				ApplicationFormModel applicationFormModel;
				TransactionsModel transactionsModel = new TransactionsModel();
				DateCalculator dateCalculator = new DateCalculator();
				applicationFormModel = Util.deserialize(request, ApplicationFormModel.class);
				applicationFormModel.setApprovedBy(insurerName);
				applicationFormModel.setIsApproved(true);
				applicationFormModel.setDateApproved(java.time.LocalDate.now().toString());
				Long id = applicationFormModel.getId();
				List<ApplicationFormModel> applicationToTransaction = (List<ApplicationFormModel>) applicationFormService
						.findApplicationById(id);

				Long productId = applicationToTransaction.get(0).getProductId();
				transactionsModel.setPlansCategory(applicationToTransaction.get(0).getPlanId());
				List<ProductsModel> productsModel = (List<ProductsModel>) productsService.findProductsById(productId);

				List<TransactionsModel> getUsersName = (List<TransactionsModel>) transactionsService.getUsersName();
				String firstName = getUsersName.get(0).getFirstName();
				String lastName = getUsersName.get(0).getLastName();
				String fullName = firstName + " " + lastName;
				String email = getUsersName.get(0).getEmail();
				Integer monthly = productsModel.get(0).getMonthy();
				Integer quarterly = productsModel.get(0).getQuarterly();
				Integer cash = productsModel.get(0).getCash();
				Integer term = productsModel.get(0).getTerm();
				Long planId = applicationToTransaction.get(0).getPlanId();
				String dateNow = java.time.LocalDate.now().toString();
				Integer premium = productsModel.get(0).getPremium();
				List<PolicyModel> policyJson = (List<PolicyModel>) policyService.findPolicyByPlansCategory(planId);
				String apiCall = "http://localhost:8081/alpha-vantage.p.rapidapi.com/query?from_currency=BTC&function=CURRENCY_EXCHANGE_RATE&to_currency=USD";
				String jsonCurrency = ApiCall.apiCall(apiCall);
				Object jo = null;
				try {
					jo = new JSONParser().parse(jsonCurrency);
				} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject jobject = (JSONObject) jo;
				String exchangeRateString = (String) jobject.get("Exchange Rate");
				Double exchangeRate = Double.parseDouble(exchangeRateString);

				Double currencyCoinQty = premium / exchangeRate;
				transactionsModel.setCurrencyCoinQty(currencyCoinQty);
				transactionsModel.setApplicationFormId(id);
				transactionsModel.setSubscriptionDate(applicationFormModel.getDateApproved());
				transactionsModel.setTotalAmountPaid(0.0);
				transactionsModel.setStatus(Status.ACTIVE);
				transactionsModel.setUsersId(applicationFormModel.getUsersId());

				String subscriptionDateEnd = dateCalculator.addYearToCurrentYear(term);

				transactionsModel.setSubscriptionDateEnd(subscriptionDateEnd);

				if (planId == 1) {

					Integer multiplierMonthly = 12 * term;
					Integer balanceMonthly = monthly * multiplierMonthly;
					transactionsModel.setRemainingBalance(Double.parseDouble(balanceMonthly.toString()));
					String dueDateMonthly = dateCalculator.addMonthToCurrentMonth(1);
					transactionsModel.setDueDatePayment(dueDateMonthly);
					String dateTermination = dateCalculator.addMonthsToMonth(dateNow, 2);
					transactionsModel.setDueDateTermination(dateTermination);
				}
				if (planId == 2) {

					Integer multiplierQuarterly = 4 * term;
					Integer balanceQuarterly = quarterly * multiplierQuarterly;
					transactionsModel.setRemainingBalance(Double.parseDouble(balanceQuarterly.toString()));
					String dueDateQuarterly = dateCalculator.addMonthToCurrentMonth(4);
					transactionsModel.setDueDatePayment(dueDateQuarterly);
					String dateTermination = dateCalculator.addMonthsToMonth(dateNow, 6);
					transactionsModel.setDueDateTermination(dateTermination);
				}
				if (planId == 3) {
					System.out.println("if else statement 3");
					transactionsModel.setRemainingBalance(Double.parseDouble(cash.toString()));
					String dueDateCash = dateCalculator.addMonthToCurrentMonth(2);
					transactionsModel.setDueDatePayment(dueDateCash);
					String dateTermination = dateCalculator.addMonthsToMonth(dateNow, 2);
					transactionsModel.setDueDateTermination(dateTermination);
				}

				transactionsService.insert(transactionsModel);
				applicationFormService.update(applicationFormModel);
				sendResponse(response, ResponseCode.OK, "Approved successfuly");
				AutoEmailModel autoEmailModel = new AutoEmailModel();

				Long tId = getUsersName.get(0).getId();
				autoEmailModel.setTransactionsId(tId);
				AutoEmailer autoEmail = new AutoEmailer();
				autoEmail.sendMailSucessfullyInsured(email, fullName);
				autoEmailService.insert(autoEmailModel);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!isAdmin) {
			try {
				sendError(response, ResponseCode.UNAUTHORIZED, "You're not an Admin");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void sendEmailToTerminated(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			List<TransactionsModel> transactionsModel = (List<TransactionsModel>) transactionsService
					.findTransactionsTerminated();

			int length = transactionsModel.size();
			System.out.println(length + "lenghthh");

			for (int i = 0; i < length; i++) {
				List<TransactionsModel> getUsersInfo = (List<TransactionsModel>) transactionsService
						.getUsersInfo(transactionsModel.get(i).getId());
				System.out.println("i is incrementing " + i);

				if (transactionsModel.get(i).getStatus() == Status.TERMINATED) {

					System.out.println(
							getUsersInfo.get(0).getEmail() + getUsersInfo.get(0).getFirstName() + "weh di nga");
					AutoEmailer autoEmail = new AutoEmailer();
					try {
						autoEmail.sendMailTermination(getUsersInfo.get(0).getEmail(),
								getUsersInfo.get(0).getFirstName());
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}

					AutoEmailModel autoEmail2 = new AutoEmailModel();
					autoEmail2.setTransactionsId(transactionsModel.get(i).getId());
					autoEmail2.setTermination(true);
					autoEmail2.setApproval(false);
					autoEmail2.setNoticeDueDate(false);
					try {
						autoEmailService.update(autoEmail2);
					} catch (XDevServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			sendResponse(response, ResponseCode.OK, transactionsModel);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}

	}

}
