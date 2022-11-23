package com.juliett.core.commons.ThreadMultiplier;

import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.commons.servlet.AbstractServlet;
import com.juliett.core.FinanceEntity.model.FinanceEntityModel;
import com.juliett.core.FinanceEntityService.FinanceEntityService;
import com.juliett.core.TransactionsService.TransactionsService;
import com.juliett.core.commons.ApiCall.ApiCall;
import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.logger.Logger;

public class ThreadMultiplier extends Thread {
	private FinanceEntityService financeEntityService;

	public ThreadMultiplier(FinanceEntityService financeEntityService, Logger logger) {
		this.financeEntityService = financeEntityService;

	}

	public void run() {

		while (true) {
			FinanceEntityModel financeEntityModel = new FinanceEntityModel();
			Long id = Long.parseLong("1");
			String jsonResult = "http://localhost:8081/alpha-vantage.p.rapidapi.com/query?from_currency=BTC&function=CURRENCY_EXCHANGE_RATE&to_currency=USD";
			Object jo = null;
			try {
				jo = new JSONParser().parse(ApiCall.apiCall(jsonResult));

			} catch (ParseException e2) {

				e2.printStackTrace();
			}
			JSONObject jobject = (JSONObject) jo;
			String updateExchangeRate = (String) jobject.get("Exchange Rate");
			Double updated = Double.parseDouble(updateExchangeRate);
			financeEntityModel.setId(id);
			financeEntityModel.setExchangeRate(updated);
			
			try {

				financeEntityService.update(financeEntityModel);
			} catch (XDevServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}
