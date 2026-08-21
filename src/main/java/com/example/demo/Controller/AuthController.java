package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.SignUpData;
import com.example.demo.services.AuthServices;

import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	@Autowired AuthServices authServices;
	
	public String SignupApi(@Valid @RequestBody SignUpData signUpData) {
		String signUpResponse = authServices.SignupApi(signUpData);
		return signUpResponse;
	}
}
