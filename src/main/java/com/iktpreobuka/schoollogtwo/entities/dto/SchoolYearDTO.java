package com.iktpreobuka.schoollogtwo.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SchoolYearDTO {

	@JsonProperty(value = "startDate")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private String startDate;
	@JsonProperty(value = "endDate")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private String endDate;
	@JsonProperty(value = "name")
	private String name;
	

	public SchoolYearDTO() {
		super();
	}

	public SchoolYearDTO(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
