package com.example.E_commerceApplication.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.E_commerceApplication.implementation.CacheInspectionService;
import com.example.E_commerceApplication.service.ProductService;

@SpringBootTest
public class ProductServiceCacheTest {

	@Autowired
	private ProductService productService;

	@Autowired
	private CacheInspectionService cacheInspectionService;

	@Test
	public void testProduct() {
		Long id = 1l;

		System.out.println("First call: Should hit DB");
		productService.findbyID(id);

		System.out.println("Second call: Should hit cache (no DB)");
		productService.findbyID(id);

		System.out.println(" Inspecting cache");
		cacheInspectionService.printCacheContents("Products");
	}
}
