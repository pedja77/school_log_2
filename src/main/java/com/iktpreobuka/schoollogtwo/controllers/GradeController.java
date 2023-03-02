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

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getGradeById(@PathVariable Integer id) {
		return new ResponseEntity<>(gradeRepository.findById(id).orElseThrow(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createGrade(@RequestBody GradeDTO newGrade){
		return new ResponseEntity<>(gradeService.createGrade(newGrade), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> deleteGrade(@PathVariable Integer id, @RequestBody GradeDTO updatedGrade) {
		return new ResponseEntity<>(gradeService.updateGrade(id, updatedGrade), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteGrade(@PathVariable Integer id) {
		return new ResponseEntity<>(gradeService.deleteGrade(id), HttpStatus.OK);
	}
}
