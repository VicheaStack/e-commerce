package com.example.E_commerceApplication.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.E_commerceApplication.Data.CartItem;
import com.example.E_commerceApplication.repository.CartItemRepository;
import com.example.E_commerceApplication.service.CartItemService;

@Service
public class CartItemImpl implements CartItemService {

	private CartItemRepository cartItemRepository;

	@Override
	public CartItem addItemToCart(CartItem cartItem) {
		cartItem.setRemoved(false);
		return cartItemRepository.save(cartItem);
	}

	@Override
	public List<CartItem> getUserCart(Long userId) {
		return cartItemRepository.findAll();
	}

	@Override
	public void removeCartItem(Long itemId) {
		Optional<CartItem> productRemove = cartItemRepository.findById(itemId);
		productRemove.ifPresent(cart -> {
			cart.setRemoved(true);
			cartItemRepository.save(cart);
		});
	}

	@Override
	public void cleanUserCart(Long userId) {
		List<CartItem> items = cartItemRepository.findByUserIdAndRemovedFalse(userId);
		for (CartItem item : items) {
			item.setRemoved(true);
		}
		cartItemRepository.saveAll(items);
	}
}
