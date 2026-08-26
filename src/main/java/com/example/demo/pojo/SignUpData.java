package com.example.demo.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpData {
	@NotBlank(message = "name should not be empty")
	private String name;
	@NotBlank(message = "password should not be empty")
	private String password;
	@NotBlank(message = "mobile should not be empty")
	private String mobile;
	@NotBlank(message = "email should not be empty")
	private String email;
}
