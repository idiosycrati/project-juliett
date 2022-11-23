package com.juliett.api.AdminProcess;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.commons.codec.digest.DigestUtils;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.FinanceEntity.model.FinanceEntityModel;
import com.juliett.core.FinanceEntityService.FinanceEntityService;
import com.juliett.core.InsuranceEntity.model.InsuranceEntityModel;
import com.juliett.core.InsuranceEntityService.InsuranceEntityService;
import com.juliett.core.Policy.model.PolicyModel;
import com.juliett.core.PolicyService.PolicyService;
import com.juliett.core.ProductsService.ProductsService;
import com.juliett.core.SampleService.SampleService;
import com.juliett.core.Users.model.UsersModel;
import com.juliett.core.Users.model.UsersModelDTO;
import com.juliett.core.UsersService.UsersService;
import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.logger.Logger;
import com.xurpas.development.core.tools.HashUtil;
import com.xurpas.development.core.tools.Util;

public class AdminProcess extends AbstractProcess {

	private UsersService usersService;
	private ProductsService productsService;
	private FinanceEntityService financeEntityService;
	private InsuranceEntityService insuranceEntityService;
	private PolicyService policyService;

	public AdminProcess(UsersService usersService, ProductsService productsService,
			FinanceEntityService financeEntityService, InsuranceEntityService insuranceEntityService, Logger logger) {
		super(logger);
		this.usersService = usersService;
		this.productsService = productsService;
		this.financeEntityService = financeEntityService;
		this.insuranceEntityService = insuranceEntityService;
		// TODO Auto-generated constructor stub
	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String subpathEndpoint = request.getPathInfo();
		switch (subpathEndpoint.substring(1)) {
		case "users":
			users(request, response);
			return;
		case "finance":
			financeList(request, response);
			return;
		default:
			response.sendRedirect("/project-juliett");
			return;
		}

	}

	public void postMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String subpathEndpoint = request.getPathInfo();
		switch (subpathEndpoint.substring(1)) {

		case "finance/delete":
			deleteFinance(request, response);
			return;
		case "insurance/delete":
			deleteInsurance(request, response);
			return;
		case "policy/delete":
			policyDelete(request, response);
			return;
		case "finance/add":
			addFinance(request, response);
			return;
		case "insurance/add":
			addInsuranceItem(request, response);
			return;
		case "policy/add":
			addPolicy(request, response);
			return;
		default:
			response.sendRedirect("/project-juliett");
			return;
		}
	}

	public void patchMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String subpathEndpoint = request.getPathInfo();
		switch (subpathEndpoint.substring(1)) {
		case "users/edit":
			editUsers(request, response);
			return;
		case "finance/edit":
			updateFinance(request, response);
			return;
		case "insurance/edit":
			editInsuranceItem(request, response);
			return;
		case "policy/edit":
			editInsuranceItem(request, response);
			return;

		default:
			response.sendRedirect("/project-juliett");
			return;
		}

	}

	public void deleteInsurance(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String token = request.getAttribute("tokenInput").toString();

			Boolean isAdmin = usersService.isAdmin(token);
			InsuranceEntityModel insuranceDelete;

			insuranceDelete = Util.deserialize(request, InsuranceEntityModel.class);
			if (isAdmin) {
				insuranceEntityService.delete(insuranceDelete);
				sendResponse(response, ResponseCode.OK, insuranceEntityService.list());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void policyDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String token = request.getAttribute("tokenInput").toString();

			Boolean isAdmin = usersService.isAdmin(token);
			PolicyModel policyDelete;

			policyDelete = Util.deserialize(request, PolicyModel.class);
			if (isAdmin) {
				policyService.delete(policyDelete);
				sendResponse(response, ResponseCode.OK, policyService.list());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteFinance(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String token = request.getAttribute("tokenInput").toString();

			Boolean isAdmin = usersService.isAdmin(token);
			FinanceEntityModel financeDelete;

			financeDelete = Util.deserialize(request, FinanceEntityModel.class);
			if (isAdmin) {
				financeEntityService.delete(financeDelete);
				sendResponse(response, ResponseCode.OK, financeEntityService.list());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateFinance(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String token = request.getAttribute("tokenInput").toString();

			Boolean isAdmin = usersService.isAdmin(token);
			FinanceEntityModel financeUpdate;

			financeUpdate = Util.deserialize(request, FinanceEntityModel.class);
			if (isAdmin) {

				financeEntityService.update(financeUpdate);
				sendResponse(response, ResponseCode.OK, financeEntityService.findFinanceById(financeUpdate.getId()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addFinance(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String token = request.getAttribute("tokenInput").toString();

			Boolean isAdmin = usersService.isAdmin(token);
			FinanceEntityModel financeAdd;

			financeAdd = Util.deserialize(request, FinanceEntityModel.class);

			if (isAdmin) {
				financeEntityService.insert(financeAdd);
				sendResponse(response, ResponseCode.OK, financeEntityService.list());
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public void addPolicy(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String token = request.getAttribute("tokenInput").toString();

			Boolean isAdmin = usersService.isAdmin(token);
			PolicyModel policyAdd;

			policyAdd = Util.deserialize(request, PolicyModel.class);

			if (isAdmin) {
				policyService.insert(policyAdd);
				sendResponse(response, ResponseCode.OK, policyService.list());
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void addInsuranceItem(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String token = request.getAttribute("tokenInput").toString();

			Boolean isAdmin = usersService.isAdmin(token);
			InsuranceEntityModel insuranceAdd;

			insuranceAdd = Util.deserialize(request, InsuranceEntityModel.class);

			if (isAdmin) {
				insuranceEntityService.insert(insuranceAdd);
				sendResponse(response, ResponseCode.OK, insuranceEntityService.list());
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public void editInsuranceItem(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String token = request.getAttribute("tokenInput").toString();

			Boolean isAdmin = usersService.isAdmin(token);
			InsuranceEntityModel insuranceEdit;

			insuranceEdit = Util.deserialize(request, InsuranceEntityModel.class);

			if (isAdmin) {
				insuranceEntityService.update(insuranceEdit);
				sendResponse(response, ResponseCode.OK, insuranceEntityService.findItemById(insuranceEdit.getId()));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public void editPolicy(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String token = request.getAttribute("tokenInput").toString();

			Boolean isAdmin = usersService.isAdmin(token);
			PolicyModel policyEdit;

			policyEdit = Util.deserialize(request, PolicyModel.class);

			if (isAdmin) {
				policyService.update(policyEdit);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public void editUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {

			String token = request.getAttribute("tokenInput").toString();

			Boolean isAdmin = usersService.isAdmin(token);
			UsersModelDTO inputUser;
			Long parameter = Long.parseLong(request.getParameter("Id"));
			inputUser = Util.deserialize(request, UsersModelDTO.class);
			UsersModel usersModel;
			System.out.println(inputUser.getIsAdmin() + " admin is admin admin");
			System.out.println(parameter + " id came from parameter");

			if (parameter != null && isAdmin
					&& usersService.findUserById(Integer.parseInt(request.getParameter("Id"))) != null) {

				usersModel = new UsersModel();
				System.out.println("update User" + usersModel.getId());
				System.out.println("is admin" + usersModel.getIsAdmin());
				usersModel.setId(parameter);
				usersModel.setIsAdmin(inputUser.getIsAdmin());
				usersService.updateIsAdmin(usersModel);
				sendResponse(response, ResponseCode.OK,
						usersService.findUserById(Integer.parseInt(request.getParameter("Id"))));

			}
			if (parameter != null && isAdmin
					&& usersService.findUserById(Integer.parseInt(request.getParameter("Id"))) == null) {
				sendResponse(response, ResponseCode.BAD_REQUEST,
						"Sorry id " + Integer.parseInt(request.getParameter("Id")) + " is not found");

			}

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void users(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {

			String token = request.getAttribute("tokenInput").toString();
			Boolean isAdmin = usersService.isAdmin(token);
			String parameter = request.getParameter("Id");

			if (parameter != null && isAdmin
					&& usersService.findUserById(Integer.parseInt(request.getParameter("Id"))) != null) {
				sendResponse(response, ResponseCode.OK,
						usersService.findUserById(Integer.parseInt(request.getParameter("Id"))));

			}

			if (parameter != null && isAdmin
					&& usersService.findUserById(Integer.parseInt(request.getParameter("Id"))) == null) {
				sendResponse(response, ResponseCode.BAD_REQUEST,
						"Sorry id" + Integer.parseInt(request.getParameter("Id")) + " is not found");

			}

			if (isAdmin) {

				sendResponse(response, ResponseCode.OK, usersService.list());
			} else {

				sendResponse(response, ResponseCode.UNAUTHORIZED, "Sorry you are not an Admin ");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XDevServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void financeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {

			String token = request.getAttribute("tokenInput").toString();
			Boolean isAdmin = usersService.isAdmin(token);
			String parameter = request.getParameter("Id");

			if (parameter != null && isAdmin
					&& usersService.findUserById(Integer.parseInt(request.getParameter("Id"))) != null) {
				sendResponse(response, ResponseCode.OK,
						usersService.findUserById(Integer.parseInt(request.getParameter("Id"))));

			}

			if (parameter != null && isAdmin
					&& usersService.findUserById(Integer.parseInt(request.getParameter("Id"))) == null) {
				sendResponse(response, ResponseCode.BAD_REQUEST,
						"Sorry id" + Integer.parseInt(request.getParameter("Id")) + " is not found");

			}

			if (isAdmin) {

				sendResponse(response, ResponseCode.OK, financeEntityService.list());
			} else {

				sendResponse(response, ResponseCode.UNAUTHORIZED, "Sorry you are not an Admin ");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XDevServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}