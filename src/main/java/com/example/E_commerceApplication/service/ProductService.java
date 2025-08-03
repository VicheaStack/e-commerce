package com.example.E_commerceApplication.service;

import java.util.List;
import java.util.Optional;

import com.example.E_commerceApplication.Dto.ProductDTO;

public interface ProductService {
	ProductDTO create(ProductDTO productDTO);

	List<ProductDTO> findAll();

	Optional<ProductDTO> findbyID(Long id);

	ProductDTO update(Long id, ProductDTO update);

	void delete(Long id);

}
