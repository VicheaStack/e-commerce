package com.example.E_commerceApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.E_commerceApplication.Data.WishListItem;

@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {
	List<WishListItem> findByUserIdAndRemovedFalse(Long userId);
}
