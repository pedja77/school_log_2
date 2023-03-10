package com.iktpreobuka.schoollogtwo.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.transaction.TransactionalException;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(MultipartException.class)
	public String handleError(MultipartException e) {

		logger.error(e.getMessage());
		return "Something went wrong!!!";
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
	}
	 
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>("Request data not valid or doesn't comply to relations in the model.", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<?> handleDateTimeParseException(DateTimeParseException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>("Bad date or time format", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchFieldException.class)
	public ResponseEntity<String> handleNoSuchFieldException(NoSuchFieldException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<String>("Requested non existent field!", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
		logger.error(e.getMessage());
		String msg = "Requested non exsistent entity: " + e.getMessage();
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	}
	
	// Proveriti jos kako ovo da se picne
	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<?> handleConstraintViolationException(TransactionSystemException e) {
//		String msg = String.format("%s: %s", e., e.getMessage());
		logger.error(e.getMessage());
		return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = "";
			String errorMessage = "";
			if (error instanceof FieldError) {
				fieldName = ((FieldError) error).getField();
				errorMessage = error.getDefaultMessage();
				logger.error(errorMessage);
			} else if (error instanceof ObjectError) {
				fieldName = ((ObjectError) error).getObjectName();
				errorMessage = error.getDefaultMessage();
				logger.error(errorMessage);
			} 
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
