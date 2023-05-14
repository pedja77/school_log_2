package com.iktpreobuka.schoollogtwo.entities.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iktpreobuka.schoollogtwo.entities.SubjectEntity;

public class SubjectDTO {
	
	@JsonProperty
	private Integer id;

	@NotNull
	@JsonProperty(value = "subjectName")
	private String subjectName;
	@NotNull
	@JsonProperty(value = "weeklyFund")
	private Integer weeklyFund;
	@NotNull
	@JsonProperty(value = "grade")
	@Range(min = 1, max = 8)
	private Integer grade; // ne sifra razreda, nego "vrednost razreda" ~ godina
	@JsonProperty
	private List<UserDTO> teachers;
	@JsonProperty
	private List<UserDTO> students;
	
	public SubjectDTO() {
		super();
	}

	public SubjectDTO(@NotNull String subjectName, @NotNull Integer weeklyFund,
			@NotNull @Range(min = 1, max = 8) Integer grade) {
		super();
		this.subjectName = subjectName;
		this.weeklyFund = weeklyFund;
		this.grade = grade;
		this.teachers = null;
		this.students = null;
	}
	

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getWeeklyFund() {
		return weeklyFund;
	}

	public void setWeeklyFund(Integer weeklyFund) {
		this.weeklyFund = weeklyFund;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public List<UserDTO> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<UserDTO> teachers) {
		this.teachers = teachers;
	}

	public List<UserDTO> getStudents() {
		return students;
	}

	public void setStudents(List<UserDTO> students) {
		this.students = students;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
