package com.iktpreobuka.schoollogtwo.entities.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentDTO extends UserDTO {

	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Past
	@JsonProperty(value = "Date of birth")
	private LocalDate dateOfBirth;
	
	@Range(min = 1, max = 8)
	@JsonProperty(value = "Grade")
	private Integer grade;
	
	public StudentDTO() {
		super();
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	
}
