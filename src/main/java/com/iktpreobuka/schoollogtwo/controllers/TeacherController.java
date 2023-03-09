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

import com.iktpreobuka.schoollogtwo.entities.dto.StudentsCollectionDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.SubjectsCollectionDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.TeacherDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.TeachersCollectionDTO;
import com.iktpreobuka.schoollogtwo.repositories.TeacherRepository;
import com.iktpreobuka.schoollogtwo.services.TeacherService;

@RestController
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TeacherRepository teacherRepository;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
//	@PostMapping
//	public ResponseEntity<?> createTeacher(@RequestBody TeacherDTO newTeacher, Principal p) {
//		String methodName = new Object() {
//		}.getClass().getEnclosingMethod().getName();
//		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
//		
//		return new ResponseEntity<>(teacherService.createTeacher(newTeacher), HttpStatus.CREATED);
//	}
//	
//	@PutMapping(path = "/{id}")
//	public ResponseEntity<?> updateTeacher(@PathVariable Integer id, @RequestBody TeacherDTO updatedTeacher, Principal p) {
//		String methodName = new Object() {
//		}.getClass().getEnclosingMethod().getName();
//		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
//		
//		return new ResponseEntity<>(teacherService.updateTeacher(id, updatedTeacher), HttpStatus.OK);
//	} 
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(teacherRepository.findById(id).orElseThrow(), HttpStatus.OK);
	}
	
//	@DeleteMapping(path = "/{id}")
//	public ResponseEntity<?> deleteTeacher(@PathVariable Integer id, Principal p) {
//		String methodName = new Object() {
//		}.getClass().getEnclosingMethod().getName();
//		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
//		
//		return new ResponseEntity<>(teacherService.deleteTeacher(id), HttpStatus.OK);
//	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/bulk")
	public ResponseEntity<?> bulkCreate(@RequestBody TeachersCollectionDTO teachers, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(teacherService.bulkCreate(teachers), HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping(path = "/{id}/subjects")
	public ResponseEntity<?> addSubjectsToTeacher(@PathVariable Integer id, @RequestBody SubjectsCollectionDTO subjects, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(teacherService.addSubjectsToTeacher(id, subjects), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping(path = "/{id}/subject/{subjectId}/students")
	public ResponseEntity<?> addStudentsToTeacher(@PathVariable Integer id,
			@PathVariable Integer subjectId,  @RequestBody StudentsCollectionDTO students, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(teacherService.addStudentsToTeacherBySubject(id, subjectId, students)
				.orElseThrow(IllegalArgumentException::new), 
				HttpStatus.OK);
	}
}
