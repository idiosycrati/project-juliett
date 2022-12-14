package com.juliett.core.Transactions.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.juliett.core.model.enums.Status;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class TransactionsModel {

	private Long id;
	private Long applicationFormId;
	private Double totalAmountPaid;
	private Double remainingBalance;
	private String subscriptionDate;
	private String subscriptionDateEnd;
	private Status status;
	private String dueDatePayment;
	private String dueDateTermination;
	private Double currencyCoinQty;
	private Double sumAssurance;
	private Integer claims;
	private Double claimableAssurance;
	private Long plansCategory;
	private String jsonPolicy;
	private Long usersId;
	private String firstName;
	private String lastName;
	private String email;

	public TransactionsModel() {
		super();
	}

	public TransactionsModel(Long id, Long applicationFormId, Double totalAmountPaid, Double remainingBalance,
			String subscriptionDate, String subscriptionDateEnd, Status status, String dueDatePayment,
			String dueDateTermination, Double currencyCoinQty, Double sumAssurance, Integer claims,
			Double claimableAssurance, Long usersId) {
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

		this.usersId = usersId;
	}

	public TransactionsModel(Long id, Long applicationFormId, Double totalAmountPaid, Double remainingBalance,
			String subscriptionDate, String subscriptionDateEnd, Status status, String dueDatePayment,
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

	public TransactionsModel(String jsonPolicy) {
		super();
		this.jsonPolicy = jsonPolicy;
	}

	public TransactionsModel(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public TransactionsModel(Long id, Long applicationFormId, Double totalAmountPaid, Double remainingBalance,
			String subscriptionDate, String subscriptionDateEnd, Status status, String dueDatePayment,
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
			String subscriptionDate, String subscriptionDateEnd, Status status, String dueDatePayment) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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

	public String getJsonPolicy() {
		return jsonPolicy;
	}

	public void setJsonPolicy(String jsonPolicy) {
		this.jsonPolicy = jsonPolicy;
	}

	public Long getPlansCategory() {
		return plansCategory;
	}

	public void setPlansCategory(Long plansCategory) {
		this.plansCategory = plansCategory;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
