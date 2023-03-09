package com.iktpreobuka.schoollogtwo.entities.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SemesterDTO {

	@JsonProperty(value = "semester")
	@NotNull
	@Range(min = 1, max = 2, message = "There are only two semesters in the year. Must be 1 or 2.")
	private Integer semester;
	
	@JsonProperty(value = "startDate")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull
	private String startDate;
	
	@JsonProperty(value = "endDate")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull
	private String endDate;
	
	@JsonProperty(value = "schoolYearId")
	@NotNull
	private Integer schoolYearId;

	public SemesterDTO() {
		super();
	}

	public SemesterDTO(Integer semester, String startDate, String endDate, Integer schoolYearId) {
		super();
		this.semester = semester;
		this.startDate = startDate;
		this.endDate = endDate;
		this.schoolYearId = schoolYearId;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
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

	public Integer getSchoolYearId() {
		return schoolYearId;
	}

	public void setSchoolYearId(Integer schoolYearId) {
		this.schoolYearId = schoolYearId;
	}
	
	
}
