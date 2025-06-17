package com.example.E_commerceApplication.Mapper;

import com.example.E_commerceApplication.Data.CartItem;
import com.example.E_commerceApplication.Data.User;
import com.example.E_commerceApplication.Dto.CartItemDTO;

public class CartItemMapper {

	public static CartItemDTO toDTO(CartItem cartItem) {
		if (cartItem == null) {
			return null;
		}

		CartItemDTO dto = new CartItemDTO();
		dto.setId(cartItem.getId());
		dto.setQuantity(cartItem.getQuantity());
		dto.setProductName(cartItem.getProductName());
		dto.setUserId(cartItem.getUser() != null ? cartItem.getUser().getId() : null); // Assuming User has a `getId()`
																						// method

		return dto;
	}

	public static CartItem toEntity(CartItemDTO dto) {
		if (dto == null) {
			return null;
		}

		CartItem cartItem = new CartItem();
		cartItem.setId(dto.getId());
		cartItem.setQuantity(dto.getQuantity());
		cartItem.setProductName(dto.getProductName());

		if (dto.getUserId() != null) {
			User user = new User();
			user.setId(dto.getUserId());
			cartItem.setUser(user);
		}

		return cartItem;
	}
}
