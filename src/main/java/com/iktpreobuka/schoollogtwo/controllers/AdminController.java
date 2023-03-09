package com.iktpreobuka.schoollogtwo.controllers;

import java.io.IOException;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;
import com.iktpreobuka.schoollogtwo.services.AdminService;

@RestController
@RequestMapping(path = "/api/v1/admins")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
//	@PostMapping
//	private ResponseEntity<?> createAdmin(@RequestBody UserDTO user, Principal p) {
//		String methodName = new Object() {
//		}.getClass().getEnclosingMethod().getName();
//		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
//		return new ResponseEntity<>(adminService.createAdmin(user), HttpStatus.CREATED);
//	}
//	
//	@PutMapping(path = "/{id}")
//	public ResponseEntity<?> updateAdmin(@PathVariable Integer id, @RequestBody UserDTO updatedAdmin, Principal p) {
//		String methodName = new Object() {
//		}.getClass().getEnclosingMethod().getName();
//		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
//		return new ResponseEntity<>(adminService.updateAdmin(id, updatedAdmin), HttpStatus.OK);
//	}
//	
//	@DeleteMapping(path = "/{id}")
//	public ResponseEntity<?> methodName(@PathVariable Integer id,@RequestBody UserDTO user, Principal p) {
//		String methodName = new Object() {
//		}.getClass().getEnclosingMethod().getName();
//		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
//		return new ResponseEntity<>(adminService.updateAdmin(id, user), HttpStatus.OK);
//	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping(path = "logs")
	public ResponseEntity<?> getLogs(Principal p) throws SecurityException, IOException {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info(String.format("[%s] Requested by %s", methodName, p.getName()));
		
		MediaType contentType = MediaType.TEXT_PLAIN;
		InputStreamResource resource = adminService.getLogs();
		
		return ResponseEntity.ok()
				.contentType(contentType)
				.body(resource);
		//return new ResponseEntity<>(returnVal, HttpStatus.OK);
	}
}
