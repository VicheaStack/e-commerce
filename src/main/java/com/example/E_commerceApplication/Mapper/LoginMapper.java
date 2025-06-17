package com.example.E_commerceApplication.Mapper;

import com.example.E_commerceApplication.Data.Register;
import com.example.E_commerceApplication.Dto.RegisterDTO;

public class LoginMapper {

	public static RegisterDTO toDTO(Register register) {
		if (register == null) {
			return null;
		}

		RegisterDTO dto = new RegisterDTO();
		dto.setId(register.getId());
		dto.setFullName(register.getFullName());
		dto.setEmail(register.getEmail());
		dto.setPassword(register.getPassword());
		dto.setRole(register.getRole());
		return dto;
	}

	public static Register toEntity(RegisterDTO entity) {
		if (entity == null) {
			return null;
		}

		Register register = new Register();
		register.setId(entity.getId());
		register.setFullName(entity.getFullName());
		register.setEmail(entity.getEmail());
		register.setPassword(entity.getPassword());
		register.setRole(entity.getRole());

		return register;

	}
}
