package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.pojo.SignUpData;

@Service
public class AuthServices {
	@Autowired
	UserRepository userRepository;

	public User signupApi(SignUpData signUpData) {
		User user = new User();
		user.email = signUpData.getEmail();
		user.name = signUpData.getName();
		user.password = signUpData.getPassword();
//		user.mobile = signUpData.getMobile();
		userRepository.save(user);
		return user;
	}

	public Boolean getEmailApi(SignUpData signUpData) {
		Optional<User> userEmailResponse = userRepository.findByEmail(signUpData.getEmail());
		if (userEmailResponse.isPresent()) {
			return true;
		} else {
			return false;
		}

	}

}
