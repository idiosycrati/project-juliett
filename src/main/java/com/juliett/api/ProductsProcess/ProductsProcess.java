package com.juliett.api.ProductsProcess;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.ProductsService.ProductsService;
import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.logger.Logger;

/**
 * Servlet implementation class ProductsProcess
 */
public class ProductsProcess extends AbstractProcess {

	private ProductsService productsService;

	
	public ProductsProcess(ProductsService productsService, Logger logger) {
		super(logger);
		this.productsService = productsService;
		// TODO Auto-generated constructor stub
	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) {

		try {
			sendResponse(response, ResponseCode.OK, productsService.list());

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
