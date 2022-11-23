package com.juliett.core.ApplicationForm.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApplicationFormModel {
	private Long id;
	private Long usersId;
	private Long insuranceEntityId;
	private Long insuranceTypeId;
	private Long productId;
	private Long planId;
	private String eSignature;
	private Integer itemHealth;
	private Boolean isApproved;
	private String approvedBy;
	private String dateApplied;
	private String dateApproved;

	public ApplicationFormModel() {
		super();
	}

	public ApplicationFormModel(Long id, Long usersId, Long insuranceEntityId, Long insuranceTypeId, Long productId,
			Long planId, String eSignature, Integer itemHealth, Boolean isApproved, String approvedBy,
			String dateApplied, String dateApproved) {
		super();
		this.id = id;
		this.usersId = usersId;
		this.insuranceEntityId = insuranceEntityId;
		this.insuranceTypeId = insuranceTypeId;
		this.productId = productId;
		this.planId = planId;
		this.eSignature = eSignature;
		this.itemHealth = itemHealth;
		this.isApproved = isApproved;
		this.approvedBy = approvedBy;
		this.dateApplied = dateApplied;
		this.dateApproved = dateApproved;
	}

	public ApplicationFormModel(Long id, Long usersId, Long insuranceEntityId, Long insuranceTypeId, Long productId,
			Long planId, String eSignature, Boolean isApproved, String approvedBy, String dateApplied,
			String dateApproved) {
		super();
		this.id = id;
		this.usersId = usersId;
		this.insuranceEntityId = insuranceEntityId;
		this.insuranceTypeId = insuranceTypeId;
		this.productId = productId;
		this.planId = planId;
		this.eSignature = eSignature;
		this.isApproved = isApproved;
		this.approvedBy = approvedBy;
		this.dateApplied = dateApplied;
		this.dateApproved = dateApproved;
	}

	public ApplicationFormModel(Long usersId, Long insuranceEntityId, Long insuranceTypeId, Long productId, Long planId,
			String eSignature, Boolean isApproved, String approvedBy, String dateApplied, String dateApproved) {
		super();
		this.usersId = usersId;
		this.insuranceEntityId = insuranceEntityId;
		this.insuranceTypeId = insuranceTypeId;
		this.productId = productId;
		this.planId = planId;
		this.eSignature = eSignature;
		this.isApproved = isApproved;
		this.approvedBy = approvedBy;
		this.dateApplied = dateApplied;
		this.dateApproved = dateApproved;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	public Long getInsuranceEntityId() {
		return insuranceEntityId;
	}

	public void setInsuranceEntityId(Long insuranceEntityId) {
		this.insuranceEntityId = insuranceEntityId;
	}

	public Long getInsuranceTypeId() {
		return insuranceTypeId;
	}

	public void setInsuranceTypeId(Long insuranceTypeId) {
		this.insuranceTypeId = insuranceTypeId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String geteSignature() {
		return eSignature;
	}

	public void seteSignature(String eSignature) {
		this.eSignature = eSignature;
	}

	public Integer getItemHealth() {
		return itemHealth;
	}

	public void setItemHealth(Integer itemHealth) {
		this.itemHealth = itemHealth;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(String dateApplied) {
		this.dateApplied = dateApplied;
	}

	public String getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(String dateApproved) {
		this.dateApproved = dateApproved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
