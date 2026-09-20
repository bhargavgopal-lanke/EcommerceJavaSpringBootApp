package com.example.demo.Exceptions;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleExceptions(Exception ex) {
		Map<String, String> errorsMap = new HashMap<String, String>();
		errorsMap.put("message", ex.getMessage());
		errorsMap.put("Status", "Failed");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorsMap);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleMethodExceptions(MethodArgumentNotValidException ex) {
		// Collect field validation errors into a simple map
		Map<String, String> errorsMap = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach(err -> {
			errorsMap.put(err.getField(), err.getDefaultMessage());
		});

		// Build top-level response object and attach the errors map (not itself)
		Map<String, Object> methodErrorsObjectMap = new HashMap<String, Object>();
		methodErrorsObjectMap.put("message", "Unable to process your errors");
		methodErrorsObjectMap.put("Status", "Failed");
		methodErrorsObjectMap.put("errors", errorsMap);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(methodErrorsObjectMap);
	}
}
