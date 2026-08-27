package com.example.demo.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpData {
	@NotBlank(message = "name should not be empty")
	@NotNull(message = "name should not be null")
	private String name;
	@NotBlank(message = "password should not be empty")
	@NotNull(message = "password should not be null")
	private String password;
	@NotBlank(message = "mobile should not be empty")
	@NotNull(message = "mobile should not be null")
	@Size(max = 10, message = "mobile should be max 10")
	private String mobile;
	@NotBlank(message = "email should not be empty")
	@NotNull(message = "email should not be null")
	private String email;
}
