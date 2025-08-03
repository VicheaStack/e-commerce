package com.example.E_commerceApplication.implementation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.E_commerceApplication.Data.Discount;
import com.example.E_commerceApplication.Data.DiscountType;
import com.example.E_commerceApplication.Data.Product;
import com.example.E_commerceApplication.Dto.DiscountDTO;
import com.example.E_commerceApplication.Dto.ProductDiscountDTO;
import com.example.E_commerceApplication.Mapper.DiscountMapper;
import com.example.E_commerceApplication.Mapper.ProductDiscountMapper;
import com.example.E_commerceApplication.repository.DiscountRepository;
import com.example.E_commerceApplication.repository.ProductRepository;
import com.example.E_commerceApplication.service.DiscountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiscountServiceImpl implements DiscountService {

	private final DiscountRepository discountRepository;
	private final ProductRepository productRepository;

	@Override
	public ProductDiscountDTO calculate(ProductDiscountDTO discountDTO) {
		BigDecimal price = discountDTO.getOriginalPrice();
		BigDecimal discountAmount = BigDecimal.ZERO;

		if (discountDTO.getDiscountType() == DiscountType.PERCENTAGE) {
			discountAmount = price.multiply(discountDTO.getPercentage().divide(BigDecimal.valueOf(100)));
		} else if (discountDTO.getDiscountType() == DiscountType.FLAT) {
			discountAmount = discountDTO.getPercentage();
		}

		BigDecimal discountedPrice = price.subtract(discountAmount);
		discountDTO.setDiscountedPrice(discountedPrice.max(BigDecimal.ZERO));
		return discountDTO;
	}

	@Override
	public ProductDiscountDTO startSale(ProductDiscountDTO discountDTO) {
		Discount discount = ProductDiscountMapper.toEntity(discountDTO);
		discount.setActive(true);
		discount.setStartDate(LocalDateTime.now());

		Discount savedDiscount = discountRepository.save(discount);

		Product product = productRepository.findById(discountDTO.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found"));
		product.setDiscount(savedDiscount);
		productRepository.save(product);
		return ProductDiscountMapper.toDTO(product);
	}


	@Override
	public DiscountDTO endSale(DiscountDTO discountDTO) {
		Discount discount = discountRepository.findById(discountDTO.getProductId())
				.orElseThrow(() -> new RuntimeException("Discount not found"));

		discount.setActive(false);
		discount.setEndDate(LocalDateTime.now());
		Discount saved = discountRepository.save(discount);
		return DiscountMapper.toDTO(saved);
	}

	@Override
	public DiscountDTO getDiscountByProductId(long id, String name) {
		Discount discount = discountRepository.findByIdAndName(id, name)
				.orElseThrow(() -> new RuntimeException("no discount found for product ID and name"));

		return DiscountMapper.toDTO(discount);
	}

}
