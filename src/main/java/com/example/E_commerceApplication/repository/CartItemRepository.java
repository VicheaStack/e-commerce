package com.example.E_commerceApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.E_commerceApplication.Data.CartItem;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByProductName(String productName);

	List<CartItem> findbyUserId(Long id);

	List<CartItem> findByUserIdAndRemovedFalse(Long userId);

}