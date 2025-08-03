package com.example.E_commerceApplication.Mapper;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.E_commerceApplication.Data.UserEntity;
import com.example.E_commerceApplication.Dto.UserDTO;

public class UserMapper {

	public static UserDTO toDTO(UserEntity users) {
		if (users == null)
			return null;

		UserDTO user = new UserDTO();
		user.setUsername(users.getUsername());
		user.setPassword(users.getPassword());
		user.setAuthorities(users.getAuthorities());

		return user;
	}

	public static UserEntity toEntity(UserDTO userDTO, PasswordEncoder encoder) {
		if (userDTO == null)
			return null;

		UserEntity entity = new UserEntity();
		entity.setUsername(userDTO.getUsername());
		entity.setPassword(encoder.encode(userDTO.getPassword()));
		entity.setAuthorities(userDTO.getAuthorities());

		return entity;
	}

}
