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
		Map<String, String> errorsMap = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach(Error -> {
			errorsMap.put(Error.getField(), Error.getDefaultMessage());
		});
		Map<String, Object> methodErrorsObjecMap = new HashMap<String, Object>();
		methodErrorsObjecMap.put("messga", "Unable to process your errors");
		methodErrorsObjecMap.put("Status", "Failed");
		methodErrorsObjecMap.put("errors", methodErrorsObjecMap);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(methodErrorsObjecMap);
	}
}
