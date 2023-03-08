package com.iktpreobuka.schoollogtwo.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;
import com.iktpreobuka.schoollogtwo.repositories.UserRepository;
import com.iktpreobuka.schoollogtwo.services.UserService;
import com.iktpreobuka.schoollogtwo.util.UserMapper;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

	@Autowired
	private UserMapper mapper;
	@Autowired
	UserService userService;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDTO newUser, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(userService.createUser(newUser), HttpStatus.CREATED);
	}
}
