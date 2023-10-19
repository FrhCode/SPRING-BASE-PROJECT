package com.farhan.baseproject.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	@NotBlank(message = "Password can not be empty")

	private String username;

	@NotBlank(message = "Password can not be empty")
	private String email;

	@NotBlank(message = "Password can not be empty")
	private String password;
	// private Role role;
}
