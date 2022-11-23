package com.juliett.core.Products.model;

import java.sql.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductsModel {

	private Long id;
	private String category;
	private Integer premium;
	private Integer finance_entity_id;
	private Integer monthy;
	private Integer cash;
	private Integer quarterly;
	private Integer term;
	private Integer sumAssurance;
	private Integer insuranceTypeId;

	public ProductsModel() {
		super();
	}

	public ProductsModel(Long id, String category, Integer premium, Integer finance_entity_id, Integer monthy,
			Integer cash, Integer quarterly, Integer term, Integer sumAssurance, Integer insuranceTypeId) {
		super();
		this.id = id;
		this.category = category;
		this.premium = premium;
		this.finance_entity_id = finance_entity_id;
		this.monthy = monthy;
		this.cash = cash;
		this.quarterly = quarterly;
		this.term = term;
		this.sumAssurance = sumAssurance;
		this.insuranceTypeId = insuranceTypeId;
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

	public Integer getPremium() {
		return premium;
	}

	public void setPremium(Integer premium) {
		this.premium = premium;
	}

	public Integer getFinance_entity_id() {
		return finance_entity_id;
	}

	public void setFinance_entity_id(Integer finance_entity_id) {
		this.finance_entity_id = finance_entity_id;
	}

	public Integer getMonthy() {
		return monthy;
	}

	public void setMonthy(Integer monthy) {
		this.monthy = monthy;
	}

	public Integer getCash() {
		return cash;
	}

	public void setCash(Integer cash) {
		this.cash = cash;
	}

	public Integer getQuarterly() {
		return quarterly;
	}

	public void setQuarterly(Integer quarterly) {
		this.quarterly = quarterly;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public Integer getSumAssurance() {
		return sumAssurance;
	}

	public void setSumAssurance(Integer sumAssurance) {
		this.sumAssurance = sumAssurance;
	}

	public Integer getInsuranceTypeId() {
		return insuranceTypeId;
	}

	public void setInsuranceTypeId(Integer insuranceTypeId) {
		this.insuranceTypeId = insuranceTypeId;
	}

}
