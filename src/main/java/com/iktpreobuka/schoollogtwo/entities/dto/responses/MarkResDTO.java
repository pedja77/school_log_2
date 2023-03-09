package com.iktpreobuka.schoollogtwo.entities.dto.responses;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"id", "teacher", "student", "value", "comment", "semester", "schoolYear", "givenOn", "changedOn", "isFinal"} )
public class MarkResDTO {
	
	@JsonProperty(value = "Teacher")
	private String teacher;
	
	@JsonProperty(value = "Student")
	private String student;
	
	@JsonProperty(value = "Id")
	private Integer id;

	@JsonProperty(value = "Mark")
	private Integer value;
	
	@JsonProperty(value = "Comment")
	private String comment;
	
	@JsonProperty(value = "Semester")
	private Integer semester;
	
	@JsonProperty(value = "School year")
	private String schoolYear;

	@JsonProperty(value = "Given on")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate givenOn;
	
	@JsonProperty(value = "Changed on")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate changedOn;
	
	@JsonProperty(value = "Final")
	private Boolean isFinal;
	
	public MarkResDTO() {
		super();
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public LocalDate getGivenOn() {
		return givenOn;
	}

	public void setGivenOn(LocalDate givenOn) {
		this.givenOn = givenOn;
	}

	public LocalDate getChangedOn() {
		return changedOn;
	}

	public void setChangedOn(LocalDate changedOn) {
		this.changedOn = changedOn;
	}

	public Boolean getIsFinal() {
		return isFinal;
	}

	public void setIsFinal(Boolean isFinal) {
		this.isFinal = isFinal;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
