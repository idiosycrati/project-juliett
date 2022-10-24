package com.juliett.api.AdminProcess;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.commons.codec.digest.DigestUtils;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
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

	public AdminProcess(UsersService usersService, ProductsService productsService, Logger logger) {
		super(logger);
		this.usersService = usersService;
		this.productsService = productsService;
		// TODO Auto-generated constructor stub
	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) {

		try {
			sendResponse(response, ResponseCode.OK, usersService.list());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XDevServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void postMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String subpathEndpoint = request.getPathInfo();
		switch (subpathEndpoint.substring(1)) {
		case "login":
			login(request, response);
			return;
		case "register":
			register(request, response);
			return;
		default:
			response.sendRedirect("/not-found");
			return;
		}
	}

	public void patchMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {

	}

	@SuppressWarnings("unused")
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// Parse Request

		// Validating fields
		UsersModelDTO inputUser;
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		UsersModel usersModel;

		try {

			inputUser = Util.deserialize(request, UsersModelDTO.class);

			System.out.println(uuidAsString);
			usersModel = new UsersModel();
			usersModel.setEmail(inputUser.getEmail());
			logger.debug(inputUser);
			usersModel = usersService.checkUser(usersModel);
			String inputPassword = DigestUtils.sha256Hex(inputUser.getPassword());
			String dbPassword = usersModel.getPassword();
			System.out.println("input password is " + inputPassword + " db password is " + dbPassword);
			if (inputPassword.equals(dbPassword)) {
				usersModel.setTokens(uuidAsString);
				usersService.updateToken(usersModel);

				sendResponse(response, ResponseCode.OK, usersModel.getTokens());
			} else if (inputPassword != dbPassword) {
				sendError(response, ResponseCode.UNAUTHORIZED, "Invalid username or password");
				System.out.println("input password is " + inputPassword + " db password is " + dbPassword);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UsersModel requestBody;

		try {

			requestBody = Util.deserialize(request, UsersModel.class);

			Boolean checkEmail = usersService.isEmailAlreadyTaken(requestBody.getEmail());
			if (checkEmail) {
				sendResponse(response, ResponseCode.NOT_ACCEPTABLE, "Email is already Taken");
				return;
			} else if (!checkEmail) {

				requestBody.setIsAdmin(false);
				requestBody.setPassword(DigestUtils.sha256Hex(requestBody.getPassword()));
				requestBody = usersService.insert(requestBody);
				sendResponse(response, ResponseCode.OK, requestBody);
				logger.debug(requestBody);
			} else {
				sendResponse(response, ResponseCode.BAD_REQUEST, "Bad request");
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