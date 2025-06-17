package com.example.E_commerceApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.E_commerceApplication.Data.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
	Optional<Register> findByEmail(String email);

	boolean existsByEmail(String email);
}
