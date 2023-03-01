package com.iktpreobuka.schoollogtwo.entities.dto;

import java.util.List;

public class TeachersCollectionDTO {

	private List<TeacherDTO> teachers;

	public TeachersCollectionDTO(List<TeacherDTO> teachers) {
		super();
		this.teachers = teachers;
	}

	public TeachersCollectionDTO() {
		super();
	}

	public List<TeacherDTO> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherDTO> teachers) {
		this.teachers = teachers;
	}
	
}
