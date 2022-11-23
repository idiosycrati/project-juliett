package com.juliett.core.commons.ThreadMultiplier;

import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.juliett.api.ApplicationFormProcess.ApplicationFormProcess;
import com.juliett.api.TransactionsProcess.TransactionsProcess;
import com.juliett.commons.servlet.AbstractServlet;
import com.juliett.core.FinanceEntity.model.FinanceEntityModel;
import com.juliett.core.FinanceEntityService.FinanceEntityService;
import com.juliett.core.Products.model.ProductsModel;
import com.juliett.core.ProductsService.ProductsService;
import com.juliett.core.TransactionsService.TransactionsService;
import com.juliett.core.commons.ApiCall.ApiCall;
import com.xurpas.development.core.logger.Logger;

public class ThreadSumAssurance extends Thread {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductsService productsService;
	private TransactionsService transactionsService;
	private FinanceEntityService financeEntityService;

	public ThreadSumAssurance(TransactionsService transactionsService, FinanceEntityService financeEntityService,
			Logger logger) {
		this.transactionsService = transactionsService;
		this.financeEntityService = financeEntityService;

	}

	public void run() {
		while (true) {
			Long id = Long.parseLong("1");
			List<FinanceEntityModel> multiplierFinance = (List<FinanceEntityModel>) financeEntityService
					.findFinanceById(id);
			Double multiplier = multiplierFinance.get(0).getExchangeRate();
			transactionsService.updateSumAssurance(multiplier);
			System.out.println(multiplier);
			try {

				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
