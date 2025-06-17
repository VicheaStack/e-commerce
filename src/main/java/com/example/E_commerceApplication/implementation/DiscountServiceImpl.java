package com.example.E_commerceApplication.implementation;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.E_commerceApplication.Dto.DiscountDTO;
import com.example.E_commerceApplication.repository.DiscountRepository;
import com.example.E_commerceApplication.service.DiscountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiscountServiceImpl implements DiscountService {

	private final DiscountRepository discountRepository;

	public DiscountDTO calculate(DiscountDTO discountDTO) {
		BigDecimal price = discountDTO.getPrice();
		String discountType = discountDTO.getDiscountType();
		
		BigDecimal finalPrice;
		
		switch (percentage.toString()) {
		case "PERCENTAGE":
			finalPrice = price.subtract(price.multiply(finalPrice))
		}
	}

	@Override
	public DiscountDTO startSale(DiscountDTO discountDTO) {
		return null;
	}

	@Override
	public DiscountDTO productDiscount(long id, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiscountDTO endSale(DiscountDTO discountDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
