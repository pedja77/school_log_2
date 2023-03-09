package com.iktpreobuka.schoollogtwo.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

import com.iktpreobuka.schoollogtwo.entities.SchoolYearEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.SchoolYearDTO;
import com.iktpreobuka.schoollogtwo.repositories.SchoolYearRepository;

@RestController
@RequestMapping(path = "/api/v1/schoolyears")
public class SchoolYearController {

	@Autowired
	private SchoolYearRepository syRepo;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT", "ROLE_PARENT"})
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getSchoolYearById(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		SchoolYearEntity year = syRepo.findById(id).orElseThrow();
		System.out.println(year.getStartDate());
		return new ResponseEntity<>(year, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping
	public ResponseEntity<?> createSchoolYear(@RequestBody SchoolYearDTO newSchoolYear, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		SchoolYearEntity schoolYear = new SchoolYearEntity();
		
		schoolYear.setStartDate(LocalDate.parse(newSchoolYear.getStartDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		schoolYear.setEndDate(LocalDate.parse(newSchoolYear.getEndDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		
		syRepo.save(schoolYear);
		
		return new ResponseEntity<>(newSchoolYear, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateSchoolYear(@PathVariable Integer id, @RequestBody SchoolYearDTO updatedSchoolYear, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		SchoolYearEntity schoolYear = syRepo.findById(id).orElseThrow();
		LocalDate start = LocalDate.parse(updatedSchoolYear.getStartDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		LocalDate end = LocalDate.parse(updatedSchoolYear.getEndDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		if (updatedSchoolYear.getStartDate() != null && !schoolYear.getStartDate().equals(start)) {
			schoolYear.setStartDate(start);
		}
		if (updatedSchoolYear.getEndDate() != null && !schoolYear.getEndDate().equals(end)) {
			schoolYear.setEndDate(end);
		}
		
		syRepo.save(schoolYear);
		
		return new ResponseEntity<>(updatedSchoolYear, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteSchoolYear(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		// METHOD STUB - Koliko je pametno brisati skolsku godinu???????!!!!!!?????
		return null;
	}
	
	
}
