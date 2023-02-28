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

import com.iktpreobuka.schoollogtwo.entities.dto.StudentDTO;
import com.iktpreobuka.schoollogtwo.repositories.StudentRepository;
import com.iktpreobuka.schoollogtwo.services.StudentService;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable Integer id) {
		return new ResponseEntity<>(studentRepository.findById(id).get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createStudent(@RequestBody StudentDTO newStudent) {
		return new ResponseEntity<>(studentService.createStudent(newStudent), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{studentId}/parent/{parentId}")
	public ResponseEntity<?> addParentToStudent(@PathVariable Integer studentId, @PathVariable Integer parentId) {
		return new ResponseEntity<>(studentService.addParentToStudent(studentId, parentId), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
		return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.OK);
	}
	
}
