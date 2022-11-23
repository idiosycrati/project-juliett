package com.juliett.core.Policy.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PolicyModel {

	private Long id;
	private String policyName;
	private String description;
	private Long plansCategory;
	private String policyJson;

	public PolicyModel() {

	}

	public PolicyModel(Long id, String policyName, String description, Long plansCategory, String policyJson) {
		super();
		this.id = id;
		this.policyName = policyName;
		this.description = description;
		this.plansCategory = plansCategory;
		this.policyJson = policyJson;
	}

	public PolicyModel(Long id, String policyName, String description, Long plansCategory) {
		super();
		this.id = id;
		this.policyName = policyName;
		this.description = description;
		this.plansCategory = plansCategory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPlansCategory() {
		return plansCategory;
	}

	public void setPlansCategory(Long plansCategory) {
		this.plansCategory = plansCategory;
	}

	public String getPolicyJson() {
		return policyJson;
	}

	public void setPolicyJson(String policyJson) {
		this.policyJson = policyJson;
	}

}
