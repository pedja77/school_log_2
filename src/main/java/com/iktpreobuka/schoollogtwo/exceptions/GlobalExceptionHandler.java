package com.iktpreobuka.schoollogtwo.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MultipartException.class)
	public String handleError(MultipartException e) {
		
		return "Something went wrong!!!";
	}
	
	@ExceptionHandler(NoSuchFieldException.class)
	public ResponseEntity<String> handleNoSuchFieldException(NoSuchFieldException e) {
		return new ResponseEntity<String>("Requested non existent field!", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
		String msg = "Requested non exsistent entity: " + e.getMessage();
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	}
}
