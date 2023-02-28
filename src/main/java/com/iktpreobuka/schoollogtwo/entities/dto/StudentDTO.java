package com.iktpreobuka.schoollogtwo.entities.dto;

import java.time.LocalDate;

public class StudentDTO extends UserDTO {

	private LocalDate dateOfBirth;
	
	public StudentDTO() {
		super();
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	
}
