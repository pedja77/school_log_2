package com.iktpreobuka.schoollogtwo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.dto.ParentDTO;
import com.iktpreobuka.schoollogtwo.repositories.ParentRepository;
import com.iktpreobuka.schoollogtwo.services.ParentService;

@RestController
@RequestMapping(path = "/api/v1/parents")
public class ParentController {

	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private ParentService parentService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getParentById(@PathVariable Integer id) {
		return new ResponseEntity<>(parentRepository.findById(id).get(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> allParents() {
		return new ResponseEntity<>(parentRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createParent(@RequestBody ParentDTO newParent) {
		return new ResponseEntity<>(parentService.createParent(newParent), HttpStatus.CREATED);
	}
}
