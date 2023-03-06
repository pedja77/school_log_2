package com.iktpreobuka.schoollogtwo.entities.dto.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParentResDTO {

	@JsonProperty(value = "Students")
	private List<StudentsMarksResDTO> marksByStudents = new ArrayList<StudentsMarksResDTO>();

	public ParentResDTO() {
		super();
	}
	
	public void addStudentsMarks(StudentsMarksResDTO marks) {
		this.marksByStudents.add(marks);
	}

	public List<?> getMarksByStudents() {
		return marksByStudents;
	}

	public void setMarksByStudents(List<StudentsMarksResDTO> marksByStudents) {
		this.marksByStudents = marksByStudents;
	}
	
	
}
