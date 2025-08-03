package com.example.E_commerceApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.E_commerceApplication.Data.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	// Find by product name
	List<CartItem> findByProductName(String productName);

	// ✅ Corrected: Find by user's ID (through the user relationship)
	List<CartItem> findByUser_Id(Long id);

	// ✅ Corrected: Find by user's ID and only non-removed items
	List<CartItem> findByUser_IdAndRemovedFalse(Long id);
}
