package com.iktpreobuka.schoollogtwo.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.SchoolYearEntity;
import com.iktpreobuka.schoollogtwo.entities.SemesterEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.SemesterDTO;
import com.iktpreobuka.schoollogtwo.repositories.SchoolYearRepository;
import com.iktpreobuka.schoollogtwo.repositories.SemesterRepository;

@RestController
@RequestMapping(path = "/api/v1/semesters")
public class SemesterController {

	@Autowired
	private SemesterRepository semesterRepo;
	@Autowired
	private SchoolYearRepository syRepo;

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@GetMapping
	public ResponseEntity<?> getByMarkDate(@RequestParam String md, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));

		LocalDate markDate = LocalDate.parse(md);
		return new ResponseEntity<>(semesterRepo.findByMarkDate(markDate), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getSemesterById(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));

		return new ResponseEntity<>(semesterRepo.findById(id).orElseThrow(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createSemester(@Valid @RequestBody SemesterDTO newSemester, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		SemesterEntity semester = new SemesterEntity();
		semester.setStartDate(LocalDate.parse(newSemester.getStartDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		semester.setEndDate(LocalDate.parse(newSemester.getEndDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		semester.setSemester(newSemester.getSemester());
		semester.setSchoolYear(syRepo.findById(newSemester.getSchoolYearId()).orElseThrow());

		semesterRepo.save(semester);

		return new ResponseEntity<>(newSemester, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateSemester(@PathVariable Integer id, @Valid @RequestBody SemesterDTO updatedSemester,
			Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));

		SemesterEntity semester = semesterRepo.findById(id).orElseThrow();
		LocalDate start = LocalDate.parse(updatedSemester.getStartDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		LocalDate end = LocalDate.parse(updatedSemester.getEndDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		SchoolYearEntity year = syRepo.findById(updatedSemester.getSchoolYearId()).orElseThrow();

		if (!semester.getStartDate().equals(start)) {
			semester.setStartDate(start);
		}
		if (!semester.getEndDate().equals(end)) {
			semester.setEndDate(end);
		}
		if (!semester.getSemester().equals(updatedSemester.getSemester())) {
			semester.setSemester(updatedSemester.getSemester());
		}
		if (!semester.getSchoolYear().equals(year)) {
			semester.setSchoolYear(year);
		}

		semesterRepo.save(semester);

		return new ResponseEntity<>(updatedSemester, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteSemester(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		// Koliko je pametno brisati polugodiste??????!!!!!?????
		return null;
	}
}
