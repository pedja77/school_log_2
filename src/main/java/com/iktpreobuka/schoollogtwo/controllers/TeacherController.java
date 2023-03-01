package com.iktpreobuka.schoollogtwo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.dto.TeacherDTO;
import com.iktpreobuka.schoollogtwo.services.TeacherService;

@RestController
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@PostMapping
	public ResponseEntity<?> createTeacher(@RequestBody TeacherDTO newTeacher) {
		return new ResponseEntity<>(teacherService.createTeacher(newTeacher), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateTeacher(@PathVariable Integer id, @RequestBody TeacherDTO updatedTeacher) {
		return new ResponseEntity<>(teacherService.updateTeacher(id, updatedTeacher), HttpStatus.OK);
	}
}
