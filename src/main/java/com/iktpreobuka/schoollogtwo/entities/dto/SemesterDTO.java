package com.iktpreobuka.schoollogtwo.entities.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SemesterDTO {

	@JsonProperty(value = "Semester")
	@NotNull
	private Integer semester;
	
	@JsonProperty(value = "Start")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull
	private LocalDate startDate;
	
	@JsonProperty(value = "End")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull
	private LocalDate endDate;
	
	@JsonProperty(value = "School year")
	@NotNull
	private Integer schoolYearId;

	public SemesterDTO() {
		super();
	}

	public SemesterDTO(Integer semester, LocalDate startDate, LocalDate endDate, Integer schoolYearId) {
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

	public Integer getSchoolYearId() {
		return schoolYearId;
	}

	public void setSchoolYearId(Integer schoolYearId) {
		this.schoolYearId = schoolYearId;
	}
	
	
}
