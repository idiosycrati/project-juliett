package com.juliett.core.ApiCallModel;

public class ApiCallModel {

	private String fromCurrencyCode;

	private String fromCurrencyName;
	private String toCurrencyCode;

	private String toCurrencyName;
	private String exchangeRate;
	private String lastRefreshed;

	private String timeZone;

	private String bidPrice;
	private String askPrice;

	public ApiCallModel() {
	}

	public ApiCallModel(String fromCurrencyCode, String fromCurrencyName, String toCurrencyCode, String toCurrencyName,
			String exchangeRate, String lastRefreshed, String timeZone, String bidPrice, String askPrice) {
		this.fromCurrencyCode = fromCurrencyCode;
		this.fromCurrencyName = fromCurrencyName;
		this.toCurrencyCode = toCurrencyCode;
		this.toCurrencyName = toCurrencyName;
		this.exchangeRate = exchangeRate;
		this.lastRefreshed = lastRefreshed;
		this.timeZone = timeZone;
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
	}

	public String getFromCurrencyCode() {
		return this.fromCurrencyCode;
	}

	public void setFromCurrencyCode(String fromCurrencyCode) {
		this.fromCurrencyCode = fromCurrencyCode;
	}

	public String getFromCurrencyName() {
		return this.fromCurrencyName;
	}

	public void setFromCurrencyName(String fromCurrencyName) {
		this.fromCurrencyName = fromCurrencyName;
	}

	public String getToCurrencyCode() {
		return this.toCurrencyCode;
	}

	public void setToCurrencyCode(String toCurrencyCode) {
		this.toCurrencyCode = toCurrencyCode;
	}

	public String getToCurrencyName() {
		return this.toCurrencyName;
	}

	public void setToCurrencyName(String toCurrencyName) {
		this.toCurrencyName = toCurrencyName;
	}

	public String getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getLastRefreshed() {
		return this.lastRefreshed;
	}

	public void setLastRefreshed(String lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}

	public String getTimeZone() {
		return this.timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getBidPrice() {
		return this.bidPrice;
	}

	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}

	public String getAskPrice() {
		return this.askPrice;
	}

	public void setAskPrice(String askPrice) {
		this.askPrice = askPrice;
	}

	@Override
	public String toString() {
		return "{" + " fromCurrencyCode='" + getFromCurrencyCode() + "'" + ", fromCurrencyName='"
				+ getFromCurrencyName() + "'" + ", toCurrencyCode='" + getToCurrencyCode() + "'" + ", toCurrencyName='"
				+ getToCurrencyName() + "'" + ", exchangeRate='" + getExchangeRate() + "'" + ", lastRefreshed='"
				+ getLastRefreshed() + "'" + ", timeZone='" + getTimeZone() + "'" + ", bidPrice='" + getBidPrice() + "'"
				+ ", askPrice='" + getAskPrice() + "'" + "}";
	}

}
