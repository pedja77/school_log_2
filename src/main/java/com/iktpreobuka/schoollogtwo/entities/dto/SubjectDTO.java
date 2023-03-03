package com.iktpreobuka.schoollogtwo.entities.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectDTO {

	@NotNull
	@JsonProperty(value = "Subject")
	private String subjectName;
	@NotNull
	@JsonProperty(value = "Weekly fund")
	private Integer weeklyFund;
	@NotNull
	@JsonProperty(value = "Grade")
	@Range(min = 1, max = 8)
	private Integer grade; // ne sifra razreda, nego "vrednost razreda" ~ godina
	
	public SubjectDTO() {
		super();
	}

	public SubjectDTO(@NotNull String subjectName, @NotNull Integer weeklyFund,
			@NotNull @Range(min = 1, max = 8) Integer grade) {
		super();
		this.subjectName = subjectName;
		this.weeklyFund = weeklyFund;
		this.grade = grade;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getWeeklyFund() {
		return weeklyFund;
	}

	public void setWeeklyFund(Integer weeklyFund) {
		this.weeklyFund = weeklyFund;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
}
