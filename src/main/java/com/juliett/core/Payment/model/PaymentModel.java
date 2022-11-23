package com.juliett.core.Payment.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentModel {
	private Long id;
	private Double amount;
	private String referenceNumber;
	private Long transactionId;
	private String paymentDate;
	private Long usersId;

	public PaymentModel() {
		super();
	}

	public PaymentModel(Long id, Double amount, String referenceNumber, Long transactionId, String paymentDate,
			Long usersId) {
		super();
		this.id = id;
		this.amount = amount;
		this.referenceNumber = referenceNumber;
		this.transactionId = transactionId;
		this.paymentDate = paymentDate;
		this.usersId = usersId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

}
