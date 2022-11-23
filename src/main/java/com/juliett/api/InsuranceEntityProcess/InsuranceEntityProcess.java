package com.juliett.api.InsuranceEntityProcess;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.InsuranceEntityService.InsuranceEntityService;
import com.juliett.core.UsersService.UsersService;
import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.logger.Logger;

public class InsuranceEntityProcess extends AbstractProcess {

	private InsuranceEntityService insuranceEntityService;

	public InsuranceEntityProcess(InsuranceEntityService insuranceEntityService, Logger logger) {
		super(logger);
		this.insuranceEntityService = insuranceEntityService;

	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {

//		String subpathEndpoint = request.getPathInfo();
//		switch (subpathEndpoint.substring(1)) {
//		case "try":
//			insuranceList(request, response);
//			return;
//
//		default:
//			response.sendRedirect("/project-juliett");
//			return;
//		}
		try {

			sendResponse(response, ResponseCode.OK, insuranceEntityService.list());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDevServiceException e) {
			e.printStackTrace();
		}
	}

	public void postMethod(HttpServletRequest request, HttpServletResponse response) {

	}

	public void patchMethod(HttpServletRequest request, HttpServletResponse response) {

	}

	public void insuranceList(HttpServletRequest request, HttpServletResponse response) {

	}

}
