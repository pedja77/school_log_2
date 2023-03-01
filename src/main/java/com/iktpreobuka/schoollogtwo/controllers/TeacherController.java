package com.iktpreobuka.schoollogtwo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.dto.TeacherDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.TeachersCollectionDTO;
import com.iktpreobuka.schoollogtwo.repositories.TeacherRepository;
import com.iktpreobuka.schoollogtwo.services.TeacherService;

@RestController
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TeacherRepository teacherRepository;
	
	@PostMapping
	public ResponseEntity<?> createTeacher(@RequestBody TeacherDTO newTeacher) {
		return new ResponseEntity<>(teacherService.createTeacher(newTeacher), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateTeacher(@PathVariable Integer id, @RequestBody TeacherDTO updatedTeacher) {
		return new ResponseEntity<>(teacherService.updateTeacher(id, updatedTeacher), HttpStatus.OK);
	} 
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable Integer id) {
		return new ResponseEntity<>(teacherRepository.findById(id).orElseThrow(), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable Integer id) {
		return new ResponseEntity<>(teacherService.deleteTeacher(id), HttpStatus.OK);
	}
	
	@PostMapping(path = "/bulk")
	public ResponseEntity<?> bulkCreate(@RequestBody TeachersCollectionDTO teachers) {
		return new ResponseEntity<>(teacherService.bulkCreate(teachers), HttpStatus.CREATED);
	}
}
