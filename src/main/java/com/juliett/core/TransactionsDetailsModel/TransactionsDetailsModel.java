package com.juliett.core.TransactionsDetailsModel;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TransactionsDetailsModel {

	private Long id;
	private Long transactionsId;
	private Integer amount_claim;
	private String date_claim;

	public TransactionsDetailsModel() {
		super();
	}

	public TransactionsDetailsModel(Long id, Long transactionsId, Integer amount_claim, String date_claim) {
		super();
		this.id = id;
		this.transactionsId = transactionsId;
		this.amount_claim = amount_claim;
		this.date_claim = date_claim;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTransactionsId() {
		return transactionsId;
	}

	public void setTransactionsId(Long transactionsId) {
		this.transactionsId = transactionsId;
	}

	public Integer getAmount_claim() {
		return amount_claim;
	}

	public void setAmount_claim(Integer amount_claim) {
		this.amount_claim = amount_claim;
	}

	public String getDate_claim() {
		return date_claim;
	}

	public void setDate_claim(String date_claim) {
		this.date_claim = date_claim;
	}

}
