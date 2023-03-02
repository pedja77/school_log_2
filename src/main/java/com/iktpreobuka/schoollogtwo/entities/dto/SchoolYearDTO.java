package com.iktpreobuka.schoollogtwo.entities.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SchoolYearDTO {

	@JsonProperty(value = "Start")
	private LocalDate startDate;
	@JsonProperty(value = "End")
	private LocalDate endDate;

	public SchoolYearDTO() {
		super();
	}

	public SchoolYearDTO(LocalDate startDate, LocalDate endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
}
