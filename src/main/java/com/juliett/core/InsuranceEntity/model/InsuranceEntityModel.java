package com.juliett.core.InsuranceEntity.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InsuranceEntityModel {

	private Long id;
	private String itemName;
	private Long insuranceId;

	public InsuranceEntityModel() {

	}

	public InsuranceEntityModel(Long id, String itemName, Long insuranceId) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.insuranceId = insuranceId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Long insuranceId) {
		this.insuranceId = insuranceId;
	}

}
