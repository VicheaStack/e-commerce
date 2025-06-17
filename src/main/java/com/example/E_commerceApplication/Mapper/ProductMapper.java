package com.example.E_commerceApplication.Mapper;

import org.springframework.stereotype.Service;

import com.example.E_commerceApplication.Data.Product;
import com.example.E_commerceApplication.Dto.ProductDTO;

@Service
public class ProductMapper {

	public static ProductDTO toDTO(Product product) {
		if (product == null)
			return null;

		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setCategory(product.getCategory());
		dto.setProduct(product.getProduct());
		dto.setPrice(product.getPrice());
		dto.setQuantity(product.getQuantity());
		dto.setRate(product.getRate());

		return dto;
	}

	public static Product toEntity(ProductDTO dto) {
		if (dto == null)
			return null;

		Product product = new Product();
		product.setId(dto.getId());
		product.setCategory(dto.getCategory());
		product.setProduct(dto.getProduct());
		product.setPrice(dto.getPrice());
		product.setQuantity(dto.getQuantity());
		product.setRate(dto.getRate());

		return product;
	}
}
