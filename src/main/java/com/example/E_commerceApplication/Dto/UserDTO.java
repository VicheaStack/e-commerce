package com.example.E_commerceApplication.Dto;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String username;
	private int quantity; // You might want to calculate this based on cartItems or wishlistItems
}
