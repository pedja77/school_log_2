package com.iktpreobuka.schoollogtwo.entities.dto.responses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"student", "grade", "marks"})
public class StudentsMarksResDTO {
	
	@JsonProperty(value = "Students name")
	private String student;
	
	@JsonProperty(value = "Grade")
	private Integer grade;

	@JsonProperty(value = "Marks by subject")
	Map<String, List<?>> marks = new HashMap<>();

	public StudentsMarksResDTO() {
		super();
	}

	public Map<String, List<?>> getMarks() {
		return marks;
	}

	public void setMarks(Map<String, List<?>> marks) {
		this.marks = marks;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	
}
