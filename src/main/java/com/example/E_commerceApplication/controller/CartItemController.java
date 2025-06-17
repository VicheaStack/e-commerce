package com.example.E_commerceApplication.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_commerceApplication.Data.CartItem;
import com.example.E_commerceApplication.service.CartItemService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cartItem")
public class CartItemController {
	
	private final CartItemService cartItemService;
	
	@PostMapping("/add")
	public CartItem add(@RequestBody CartItem cartItem) {
		return cartItemService.addItemToCart(cartItem);
	}

	@GetMapping
	public List<CartItem> getUserCart(@PathVariable long id) {
		return cartItemService.getUserCart(id);
	}

	@DeleteMapping("/delete/{itemId}")
	public void deleteItem(@PathVariable Long itemid) {
		cartItemService.removeCartItem(itemid);
	}
}
