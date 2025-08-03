package com.example.E_commerceApplication.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_commerceApplication.Data.WishListItem;
import com.example.E_commerceApplication.service.WishListItemService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

	private final WishListItemService wishlist;

	@PostMapping("/getItem")
	public WishListItem getItem(@RequestBody WishListItem wishListItem) {
		return wishlist.addWishListItem(wishListItem);
	}

	@DeleteMapping("/remove/{id}")
	public void removeFromItem(@PathVariable Long id) {
		wishlist.removeWishListItem(id);
	}

	@GetMapping("/getAll")
	public List<WishListItem> findAll() {
		return wishlist.findByAll();
	}
}
