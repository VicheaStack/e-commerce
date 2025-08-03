package com.example.E_commerceApplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_commerceApplication.Dto.DiscountDTO;
import com.example.E_commerceApplication.Dto.ProductDiscountDTO;
import com.example.E_commerceApplication.service.DiscountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/discount")
public class AdminDiscountSetUp {

	private final DiscountService discountService;

	@PostMapping("/calculateSale")
	public ResponseEntity<ProductDiscountDTO> calculate(@Valid @RequestBody ProductDiscountDTO discountDTO) {
		ProductDiscountDTO result = discountService.calculate(discountDTO);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/start")
	public ResponseEntity<ProductDiscountDTO> start(@Valid @RequestBody ProductDiscountDTO discountDTO) {
		ProductDiscountDTO startSale = discountService.startSale(discountDTO);
		return ResponseEntity.ok(startSale);
	}

	@PutMapping("/endSale")
	public ResponseEntity<DiscountDTO> endSale(@Valid @RequestBody DiscountDTO discountDTO) {
		DiscountDTO endSale = discountService.endSale(discountDTO);
		return ResponseEntity.ok(endSale);
	}

	@PostMapping("/setupDiscount/{id}/{name}")
	public ResponseEntity<DiscountDTO> productDiscount(@PathVariable Long id, String name) {
		DiscountDTO productDiscount = discountService.getDiscountByProductId(id, name);
		return ResponseEntity.ok(productDiscount);
	}
}
