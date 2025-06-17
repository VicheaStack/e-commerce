package com.example.E_commerceApplication.Mapper;

import com.example.E_commerceApplication.Data.User;
import com.example.E_commerceApplication.Dto.UserDTO;

public class UserMapper {

	// Convert from Entity to DTO
	public static UserDTO toDTO(User user) {
		if (user == null) {
			return null;
		}

		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setQuantity(user.getQuantity()); // Calls getQuantity() method

		return dto;
	}

	// Convert from DTO to Entity
	public static User toEntity(UserDTO dto) {
		if (dto == null) {
			return null;
		}

		User user = new User();
		user.setId(dto.getId());
		user.setUsername(dto.getUsername());

		// Do NOT set quantity directly since it's derived from relationships
		// CartItems and WishListItems must be set separately if needed

		return user;
	}
}
