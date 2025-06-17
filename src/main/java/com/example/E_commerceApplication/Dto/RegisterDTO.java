package com.example.E_commerceApplication.Dto;

import lombok.Data;

@Data
public class RegisterDTO {

	private Long id;

	private String fullName;
	private String email;

	private String password;

	private String role;
}
