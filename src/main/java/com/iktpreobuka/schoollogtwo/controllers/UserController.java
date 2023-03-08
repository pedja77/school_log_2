package com.iktpreobuka.schoollogtwo.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;
import com.iktpreobuka.schoollogtwo.repositories.UserRepository;
import com.iktpreobuka.schoollogtwo.services.UserService;
import com.iktpreobuka.schoollogtwo.util.UserCustomValidator;
import com.iktpreobuka.schoollogtwo.util.UserMapper;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

	@Autowired
	private UserMapper mapper;
	@Autowired
	UserService userService;
	@Autowired
	UserCustomValidator userValidator;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(userValidator);
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@Valid  @RequestBody UserDTO newUser, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(userService.createUser(newUser), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO user, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getUser(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(mapper.mapToUserDTO(userService.getUser(id)), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllUsers(Principal p) {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
}
