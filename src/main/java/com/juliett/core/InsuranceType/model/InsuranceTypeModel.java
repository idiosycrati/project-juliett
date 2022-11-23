package com.juliett.core.InsuranceType.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InsuranceTypeModel {
	private Long id;
	private String category;
	private Integer acceptanceEligibility;

	public InsuranceTypeModel() {

	}

	public InsuranceTypeModel(Long id, String category, Integer acceptanceEligibility) {
		super();
		this.id = id;
		this.category = category;
		this.acceptanceEligibility = acceptanceEligibility;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getAcceptanceEligibility() {
		return acceptanceEligibility;
	}

	public void setAcceptanceEligibility(Integer acceptanceEligibility) {
		this.acceptanceEligibility = acceptanceEligibility;
	}

}