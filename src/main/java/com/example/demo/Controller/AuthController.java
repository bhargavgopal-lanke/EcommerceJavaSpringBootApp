package com.example.demo.Controller;

import java.lang.reflect.Method;
import java.time.chrono.IsoChronology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.SignUpData;
import com.example.demo.services.AuthServices;

import jakarta.validation.Valid;

@RestController
public class AuthController {

	@Autowired
	AuthServices authServices;

	@PostMapping("test-post-api")
	public String postApiTesting(@RequestBody SignUpData signUpData) {
		System.out.println("test" + " " + " tooo" + signUpData);
		return signUpData.toString();
	}

	@GetMapping("/test")
	public String testApi() {
		return "This is working";
	}
}
