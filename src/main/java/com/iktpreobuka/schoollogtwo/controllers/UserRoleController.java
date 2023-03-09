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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.UserRoleEntity;
import com.iktpreobuka.schoollogtwo.repositories.UserRoleRepository;

@RestController
@RequestMapping(path = "/api/v1/roles")
public class UserRoleController {
	
	@Autowired
	private UserRoleRepository roleRepository;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping
	public ResponseEntity<?> allRoles(Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.OK);
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping
	public ResponseEntity<?> createUserRole(@RequestParam(name = "role") String role, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setRoleName(role);
		
		roleRepository.save(userRole);
		
		return new ResponseEntity<>(userRole, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable Integer id, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		UserRoleEntity role = roleRepository.findById(id).get();
		roleRepository.delete(role);
		
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> createUserRole(@PathVariable Integer id, @RequestParam(name = "role") String role, Principal p) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		UserRoleEntity userRole = roleRepository.findById(id).orElseThrow();
		if (role != null && !userRole.getRoleName().equals(role))
			userRole.setRoleName(role);
		
		roleRepository.save(userRole);
		
		return new ResponseEntity<>(userRole, HttpStatus.OK);
	}
}
