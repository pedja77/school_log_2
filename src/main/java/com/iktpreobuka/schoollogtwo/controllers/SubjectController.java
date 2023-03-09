package com.iktpreobuka.schoollogtwo.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getSubjectById(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(subjectRepository.findById(id).orElseThrow(), HttpStatus.OK);
	}
	

	@Secured({"ROLE_ADMIN"})
	@PostMapping
	public ResponseEntity<?> createSubject(@RequestBody SubjectDTO newSubject, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(subjectService.createSubject(newSubject), HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateSubject(@PathVariable Integer id, @RequestBody SubjectDTO updatedSubject, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(subjectService.updateSubject(id, updatedSubject), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteSubject(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(subjectService.deleteSubject(id), HttpStatus.OK);
	}
}
