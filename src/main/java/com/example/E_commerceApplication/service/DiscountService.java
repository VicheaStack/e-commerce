package com.example.E_commerceApplication.service;

import com.example.E_commerceApplication.Dto.DiscountDTO;

public interface DiscountService {
	DiscountDTO startSale(DiscountDTO discountDTO);

	DiscountDTO productDiscount(long id, String name);

	DiscountDTO endSale(DiscountDTO discountDTO);
}
