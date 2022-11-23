
package com.juliett.api.FinanceEntityProcess;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.core.FinanceEntity.model.FinanceEntityModel;
import com.juliett.core.FinanceEntityService.FinanceEntityService;
import com.juliett.core.commons.ApiCall.ApiCall;
import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.logger.Logger;

public class FinanceEntityProcess extends AbstractProcess {

	private FinanceEntityService financeEntityService;

	public FinanceEntityProcess(FinanceEntityService financeEntityService, Logger logger) {
		super(logger);
		this.financeEntityService = financeEntityService;

	}

	public void getMethod(HttpServletRequest request, HttpServletResponse response) {

	}

	public void postMethod(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException, InterruptedException {

		try {

			while (true) {
				FinanceEntityModel financeEntityModel = new FinanceEntityModel();

				Long id = Long.parseLong("1");
				sendResponse(response, ResponseCode.OK, financeEntityService.findFinanceById(id));

				String jsonResult = "http://localhost:8081/alpha-vantage.p.rapidapi.com/query?from_currency=BTC&function=CURRENCY_EXCHANGE_RATE&to_currency=USD";

				String apiCall = ApiCall.apiCall(jsonResult);

				Object jo = null;
				jo = new JSONParser().parse(apiCall);
				JSONObject jobject = (JSONObject) jo;
				String updateExchangeRate = (String) jobject.get("Exchange Rate");
				Double updated = Double.parseDouble(updateExchangeRate);
				financeEntityModel.setId(id);
				financeEntityModel.setExchangeRate(updated);
				financeEntityService.update(financeEntityModel);
				System.out.println("threading running singsiadgadgng");
				Thread.sleep(1000);
			}
		} catch (XDevServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void patchMethod(HttpServletRequest request, HttpServletResponse response) {

	}
}
