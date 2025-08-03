package com.example.E_commerceApplication.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_commerceApplication.Dto.ProductDTO;
import com.example.E_commerceApplication.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3030")
@RequestMapping("/product/request")
public class ProductController {

	private final ProductService productService;

	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@Valid @RequestBody ProductDTO productDTO) {
		ProductDTO createdProduct = productService.create(productDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Product created successfully: " + createdProduct.getProduct());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
		ProductDTO updatedProduct = productService.update(id, productDTO);
		return ResponseEntity.ok("Product updated successfully: " + updatedProduct.getProduct());
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<ProductDTO> productOpt = productService.findbyID(id);
		return productOpt.<ResponseEntity<?>>map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id " + id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAll")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(productService.findAll());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.ok("Product deleted successfully.");
	}
}
