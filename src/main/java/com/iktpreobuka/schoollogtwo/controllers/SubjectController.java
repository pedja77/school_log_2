package com.iktpreobuka.schoollogtwo.controllers;

import java.security.Principal;

import javax.validation.Valid;

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

import com.iktpreobuka.schoollogtwo.entities.dto.SubjectDTO;
import com.iktpreobuka.schoollogtwo.repositories.SubjectRepository;
import com.iktpreobuka.schoollogtwo.services.SubjectService;

@RestController
@RequestMapping(path = "/api/v1/subjects")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SubjectRepository subjectRepository;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Secured({"ROLE_ADMIN", "ROLE_STUDENT", "ROLE_TEACHER"})
	@GetMapping
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getAllSubjects(Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(subjectService.getAllSubjectDTOs(), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_STUDENT", "ROLE_TEACHER"})
	@GetMapping(path="/teacher/{teacherId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getSubjectsByTeacher(@PathVariable Integer teacherId, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(subjectService.getSubjectsByTeacher(teacherId), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_STUDENT", "ROLE_TEACHER"})
	@GetMapping(path = "/search")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getAllSubjectsFiltered(@RequestParam String query, @RequestParam Integer grade, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(subjectService.getAllSubjectDTOsFiltered(query, grade), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_STUDENT", "ROLE_TEACHER"})
	@GetMapping(path = "/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getSubjectById(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(subjectService.getSubjectDTO(id), HttpStatus.OK);
	}
	

	@Secured({"ROLE_ADMIN"})
	@PostMapping
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> createSubject(@Valid @RequestBody SubjectDTO newSubject, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(subjectService.createSubject(newSubject), HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping(path = "/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> updateSubject(@PathVariable Integer id, @Valid @RequestBody SubjectDTO updatedSubject, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(subjectService.updateSubject(id, updatedSubject), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping(path = "/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> deleteSubject(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(subjectService.deleteSubject(id), HttpStatus.OK);
	}
}
