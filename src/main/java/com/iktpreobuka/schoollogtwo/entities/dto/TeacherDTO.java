package com.iktpreobuka.schoollogtwo.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherDTO extends UserDTO {

	@JsonProperty(value = "weeklyClasses")
	private Integer weeklyClasses;

	public TeacherDTO() {
		super();
	}

	public Integer getWeeklyClasses() {
		return weeklyClasses;
	}

	public void setWeeklyClasses(Integer weeklyClasses) {
		this.weeklyClasses = weeklyClasses;
	}
	
	
}
