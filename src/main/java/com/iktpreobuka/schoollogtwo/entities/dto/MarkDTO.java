package com.iktpreobuka.schoollogtwo.entities.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class MarkDTO {

	@NotNull
	@JsonProperty(value = "Teacher")
	private Integer teacherId;
	
	@NotNull
	@JsonProperty(value = "Student")
	private Integer studentId;
	
	@NotNull
	@JsonProperty(value = "Subject")
	private Integer subjectId;
	
//	@NotNull
//	@JsonProperty(value = "Semester")
//	private Integer semesterId;
	
//	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
//	protected LocalDate markDate;
	
	@NotNull
	@JsonProperty(value = "Mark")
	@Range(min = 1, max = 5, message = "Mark must be between 1 and 5.")
	private Integer value;
	
	@JsonProperty(value = "Comment")
	private String comment;

	public MarkDTO() {
		super();
	}

	public MarkDTO(Integer teacherId, Integer studentId, Integer subjectId, Integer value, String comment) {
		super();
		this.teacherId = teacherId;
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.value = value;
		this.comment = comment;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
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

//	public Integer getSemesterId() {
//		return semesterId;
//	}
//
//	public void setSemesterId(Integer semesterId) {
//		this.semesterId = semesterId;
//	}

//	public LocalDate getMarkDate() {
//		return markDate;
//	}
//
//	public void setMarkDate(LocalDate markDate) {
//		this.markDate = markDate;
//	}
	
	
}
