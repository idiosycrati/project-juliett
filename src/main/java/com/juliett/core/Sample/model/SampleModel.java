package com.juliett.core.Sample.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.xurpas.development.core.commons.model.AbstractEntity;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SampleModel extends AbstractEntity {

    
    private Long id;
    private String description;
    



    public SampleModel(Long id) {
    	super(id);
    	this.id=id;

}


	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




}
