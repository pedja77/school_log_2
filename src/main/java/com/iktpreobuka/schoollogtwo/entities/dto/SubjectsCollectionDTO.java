package com.iktpreobuka.schoollogtwo.entities.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectsCollectionDTO {

	@JsonProperty(value = "Subjects")
	private List<Integer> subjects;

	public SubjectsCollectionDTO() {
		super();
	}

	public List<Integer> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Integer> subjects) {
		this.subjects = subjects;
	}
	
	
}
