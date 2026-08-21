package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.pojo.SignUpData;

@Service
public class AuthServices {
	public String SignupApi(SignUpData signUpData) {
		return signUpData.toString();
	}
}
