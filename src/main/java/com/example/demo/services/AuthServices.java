package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.pojo.SignUpData;

@Service
public class AuthServices {
	public Object signupApi(SignUpData signUpData) {
		System.out.println(signUpData);
		return signUpData;
	}
}
