package com.iktpreobuka.schoollogtwo.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.iktpreobuka.schoollogtwo.entities.dto.SubjectsCollectionDTO;
import com.iktpreobuka.schoollogtwo.repositories.StudentRepository;
import com.iktpreobuka.schoollogtwo.services.StudentService;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	StudentRepository studentRepository;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentRepository.findById(id).get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createStudent(@RequestBody StudentDTO newStudent, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.createStudent(newStudent), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{studentId}/parent/{parentId}")
	public ResponseEntity<?> addParentToStudent(@PathVariable Integer studentId, @PathVariable Integer parentId, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.addParentToStudent(studentId, parentId), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO updatedStudent, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.updateStudent(id, updatedStudent), HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}/subjects")
	public ResponseEntity<?> addSubjectsToStudent(@PathVariable Integer id, @RequestBody SubjectsCollectionDTO subjects, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.addSubjectsToStudent(id, subjects), HttpStatus.OK);
	}
	
	@GetMapping(path = "/marks")
	public ResponseEntity<?> methodName(Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.getStudentsMarks(p.getName()), HttpStatus.OK);
	}
	
}
