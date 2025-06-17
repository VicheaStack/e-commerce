package com.example.E_commerceApplication.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DiscountDTO {

	private Long id;

	private String name;

	private BigDecimal price;

	private DiscountDTO discountType; // Use consistent camelCase

	private BigDecimal percentage;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private boolean active;
}
