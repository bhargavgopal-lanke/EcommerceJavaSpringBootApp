package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.pojo.SignUpData;

@Service
public class AuthServices {
	@Autowired
	UserRepository userRepository;
	
	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User signupApi(SignUpData signUpData) {
		User user = new User();
		user.email = signUpData.getEmail();
		user.name = signUpData.getName();
		user.password = signUpData.getPassword();
//		user.mobile = signUpData.getMobile();
		userRepository.save(user);
		return user;
	}

	public User getEmailApi(SignUpData signUpData) throws Exception {
		Optional<User> userEmailResponse = userRepository.findByEmail(signUpData.getEmail());
		if (userEmailResponse.isEmpty()) {
			User user = new User();
			user.setName(signUpData.getName());
			user.setEmail(signUpData.getEmail());
			user.setPassword(passwordEncoder.encode(signUpData.getPassword()));
			user.setMobile(signUpData.getMobile());

			User dbuserData = userRepository.save(user);
			return dbuserData;
		} else {
			throw new Exception("User already exists. Please Login");
		}
	}
}
