package com.example.E_commerceApplication.Dto;

import lombok.Data;

@Data
public class CartItemDTO {

	private Long id;

	private int quantity;

	private String productName;

	private Long userId;
}
