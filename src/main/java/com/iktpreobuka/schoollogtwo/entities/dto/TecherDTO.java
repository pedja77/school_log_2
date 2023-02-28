package com.iktpreobuka.schoollogtwo.entities.dto;

public class TecherDTO extends UserDTO {

	private Integer weeklyClasses;

	public TecherDTO() {
		super();
	}

	public Integer getWeeklyClasses() {
		return weeklyClasses;
	}

	public void setWeeklyClasses(Integer weeklyClasses) {
		this.weeklyClasses = weeklyClasses;
	}
	
	
}
