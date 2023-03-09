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

import com.iktpreobuka.schoollogtwo.entities.dto.GradeDTO;
import com.iktpreobuka.schoollogtwo.repositories.GradeRepository;
import com.iktpreobuka.schoollogtwo.services.GradeService;

@RestController
@RequestMapping(path = "/api/v1/grades")
public class GradeController {
	
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private GradeService gradeService;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Secured({"ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT", "ROLE_PARENT"})
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getGradeById(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(gradeRepository.findById(id).orElseThrow(), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping
	public ResponseEntity<?> createGrade(@RequestBody GradeDTO newGrade, Principal p){
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(gradeService.createGrade(newGrade), HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateGrade(@PathVariable Integer id, @RequestBody GradeDTO updatedGrade, Principal p) {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(gradeService.updateGrade(id, updatedGrade), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteGrade(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(gradeService.deleteGrade(id), HttpStatus.OK);
	}
}
