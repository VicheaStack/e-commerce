package com.example.E_commerceApplication.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.E_commerceApplication.Data.DiscountType;

import lombok.Data;

@Data
public class DiscountDTO {
	private Long productId;
	private String discountName;
	private BigDecimal price;
	private BigDecimal percentage;
	private DiscountType discountType;
	private boolean isActive;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

}
