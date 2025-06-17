package com.example.E_commerceApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_commerceApplication.Data.User;

//new feature
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
