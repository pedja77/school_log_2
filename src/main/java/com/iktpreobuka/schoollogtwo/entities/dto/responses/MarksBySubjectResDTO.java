package com.iktpreobuka.schoollogtwo.entities.dto.responses;

import java.util.ArrayList;
import java.util.List;

public class MarksBySubjectResDTO {

	private String subject;
	private List<MarkResDTO> marks =new ArrayList<>();

	public MarksBySubjectResDTO() {
		super();
	}

	public List<MarkResDTO> getMarks() {
		return marks;
	}

	public void setMarks(List<MarkResDTO> marks) {
		this.marks = marks;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
