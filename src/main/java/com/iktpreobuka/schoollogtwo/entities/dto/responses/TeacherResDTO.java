package com.iktpreobuka.schoollogtwo.entities.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherResDTO {

	@JsonProperty(value = "Students marks")
	StudentsMarksResDTO studentsMarks;
}
