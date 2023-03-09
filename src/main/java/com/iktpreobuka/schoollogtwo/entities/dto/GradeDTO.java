package com.iktpreobuka.schoollogtwo.entities.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeDTO {

	@NotNull
	@Range(min = 1, max = 8)
	@JsonProperty(value = "grade")
	private Integer value;

	public GradeDTO() {
		super();
	}

	public GradeDTO(@NotNull @Range(min = 1, max = 8) Integer value) {
		super();
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	
}
