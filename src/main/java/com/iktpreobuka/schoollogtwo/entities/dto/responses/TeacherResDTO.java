package com.iktpreobuka.schoollogtwo.entities.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherResDTO {

	@JsonProperty(value = "Students marks")
	StudentsMarksResDTO studentsMarks;

	public TeacherResDTO() {
		super();
	}

	public StudentsMarksResDTO getStudentsMarks() {
		return studentsMarks;
	}

	public void setStudentsMarks(StudentsMarksResDTO studentsMarks) {
		this.studentsMarks = studentsMarks;
	}
	
	
}
