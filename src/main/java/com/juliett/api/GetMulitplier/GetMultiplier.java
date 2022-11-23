package com.juliett.api.GetMulitplier;

import java.net.HttpURLConnection;
import java.util.HashMap;

import com.juliett.core.model.HttpUtilities;
import com.xurpas.development.core.logger.Logger;

public class GetMultiplier {

	private String mockBaseURL;

	public GetMultiplier() {
		this.mockBaseURL = "http://localhost:8081/alpha-vantage.p.rapidapi.com/query";

	}

	@SuppressWarnings("serial")
	public HttpURLConnection getMultiplier() {

		HttpURLConnection connection = null;
		Logger logger = null;
		System.out.println("running getmultiplier");
		try {
			System.out.println("running try catch in multiplier");

			HttpUtilities getMultiplier = new HttpUtilities(this.mockBaseURL, new HashMap<String, String>() {

				{
					put("X_RAPID_API_KEY", "eefd572afcmsh2097a3ea41f5f3bp15b4bfjsnc264f4271864");
					put("X_RAPID_API_HOST", "alpha-vantage.p.rapidapi.com");
				}

			}, "GET", logger);
			System.out.println("running");
			connection = getMultiplier.sendRequest();
			return connection;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
