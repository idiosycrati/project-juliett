package com.juliett.core.Transactions.model;

public class TransactionsModel {

	private Long id;
	private Long applicationFormId;
	private Double totalAmountPaid;
	private Double remainingBalance;
	private String subscriptionDate;
	private String subscriptionDateEnd;
	private String status;
	private String dueDatePayment;
	private String dueDateTermination;
	private Double currencyCoinQty;
	private Double sumAssurance;
	private Integer claims;
	private Double claimableAssurance;

	public TransactionsModel() {
		super();
	}

	public TransactionsModel(Long id, Long applicationFormId, Double totalAmountPaid, Double remainingBalance,
			String subscriptionDate, String subscriptionDateEnd, String status, String dueDatePayment,
			String dueDateTermination, Double currencyCoinQty, Double sumAssurance) {
		super();
		this.id = id;
		this.applicationFormId = applicationFormId;
		this.totalAmountPaid = totalAmountPaid;
		this.remainingBalance = remainingBalance;
		this.subscriptionDate = subscriptionDate;
		this.subscriptionDateEnd = subscriptionDateEnd;
		this.status = status;
		this.dueDatePayment = dueDatePayment;
		this.dueDateTermination = dueDateTermination;
		this.currencyCoinQty = currencyCoinQty;
		this.sumAssurance = sumAssurance;
	}

	public TransactionsModel(Long id, Long applicationFormId, Double totalAmountPaid, Double remainingBalance,
			String subscriptionDate, String subscriptionDateEnd, String status, String dueDatePayment,
			String dueDateTermination, Double currencyCoinQty, Double sumAssurance, Integer claims,
			Double claimableAssurance) {
		super();
		this.id = id;
		this.applicationFormId = applicationFormId;
		this.totalAmountPaid = totalAmountPaid;
		this.remainingBalance = remainingBalance;
		this.subscriptionDate = subscriptionDate;
		this.subscriptionDateEnd = subscriptionDateEnd;
		this.status = status;
		this.dueDatePayment = dueDatePayment;
		this.dueDateTermination = dueDateTermination;
		this.currencyCoinQty = currencyCoinQty;
		this.sumAssurance = sumAssurance;
		this.claims = claims;
		this.claimableAssurance = claimableAssurance;
	}

	public TransactionsModel(Long id, Long applicationFormId, Double totalAmountPaid, Double remainingBalance,
			String subscriptionDate, String subscriptionDateEnd, String status, String dueDatePayment) {
		super();
		this.id = id;
		this.applicationFormId = applicationFormId;
		this.totalAmountPaid = totalAmountPaid;
		this.remainingBalance = remainingBalance;
		this.subscriptionDate = subscriptionDate;
		this.subscriptionDateEnd = subscriptionDateEnd;
		this.status = status;
		this.dueDatePayment = dueDatePayment;
	}

	public TransactionsModel(Long id, Long applicationFormId, Double totalAmmountPaid, Double remainingBalance) {
		super();
		this.id = id;
		this.applicationFormId = applicationFormId;
		this.totalAmountPaid = totalAmmountPaid;
		this.remainingBalance = remainingBalance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationFormId() {
		return applicationFormId;
	}

	public void setApplicationFormId(Long applicationFormId) {
		this.applicationFormId = applicationFormId;
	}

	public Double getTotalAmountPaid() {
		return totalAmountPaid;
	}

	public void setTotalAmountPaid(Double totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}

	public Double getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance(Double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	public String getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getSubscriptionDateEnd() {
		return subscriptionDateEnd;
	}

	public void setSubscriptionDateEnd(String subscriptionDateEnd) {
		this.subscriptionDateEnd = subscriptionDateEnd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDueDatePayment() {
		return dueDatePayment;
	}

	public void setDueDatePayment(String dueDatePayment) {
		this.dueDatePayment = dueDatePayment;
	}

	public String getDueDateTermination() {
		return dueDateTermination;
	}

	public void setDueDateTermination(String dueDateTermination) {
		this.dueDateTermination = dueDateTermination;
	}

	public Double getCurrencyCoinQty() {
		return currencyCoinQty;
	}

	public void setCurrencyCoinQty(Double currencyCoinQty) {
		this.currencyCoinQty = currencyCoinQty;
	}

	public Double getSumAssurance() {
		return sumAssurance;
	}

	public void setSumAssurance(Double sumAssurance) {
		this.sumAssurance = sumAssurance;
	}

	public Integer getClaims() {
		return claims;
	}

	public void setClaims(Integer claims) {
		this.claims = claims;
	}

	public Double getClaimableAssurance() {
		return claimableAssurance;
	}

	public void setClaimableAssurance(Double claimableAssurance) {
		this.claimableAssurance = claimableAssurance;
	}

}
