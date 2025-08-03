package com.example.E_commerceApplication.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

	@Autowired
	private CacheInspectionService cacheInspectionService;

	@CachePut(value = "products", key = "#result.id")
	@Override
	public ProductDTO create(ProductDTO productDTO) {
		Product entity = ProductMapper.toEntity(productDTO);
		Product save = crudRepository.save(entity);
		return ProductMapper.toDTO(save);
	}

	@CachePut(value = "products", key = "#id") // pulls 'id' from method param
	@Override
	public ProductDTO update(Long id, ProductDTO update) {
		Optional<Product> updateProduct = crudRepository.findById(update.getId());
		if (updateProduct.isPresent()) {
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

	@Cacheable(value = "products", key = "'all'") // read all (with fix key)
	@Override
	public List<ProductDTO> findAll() {
		logger.info("Fetching all products from DB");
		return crudRepository.findAll().stream().map(ProductMapper::toDTO).collect(Collectors.toList());
	}

	@Cacheable(value = "products", key = "#id")
	@Override
	public Optional<ProductDTO> findbyID(Long id) {
		System.out.println("Fetching data from Product: " + id);
		return crudRepository.findById(id).map(ProductMapper::toDTO);
	}

	@CacheEvict(value = "products", key = "#id")
	@Override
	public void delete(Long id) {
		if (!crudRepository.existsById(id)) {
			logger.error("Product not found for deletion with ID: {}", id);
			throw new RuntimeException("Product not found for deletion " + id);
		}
		logger.info("Deleting product with ID: {}", id);
		crudRepository.deleteById(id);
	}

	@CacheEvict(value = "products", allEntries = true)
	public void cleanAllProductCache() {
		logger.warn("ALL product cache entries cleared.");
	}

}
