package com.example.E_commerceApplication.Dto;

import java.util.Set;

import lombok.Data;

@Data
public class UserDTO {
	private String username;
	private String password;
	private Set<String> authorities;
}
