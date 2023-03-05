package com.iktpreobuka.schoollogtwo.entities.dto.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParentResDTO {

	@JsonProperty(value = "Students")
	private List<?> marksByStudents = new ArrayList<StudentsMarksResDTO>();

	public ParentResDTO() {
		super();
	}

	public List<?> getMarksByStudents() {
		return marksByStudents;
	}

	public void setMarksByStudents(List<?> marksByStudents) {
		this.marksByStudents = marksByStudents;
	}
	
	
}
