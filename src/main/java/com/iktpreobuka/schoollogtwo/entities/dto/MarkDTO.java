package com.iktpreobuka.schoollogtwo.entities.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	@NotNull
	@JsonProperty(value = "Mark")
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
	
	
}
