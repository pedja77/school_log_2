package com.iktpreobuka.schoollogtwo.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.dto.MarkDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.responses.MarkResDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.responses.MarksBySubjectResDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.responses.StudentsMarksResDTO;
import com.iktpreobuka.schoollogtwo.services.MarkService;

@RestController
@RequestMapping(path = "/api/v1/marks")
public class MarkController {

	@Autowired
	private MarkService markService;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Secured({"ROLE_TEACHER", "ROLE_ADMIN"})
	@PostMapping
	public ResponseEntity<?> createMark(@Valid @RequestBody MarkDTO newMark,@RequestParam Boolean isFinal, Principal p) throws IllegalArgumentException, Exception {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(markService.createMark(newMark, p.getName(), isFinal)
				.orElseThrow(IllegalArgumentException::new), 
				HttpStatus.CREATED);
	}
	
	
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteMark(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(markService.deleteMark(id, p), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateMark(@PathVariable Integer id, @Valid @RequestBody MarkDTO mark, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(markService.updateMark(id, mark, p).orElseThrow(), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getMark(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(markService.getMark(id), HttpStatus.OK);
	}
}
