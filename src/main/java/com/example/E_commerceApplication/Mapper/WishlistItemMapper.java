package com.example.E_commerceApplication.Mapper;

import org.springframework.stereotype.Component;

import com.example.E_commerceApplication.Data.WishListItem;
import com.example.E_commerceApplication.Dto.WishlistItemDTO;

@Component
public class WishlistItemMapper {

	public static WishlistItemDTO toDTO(WishListItem wishListItem) {
		if (wishListItem == null) {
			return null;
		}

		WishlistItemDTO dto = new WishlistItemDTO();
		dto.setId(wishListItem.getId());
		dto.setUserId(wishListItem.getUser() != null ? wishListItem.getUser().getId() : null);
		dto.setProductId(wishListItem.getProduct() != null ? wishListItem.getProduct().getId() : null);

		return dto;
	}

	public static WishListItem toEntity(WishlistItemDTO dto) {
		if (dto == null) {
			return null;
		}

		WishListItem entity = new WishListItem();
		entity.setId(dto.getId());
		// Note: You should set User and Product externally in the service layer since
		// only userId and productId are passed

		return entity;
	}
}
