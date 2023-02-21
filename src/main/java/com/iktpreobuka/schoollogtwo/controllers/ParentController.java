package com.iktpreobuka.schoollogtwo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping
	public ResponseEntity<?> allParents() {
		return new ResponseEntity<>(parentRepository.findByDeleted(false), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createParent(@RequestBody ParentDTO newParent) {
		return new ResponseEntity<>(parentService.createParent(newParent), HttpStatus.CREATED);
	}
}
