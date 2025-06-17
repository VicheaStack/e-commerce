package com.example.E_commerceApplication.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_commerceApplication.Data.WishListItem;
import com.example.E_commerceApplication.repository.ProductRepository;
import com.example.E_commerceApplication.repository.WishListItemRepository;
import com.example.E_commerceApplication.service.WishListItemService;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class WishListItemImpl implements WishListItemService {

	private WishListItemRepository itemRepository;
	private ProductRepository productRepository;

	@Autowired
	public WishListItemImpl(WishListItemRepository itemRepository, ProductRepository productRepository) {
		super();
		this.itemRepository = itemRepository;
		this.productRepository = productRepository;
	}

	@Override
	public WishListItem addWishListItem(WishListItem wishListItem) {
		wishListItem.setRemoved(true);
		return itemRepository.save(wishListItem);
	}

	@Override
	public void removeWishListItem(Long itemId) {
		WishListItem removeOptional = itemRepository.findById(itemId)
				.orElseThrow(() -> new RuntimeException("WishList item not found"));

		removeOptional.setRemoved(true);
		itemRepository.save(removeOptional);
	}

	@Override
	public List<WishListItem> findByAll() {
		return itemRepository.findAll().stream().filter(item -> !Boolean.TRUE.equals(item.getRemoved()))
				.collect(Collectors.toList());
	}

}
