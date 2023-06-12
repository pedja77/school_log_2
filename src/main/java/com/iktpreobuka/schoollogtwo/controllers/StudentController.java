package com.iktpreobuka.schoollogtwo.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Secured({"ROLE_ADMIN", "ROLE_STUDENT", "ROLE_TEACHER"})
	@GetMapping(path = "/grade/{grade}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getStudentsByGrade(@PathVariable Integer grade, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.getStudentsByGrade(grade), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_STUDENT", "ROLE_TEACHER"})
	@GetMapping
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getAllStudents(Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
	}
	
	
	@Secured({"ROLE_ADMIN", "ROLE_STUDENT", "ROLE_TEACHER"})
	@GetMapping(path = "/teacher/{teacherId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getStudentsByTeacher(@PathVariable Integer teacherId, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.getStudentsByTeacher(teacherId), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentRepository.findById(id).get(), HttpStatus.OK);
	}
	
//	@PostMapping
//	public ResponseEntity<?> createStudent(@RequestBody StudentDTO newStudent, Principal p) {
//		String methodName = new Object() {
//		}.getClass().getEnclosingMethod().getName();
//		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
//		
//		return new ResponseEntity<>(studentService.createStudent(newStudent), HttpStatus.CREATED);
//	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping(path = "/{studentId}/parent/{parentId}")
	public ResponseEntity<?> addParentToStudent(@PathVariable Integer studentId, @PathVariable Integer parentId, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.addParentToStudent(studentId, parentId), HttpStatus.OK);
	}
	
//	@DeleteMapping(path = "/{id}")
//	public ResponseEntity<?> deleteStudent(@PathVariable Integer id, Principal p) {
//		String methodName = new Object() {
//		}.getClass().getEnclosingMethod().getName();
//		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
//		
//		return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.OK);
//	}
//	
//	@PutMapping(path = "/{id}")
//	public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO updatedStudent, Principal p) {
//		String methodName = new Object() {
//		}.getClass().getEnclosingMethod().getName();
//		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
//		
//		return new ResponseEntity<>(studentService.updateStudent(id, updatedStudent), HttpStatus.OK);
//	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping(path = "/{id}/subjects")
	public ResponseEntity<?> addSubjectsToStudent(@PathVariable Integer id, @RequestBody SubjectsCollectionDTO subjects, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.addSubjectsToStudent(id, subjects), HttpStatus.OK);
	}
	
	@Secured({"ROLE_STUDENT"})
	@GetMapping(path = "/marks")
	public ResponseEntity<?> getAllMarks(Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(studentService.getStudentsMarks(p.getName()), HttpStatus.OK);
	}
	
	@Secured({"ROLE_STUDENT"})
	@GetMapping(path = "/marks/subject/{id}")
	public ResponseEntity<?> getAllMarksBySubject(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(studentService.getStudentsMarksBySubject(p.getName(), id), HttpStatus.OK);
	}
	
}
