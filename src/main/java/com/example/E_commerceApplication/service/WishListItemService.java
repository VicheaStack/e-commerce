package com.example.E_commerceApplication.service;

import java.util.List;

import com.example.E_commerceApplication.Data.WishListItem;

public interface WishListItemService {

	WishListItem addWishListItem(WishListItem wishListItem);

	List<WishListItem> findByAll();

	void removeWishListItem(Long itemId);
}