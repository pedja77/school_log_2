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
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getSubjectById(@PathVariable Integer id) {
		return new ResponseEntity<>(subjectRepository.findById(id).orElseThrow(), HttpStatus.OK);
	}
	

	@PostMapping
	public ResponseEntity<?> createSubject(@RequestBody SubjectDTO newSubject) {
		return new ResponseEntity<>(subjectService.createSubject(newSubject), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateSubject(@PathVariable Integer id, @RequestBody SubjectDTO updatedSubject) {
		return new ResponseEntity<>(subjectService.updateSubject(id, updatedSubject), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteSubject(@PathVariable Integer id) {
		return new ResponseEntity<>(subjectService.deleteSubject(id), HttpStatus.OK);
	}
}
