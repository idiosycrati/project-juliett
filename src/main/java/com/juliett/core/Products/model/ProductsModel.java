package com.juliett.core.Products.model;

import java.sql.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductsModel {

	private Long products_Id;
	private String category;
	private Integer premium;
	private Integer sumAssurance;
	private Integer tenure;
	private String creationDate;

	public ProductsModel() {
		super();
	}

	
	public ProductsModel(Long products_Id, String category, Integer premium, Integer tenure, String creationDate) {
		super();
		this.products_Id = products_Id;
		this.category = category;
		this.premium = premium;
		this.tenure = tenure;
		this.creationDate = creationDate;
	}


	public ProductsModel(Long products_Id, String category, Integer premium, Integer sumAssurance, Integer tenure,
			String creationDate) {
		super();
		this.products_Id = products_Id;
		this.category = category;
		this.premium = premium;
		this.sumAssurance = sumAssurance;
		this.tenure = tenure;
		this.creationDate = creationDate;
	}

	public Long getProducts_Id() {
		return products_Id;
	}

	public void setProducts_Id(Long products_Id) {
		this.products_Id = products_Id;
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

	public Integer getSumAssurance() {
		return sumAssurance;
	}

	public void setSumAssurance(Integer sumAssurance) {
		this.sumAssurance = sumAssurance;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}
