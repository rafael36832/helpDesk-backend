package com.brum.dev.helpDeskUdemy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brum.dev.helpDeskUdemy.exceptions.Entities.DataIntegrityViolationException;
import com.brum.dev.helpDeskUdemy.exceptions.Entities.NotFoundException;
import com.brum.dev.helpDeskUdemy.exceptions.errors.StandardError;
import com.brum.dev.helpDeskUdemy.exceptions.errors.ValidationError;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> notFoundException(NotFoundException ex, HttpServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Object not found", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
			HttpServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Data Integrity Violation", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> constraintViolationException(ConstraintViolationException ex,
			HttpServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Constraint limitation Exception", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<StandardError> loginException(ConstraintViolationException ex,
			HttpServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(),
				"Bad Credential Exception", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		ValidationError validationErrors = new ValidationError(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), "Validation Fields Errors", "Erro na validação dos campos", request.getRequestURI());

		for (FieldError err : ex.getBindingResult().getFieldErrors()) {
			validationErrors.addError(err.getField(), err.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);

	}

}
