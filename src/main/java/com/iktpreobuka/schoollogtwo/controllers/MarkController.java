package com.iktpreobuka.schoollogtwo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.dto.MarkDTO;
import com.iktpreobuka.schoollogtwo.services.MarkService;

@RestController
@RequestMapping(path = "/api/v1/marks")
public class MarkController {

	@Autowired
	private MarkService markService;
	
	@PostMapping
	public ResponseEntity<?> createMark(@RequestBody MarkDTO newMark) {
		return new ResponseEntity<>(markService.createMark(newMark), HttpStatus.CREATED);
	}
}
