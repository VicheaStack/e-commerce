package com.example.E_commerceApplication.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_commerceApplication.Data.Product;
import com.example.E_commerceApplication.Dto.ProductDTO;
import com.example.E_commerceApplication.Mapper.ProductMapper;
import com.example.E_commerceApplication.repository.ProductRepository;
import com.example.E_commerceApplication.service.ProductService;

@Service
public class ProductImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductImpl.class);

	@Autowired
	private ProductRepository crudRepository;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public ProductDTO create(ProductDTO productDTO) {
		Product entity = ProductMapper.toEntity(productDTO);
		Product save = crudRepository.save(entity);
		return ProductMapper.toDTO(save);
	}


	@Override
	public List<ProductDTO> findAll() {
		List<Product> products = crudRepository.findAll();
		return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
	}


	@Override
	public Optional<ProductDTO> findbyID(Long id) {
		Optional<Product> productOpt = crudRepository.findById(id);
		return productOpt.map(ProductMapper::toDTO);
	}

	@Override
	public ProductDTO update(ProductDTO update) {
		Optional<Product> updateProduct = crudRepository.findById(update.getId());
		if (updateProduct.isEmpty()) {
			Product product = updateProduct.get();

			product.setCategory(update.getCategory());
			product.setProduct(update.getProduct());
			product.setPrice(update.getPrice());
			product.setQuantity(update.getQuantity());
			product.setRate(update.getRate());
			Product save = crudRepository.save(product);
			return ProductMapper.toDTO(save);
		} else {
			logger.error("Product not found for update with ID: {}", update.getId());
			throw new RuntimeException("Product not found for update");
		}
	}

	@Override
	public void delete(Long id) {
		if (!crudRepository.existsById(id)) {
			logger.error("Product not found for deletion with ID: {}", id);
			throw new RuntimeException("Product not found for deletion " + id);
		}
		logger.info("Deleting product with ID: {}", id);
		crudRepository.deleteById(id);
	}

}
