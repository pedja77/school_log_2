package com.iktpreobuka.schoollogtwo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping
	public ResponseEntity<?> allRoles() {
		return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createUserRole(@RequestParam(name = "role") String role) {
		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setRoleName(role);
		
		roleRepository.save(userRole);
		
		return new ResponseEntity<>(userRole, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable Integer id) {
		UserRoleEntity role = roleRepository.findById(id).get();
		roleRepository.delete(role);
		
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> createUserRole(@PathVariable Integer id, @RequestParam(name = "role") String role) {
		UserRoleEntity userRole = roleRepository.findById(id).orElseThrow();
		if (role != null && !userRole.getRoleName().equals(role))
			userRole.setRoleName(role);
		
		roleRepository.save(userRole);
		
		return new ResponseEntity<>(userRole, HttpStatus.OK);
	}
}
