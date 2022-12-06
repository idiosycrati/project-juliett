package com.juliett.core.AutoEmailModel;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AutoEmailModel {
	private Long id;
	private Long transactionsId;
	private Boolean termination;
	private Boolean noticeDueDate;
	private Boolean approval;

	public AutoEmailModel() {
		super();
	}

	public AutoEmailModel(Long id, Long transactionsId, Boolean termination, Boolean noticeDueDate, Boolean approval) {
		super();
		this.id = id;
		this.transactionsId = transactionsId;
		this.termination = termination;
		this.noticeDueDate = noticeDueDate;
		this.approval = approval;
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

	public Boolean getTermination() {
		return termination;
	}

	public void setTermination(Boolean termination) {
		this.termination = termination;
	}

	public Boolean getNoticeDueDate() {
		return noticeDueDate;
	}

	public void setNoticeDueDate(Boolean noticeDueDate) {
		this.noticeDueDate = noticeDueDate;
	}

	public Boolean getApproval() {
		return approval;
	}

	public void setApproval(Boolean approval) {
		this.approval = approval;
	}

}
