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
import com.example.demo.pojo.LoginData;
import com.example.demo.pojo.SignUpData;
import com.example.demo.services.AuthServices;
import com.example.demo.services.EmailService;

import jakarta.validation.Valid;

@RestController
public class AuthController {

	@Autowired
	AuthServices authServices;
	@Autowired
	EmailService emailService;

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

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> handleLogin(@RequestBody LoginData loginData) throws Exception {
		Object loginresObject = authServices.handleLogin(loginData);
		Map<String, Object> loginDatResMap = new HashMap<String, Object>();
		loginDatResMap.put("Result", "Success");
		loginDatResMap.put("data", loginresObject);
		return ResponseEntity.status(HttpStatus.OK).body(loginDatResMap);
	}

	@GetMapping("/send-email")
	public ResponseEntity<Map<String, String>> mailSenderApi() throws Exception {
		String fromEmail = "lanketony@gmail.com";
		String toEmail = "naidujyothi083@gmail.com";
		String subject = "This is my first email";
		String mailBody = "Please read this messaage this is from my sprintboot app";
		emailService.sendEmail(fromEmail, toEmail, subject, mailBody);
		mailBody = "Hi bhargav <br/>" + "This is a test email <br/>" + "From <br/>" + "Bhargav";
		emailService.sendHtmlEmail(fromEmail, toEmail, subject, mailBody);
		Map<String, String> mailResponseObjMap = new HashMap<String, String>();
		mailResponseObjMap.put("Result", "Success");
		mailResponseObjMap.put("Message", "Email sent");
		return ResponseEntity.status(HttpStatus.OK).body(mailResponseObjMap);
	}

//	@PostMapping("/login")
//	public ResponseEntity<Map<String, Object>> getLogin(@RequestBody LoginData loginData) throws Exception {
//		User loginResultUser = authServices.getLogin(loginData);
//		Map<String, Object> responseObjMap = new HashMap<String, Object>();
//		responseObjMap.put("Result", "Success");
//		responseObjMap.put("data", loginResultUser);
//		return ResponseEntity.status(HttpStatus.OK).body(responseObjMap);
//	}

	@GetMapping("/test")
	public String testApi() {
		return "This is working";
	}
}
