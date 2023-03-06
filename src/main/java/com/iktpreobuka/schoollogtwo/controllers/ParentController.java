package com.iktpreobuka.schoollogtwo.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.dto.ParentDTO;
import com.iktpreobuka.schoollogtwo.repositories.ParentRepository;
import com.iktpreobuka.schoollogtwo.services.ParentService;
import com.iktpreobuka.schoollogtwo.util.UserCustomValidator;

@RestController
@RequestMapping(path = "/api/v1/parents")
public class ParentController {

	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private ParentService parentService;
	
	@Autowired
	UserCustomValidator userValidator;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(userValidator);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getParentById(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(parentRepository.findById(id).get(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> allParents(Principal p) {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(parentRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createParent(@Valid @RequestBody ParentDTO newParent, Principal p) {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(parentService.createParent(newParent), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateParent(@PathVariable Integer id,  @RequestBody ParentDTO updatedParent, Principal p) {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(parentService.updateParent(id, updatedParent), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteParent(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(parentService.deleteParent(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "/marks")
	public ResponseEntity<?> getAllMarksForAllStudents(Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(parentService.getAllMarks(p.getName()), HttpStatus.OK);
	}
	
	@GetMapping(path = "/marks/student/{id}")
	public ResponseEntity<?> getMarksByStudent(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(parentService.getMarksByStudent(p.getName(), id)
				.orElseThrow(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/marks/student/{studentId}/subject/{subjectId}")
	public ResponseEntity<?> getMarksByStudentAndSubject(@PathVariable Integer studentId, @PathVariable Integer subjectId, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		return new ResponseEntity<>(parentService.getMarksByStudentAndSubject(p.getName(), studentId, subjectId)
				.orElseThrow(), HttpStatus.OK);
	}
}
