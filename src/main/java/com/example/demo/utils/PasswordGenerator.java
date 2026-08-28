package com.example.demo.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {
	private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final int PASSWORD_LENGTH = 10;
	
	public String generateRandomPassword() {
		SecureRandom random = new SecureRandom();
		StringBuilder sBuilder = new StringBuilder(PASSWORD_LENGTH);
		for(int i = 0; i < PASSWORD_LENGTH; i++) {
			int index = random.nextInt(CHARS.length());
			sBuilder.append(CHARS.charAt(index));
		}
		return sBuilder.toString();
	}
}
