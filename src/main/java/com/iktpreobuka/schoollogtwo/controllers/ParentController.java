package com.iktpreobuka.schoollogtwo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.ParentDTO;
import com.iktpreobuka.schoollogtwo.repositories.ParentRepository;

@RestController
@RequestMapping(path = "/api/v1/parents")
public class ParentController {

	@Autowired
	private ParentRepository parentRepository;
	
	@GetMapping
	public ResponseEntity<?> allParents() {
		return new ResponseEntity<>(parentRepository.findByDeleted(false), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createParent(@RequestBody ParentDTO newParent) {
		ParentEntity parent = new ParentEntity();
		parent.setFirstName(newParent.getFirstName());
		parent.setLastName(newParent.getLastName());
		parent.setUsername(newParent.getUsername());
		parent.setPassword(newParent.getPassword());
		parent.setEmail(newParent.getEmail());
		
		parentRepository.save(parent);
		
		return new ResponseEntity<>(parent, HttpStatus.CREATED);
	}
}
