package com.iktpreobuka.schoollogtwo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.SubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.SubjectDTO;

@RestController
@RequestMapping(path = "/api/v1/subjects")
public class SubjectController {

	@PostMapping
	public ResponseEntity<?> createSubject(@RequestBody SubjectDTO newSubject) {
		SubjectEntity subject = new SubjectEntity();
		subject.setSubjectName(newSubject.getSubjectName());
		subject.setWeeklyFund(newSubject.getWeeklyFund());
		
		return null;
	}
}
