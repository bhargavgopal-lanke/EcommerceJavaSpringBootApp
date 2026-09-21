package com.example.demo.services;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.pojo.ForgotPasswordApiData;
import com.example.demo.pojo.LoginData;
import com.example.demo.pojo.SignUpData;
import com.example.demo.utils.PasswordGenerator;

@Service
public class AuthServices {
	private final EmailService emailService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordGenerator passwordGenerator;

	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	AuthServices(EmailService emailService) {
		this.emailService = emailService;
	}

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

	public void handleForgotPasswordApi(ForgotPasswordApiData forgotPasswordApiData) throws Exception {
		Optional<User> dbData = userRepository.findByEmail(forgotPasswordApiData.getEmail());
		if (dbData.isEmpty()) {
			throw new Exception("Yes user is not found. Please sign up");
		} else {
			String passwordResetKey = UUID.randomUUID().toString();
			User userData = dbData.get();
			userData.setPasswordResetKey(passwordResetKey);
			String fromEmail = "lanketony@gmail.com";
			String toEmail = "naidujyothi083@gmail.com";
			String subject = "This is my first email";
			String mailBody = "Hi " + userData.getName() + ", "
					+ "please find the below link to reset your password. <br/> " + "password reset link: "
					+ "<a href='http://localhost:8080/password-reset-ui?linkid=" + passwordResetKey
					+ "'>click here</a> <br/>" + "<b>Regards <br/>Ecommerse App</b>";
			userRepository.save(userData);
			emailService.sendForgotEmail(fromEmail, toEmail, subject, mailBody);
		}
	}

	/*
	 * public User forgotPasswordApi(ForgotPasswordApiData forgotPasswordApiData)
	 * throws Exception { Optional<User> userEMailresponse =
	 * userRepository.findByEmail(forgotPasswordApiData.getEmail()); if
	 * (userEMailresponse.isEmpty()) { throw new
	 * Exception("user is not registered with us"); } else { User emailUser =
	 * userEMailresponse.get(); String newPassword =
	 * passwordGenerator.generateRandomPassword();
	 * emailUser.setPassword(passwordEncoder.encode(newPassword));
	 * userRepository.save(emailUser);
	 * 
	 * return emailUser; } }
	 */

	public Object handleLogin(LoginData loginData) throws Exception {
		Optional<User> emailDataUser = userRepository.findByEmail(loginData.getEmail());
		if (emailDataUser.isEmpty()) {
			throw new Exception("Email is not registered with us. Please sign up");
		} else {
			User dbDataUser = emailDataUser.get();
			if (passwordEncoder.matches(loginData.getPassword(), dbDataUser.password)) {
				return dbDataUser;
			} else {
				throw new Exception("password is not matching please try again");
			}
		}
	}

//	public User getLogin(LoginData loginData) throws Exception {
//		Optional<User> dbDataUser = userRepository.findByEmail(loginData.getEmail());
//		User userData = dbDataUser.get();
//		Boolean emailString = userData.email.equals(loginData.getEmail());
//		Boolean passString = passwordEncoder.matches(loginData.getPassword(), userData.password);
//		if (passString && emailString) {
//			return userData;
//		} else {
//			throw new Exception("User doesnt have this data");
//		}
//	}

}
