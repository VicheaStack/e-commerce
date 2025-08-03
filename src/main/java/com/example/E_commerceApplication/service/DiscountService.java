package com.example.E_commerceApplication.service;

import com.example.E_commerceApplication.Dto.DiscountDTO;
import com.example.E_commerceApplication.Dto.ProductDiscountDTO;

public interface DiscountService {

	ProductDiscountDTO calculate(ProductDiscountDTO discountDTO);

	ProductDiscountDTO startSale(ProductDiscountDTO discountDTO);

	DiscountDTO getDiscountByProductId(long id, String name);

	DiscountDTO endSale(DiscountDTO discountDTO);
}
