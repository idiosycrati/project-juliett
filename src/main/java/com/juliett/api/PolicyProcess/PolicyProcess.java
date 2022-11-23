package com.juliett.api.PolicyProcess;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.GetMulitplier.GetMultiplier;
import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.PolicyService.PolicyService;

import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.logger.Logger;

/**
 * Servlet implementation class ProductsProcess
 */
public class PolicyProcess extends AbstractProcess {

	private PolicyService policyService;


	public PolicyProcess(PolicyService policyService, Logger logger) {
		super(logger);
		this.policyService = policyService;
		// TODO Auto-generated constructor stub
	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) {

		try {
			
			sendResponse(response, ResponseCode.OK, policyService.list());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDevServiceException e) {
			e.printStackTrace();
		}

	}

	public void postMethod(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
