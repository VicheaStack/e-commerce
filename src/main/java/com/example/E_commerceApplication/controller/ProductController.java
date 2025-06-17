package com.example.E_commerceApplication.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; // ✅ Correct import
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_commerceApplication.Dto.ProductDTO;
import com.example.E_commerceApplication.service.ProductService;

import jakarta.validation.Valid; // ✅ Optional if using validation
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product/request")
public class ProductController {

	private final ProductService productService;

	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@Valid @RequestBody ProductDTO productDTO) {
		ProductDTO product = productService.create(productDTO);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}

	@PatchMapping("/update")
	public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody ProductDTO productDTO) {
		ProductDTO update = productService.update(productDTO);
		return ResponseEntity.ok("Product created successfully.");
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<ProductDTO> findbyID = productService.findbyID(id);
		return ResponseEntity.ok("find by id");
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> findAll() {
		productService.findAll();
		return ResponseEntity.ok("Product find successfully.");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.ok("Product delete successfully.");
	}

}