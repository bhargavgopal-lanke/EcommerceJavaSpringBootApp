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
	public ResponseEntity<Map<String, Object>> getEmailApi(@Valid @RequestBody SignUpData signUpData) throws Exception {
		Object emailDataResponse = authServices.getEmailApi(signUpData);
		Map<String, Object> emailresponseDataMap = new HashMap<String, Object>();
		emailresponseDataMap.put("result", "Success");
		emailresponseDataMap.put("data", emailDataResponse);
		return ResponseEntity.status(HttpStatus.OK).body(emailresponseDataMap);
	}

	@GetMapping("/test")
	public String testApi() {
		return "This is working";
	}
}
