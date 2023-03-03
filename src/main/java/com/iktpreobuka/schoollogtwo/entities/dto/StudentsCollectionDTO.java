package com.iktpreobuka.schoollogtwo.entities.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iktpreobuka.schoollogtwo.entities.StudentEntity;

public class StudentsCollectionDTO {

	@JsonProperty(value = "Students")
	private List<Integer> students;

	public StudentsCollectionDTO() {
		super();
	}

	public StudentsCollectionDTO(List<Integer> students) {
		super();
		this.students = students;
	}

	public List<Integer> getStudents() {
		return students;
	}

	public void setStudents(List<Integer> students) {
		this.students = students;
	}
	
	
}
