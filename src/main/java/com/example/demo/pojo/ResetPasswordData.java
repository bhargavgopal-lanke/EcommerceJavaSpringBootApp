package com.example.demo.pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordData {
	@NotNull(message = "Password reset key is required")
	private String linkId;
	@NotNull(message = "password required")
	@Size(min = 8, message = "password should be min 8 characters")
	private String password;
	@NotNull(message = "confirm password required")
	@Size(min = 8, message = "confirm password should be min 8 characters")
	private String confirmPassword;
}
