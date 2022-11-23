package com.juliett.core.Plans.model;

public class PlansModel {

	private Long id;
	private String planType;

	public PlansModel() {
		super();
	}

	public PlansModel(Long id, String planType) {
		super();
		this.id = id;
		this.planType = planType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

}
