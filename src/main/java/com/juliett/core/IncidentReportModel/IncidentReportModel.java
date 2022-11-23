package com.juliett.core.IncidentReportModel;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class IncidentReportModel {

	private Long id;
	private Long transactionsId;
	private Long usersId;
	private String dateReport;
	private String dateIncident;
	private String typeOfIncident;
	private Boolean isApproved;
	private String descriptionOfIncident;
	private String approvedBy;
	private String dateApproved;


	public IncidentReportModel() {
		super();
	}



	public IncidentReportModel(Long id, Long transactionsId, Long usersId, String dateReport, String dateIncident,
			String typeOfIncident, Boolean isApproved, String descriptionOfIncident, String approvedBy,
			String dateApproved) {
		super();
		this.id = id;
		this.transactionsId = transactionsId;
		this.usersId = usersId;
		this.dateReport = dateReport;
		this.dateIncident = dateIncident;
		this.typeOfIncident = typeOfIncident;
		this.isApproved = isApproved;
		this.descriptionOfIncident = descriptionOfIncident;
		this.approvedBy = approvedBy;
		this.dateApproved = dateApproved;
	}



	public IncidentReportModel(Long id, Long usersId, Long transactionsId, String dateReport, String dateIncident,
			String typeOfIncident, String descriptionOfIncident) {
		super();
		this.id = id;
		this.usersId = usersId;
		this.dateReport = dateReport;
		this.dateIncident = dateIncident;
		this.typeOfIncident = typeOfIncident;
		this.descriptionOfIncident = descriptionOfIncident;
		this.transactionsId = transactionsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	public String getDateReport() {
		return dateReport;
	}

	public void setDateReport(String dateReport) {
		this.dateReport = dateReport;
	}

	public String getDateIncident() {
		return dateIncident;
	}

	public void setDateIncident(String dateIncident) {
		this.dateIncident = dateIncident;
	}

	public String getTypeOfIncident() {
		return typeOfIncident;
	}

	public void setTypeOfIncident(String typeOfIncident) {
		this.typeOfIncident = typeOfIncident;
	}

	public String getDescriptionOfIncident() {
		return descriptionOfIncident;
	}

	public void setDescriptionOfIncident(String descriptionOfIncident) {
		this.descriptionOfIncident = descriptionOfIncident;
	}

	public Long getTransactionsId() {
		return transactionsId;
	}

	public void setTransactionsId(Long transactionsId) {
		this.transactionsId = transactionsId;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(String dateApproved) {
		this.dateApproved = dateApproved;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	

}
