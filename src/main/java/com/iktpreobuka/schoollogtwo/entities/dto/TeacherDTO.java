package com.iktpreobuka.schoollogtwo.entities.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherDTO extends UserDTO {

	@JsonProperty(value = "weeklyClasses")
	private Integer weeklyClasses;
	
//	@JsonProperty
//	private List<UserDTO> students;
	
	@JsonProperty 
	List<SubjectDTO> subjects;
	
	

	public TeacherDTO() {
		super();
	}

	public Integer getWeeklyClasses() {
		return weeklyClasses;
	}

	public void setWeeklyClasses(Integer weeklyClasses) {
		this.weeklyClasses = weeklyClasses;
	}

//	public List<UserDTO> getStudents() {
//		return students;
//	}
//
//	public void setStudents(List<UserDTO> students) {
//		this.students = students;
//	}

	public List<SubjectDTO> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectDTO> subjects) {
		this.subjects = subjects;
	}
	
	
}
