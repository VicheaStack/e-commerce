package com.example.E_commerceApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.E_commerceApplication.Data.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

	Optional<Discount> findByIdAndName(long id, String name);
}
