package com.example.demo.Controller;

import java.lang.reflect.Method;
import java.time.chrono.IsoChronology;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.pojo.SignUpData;
import com.example.demo.services.AuthServices;

import jakarta.validation.Valid;

@RestController
public class AuthController {

	@Autowired
	AuthServices authServices;

//	@PostMapping("signup-api")
//	public ResponseEntity<User> signupApi(@RequestBody SignUpData signUpData) {
//		User userResponseObject = authServices.signupApi(signUpData);
//		Map<String, Object> userResponseMap = new HashMap<String, Object>();
//		userResponseMap.put("data", userResponseObject);
//		userResponseMap.put("status", "Success");
//		return ResponseEntity.status(HttpStatus.OK).body(userResponseObject);
//	}

	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> getEmailApi(@Valid @RequestBody SignUpData signUpData) {
		Boolean emailDataResponse = authServices.getEmailApi(signUpData);
		Map<String, Object> emailrespoMap = new HashMap<String, Object>();
		emailrespoMap.put("status", "Success");
		if (emailDataResponse) {
			Map<String, Object> emailErrorMap = new HashMap<String, Object>();
			emailErrorMap.put("Message", "Email Already exists");
			emailErrorMap.put("Status", "failed");
			return ResponseEntity.status(HttpStatus.OK).body(emailErrorMap);
		} else {
			User userResponseObject = authServices.signupApi(signUpData);
			Map<String, Object> signUpdataResponse = new HashMap<String, Object>();
			Map<String, Object> userResponseData = new HashMap<String, Object>();
			signUpdataResponse.put("data", userResponseObject);
			signUpdataResponse.put("status", "Success");
			userResponseData.put("data", userResponseData);
			return ResponseEntity.status(HttpStatus.OK).body(signUpdataResponse);
		}
	}

	@GetMapping("/test")
	public String testApi() {
		return "This is working";
	}
}
