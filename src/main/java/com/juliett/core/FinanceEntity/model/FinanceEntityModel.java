package com.juliett.core.FinanceEntity.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FinanceEntityModel {

	private Long id;
	private String currencyName;
	private Double exchangeRate;

	public FinanceEntityModel() {

	}

	public FinanceEntityModel(Long id, String currencyName, Double exchangeRate) {
		super();
		this.id = id;
		this.currencyName = currencyName;
		this.exchangeRate = exchangeRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Override
	public String toString() {
		return "FinanceEntityModel [id=" + id + ", currencyName=" + currencyName + ", exchangeRate=" + exchangeRate
				+ "]";
	}

}
