package com.juliett.api.InsuranceTypeProcess;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.InsuranceTypeService.InsuranceTypeService;

import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.logger.Logger;

/**
 * Servlet implementation class ProductsProcess
 */
public class InsuranceTypeProcess extends AbstractProcess {

	private InsuranceTypeService insuranceTypeService;

	public InsuranceTypeProcess(InsuranceTypeService insuranceTypeService, Logger logger) {
		super(logger);
		this.insuranceTypeService = insuranceTypeService;
		// TODO Auto-generated constructor stub
	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) {

	}

	public void postMethod(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
