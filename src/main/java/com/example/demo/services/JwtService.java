package com.example.demo.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	// secret key
	// add dependencies
	// decide what to store in the token
	// decide expiry duration -24 hours
	// generate encrypted key using secret key
	// generate jwt token

	@Value("${jwt.secret.key}")
	private String jwtSecretKey;

	private int JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;

	private Key generateSecurityKey() {
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
	}

	public String generateJwtToken(User userData) {
		Date tokenGeneratedTime = new Date();
		Date expiryDate = new Date(tokenGeneratedTime.getTime() + JWT_TOKEN_VALIDITY);

		Map<String, Object> tokenData = new HashMap<String, Object>();
		tokenData.put("id", userData.getId());
		tokenData.put("name", userData.getName());
		tokenData.put("email", userData.getEmail());
		String jwtToken = Jwts.builder().claims().add(tokenData).and().subject(userData.getEmail())
				.issuedAt(tokenGeneratedTime).expiration(expiryDate).signWith(generateSecurityKey()).compact();

		return jwtToken;

	}

}
