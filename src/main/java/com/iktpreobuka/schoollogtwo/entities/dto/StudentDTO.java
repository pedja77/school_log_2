package com.iktpreobuka.schoollogtwo.entities.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentDTO extends UserDTO {

	@NotNull
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	//@Past(message = "Must be a date in the past")
	@JsonProperty(value = "dateOfBirth")
	private String dateOfBirth;
	
	@NotNull
	@Range(min = 1, max = 8)
	@JsonProperty(value = "grade")
	private Integer grade;
	
	public StudentDTO() {
		super();
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	
}
