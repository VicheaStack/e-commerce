package com.example.E_commerceApplication.Dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDTO {

	private Long id;
	private String category;
	private String product;
	private BigDecimal price; // Use BigDecimal
	private Integer quantity;
	private Integer rate;
}
