package com.iktpreobuka.schoollogtwo.entities.dto;

public class TeacherDTO extends UserDTO {

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
