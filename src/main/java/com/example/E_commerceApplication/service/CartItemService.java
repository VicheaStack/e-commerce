package com.example.E_commerceApplication.service;

import java.util.List;

import com.example.E_commerceApplication.Data.CartItem;

public interface CartItemService {
	CartItem addItemToCart(CartItem cartItem);

	List<CartItem> getUserCart(Long userId);

	void removeCartItem(Long itemId);

	void cleanUserCart(Long userId);
}