package com.iktpreobuka.schoollogtwo.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.SchoolYearEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.SchoolYearDTO;
import com.iktpreobuka.schoollogtwo.repositories.SchoolYearRepository;

@RestController
@RequestMapping(path = "/api/v1/schoolyears")
public class SchoolYearController {

	@Autowired
	private SchoolYearRepository syRepo;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getSchoolYearById(@PathVariable Integer id) {
		SchoolYearEntity year = syRepo.findById(id).orElseThrow();
		System.out.println(year.getStartDate());
		return new ResponseEntity<>(year, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createSchoolYear(@RequestBody SchoolYearDTO newSchoolYear) {
		SchoolYearEntity schoolYear = new SchoolYearEntity();
		schoolYear.setStartDate(LocalDate.parse(newSchoolYear.getStartDate()));
		schoolYear.setEndDate(LocalDate.parse(newSchoolYear.getEndDate()));
		
		syRepo.save(schoolYear);
		
		return new ResponseEntity<>(newSchoolYear, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateSchoolYear(@PathVariable Integer id, @RequestBody SchoolYearDTO updatedSchoolYear) {
		SchoolYearEntity schoolYear = syRepo.findById(id).orElseThrow();
		LocalDate start = LocalDate.parse(updatedSchoolYear.getStartDate());
		LocalDate end = LocalDate.parse(updatedSchoolYear.getEndDate());
		if (updatedSchoolYear.getStartDate() != null && !schoolYear.getStartDate().equals(start)) {
			schoolYear.setStartDate(start);
		}
		if (updatedSchoolYear.getEndDate() != null && !schoolYear.getEndDate().equals(end)) {
			schoolYear.setEndDate(end);
		}
		
		syRepo.save(schoolYear);
		
		return new ResponseEntity<>(updatedSchoolYear, HttpStatus.OK);
	}
	
	
}
