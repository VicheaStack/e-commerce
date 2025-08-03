package com.example.E_commerceApplication.Mapper;
import java.math.BigDecimal;

import com.example.E_commerceApplication.Data.Discount;
import com.example.E_commerceApplication.Data.Product;
import com.example.E_commerceApplication.Dto.ProductDiscountDTO;

public class ProductDiscountMapper {

    public static ProductDiscountDTO toDTO(Product product) {
        ProductDiscountDTO dto = new ProductDiscountDTO();

        dto.setProductId(product.getId());
        dto.setProductName(product.getProduct());
        dto.setOriginalPrice(product.getPrice());

        Discount discount = product.getDiscount();

        if (discount != null && discount.isActive()) {
            BigDecimal discountedPrice = calculateDiscount(product.getPrice(), discount.getPercentage());
            dto.setDiscountedPrice(discountedPrice);
            dto.setDiscountName(discount.getName());
            dto.setPercentage(discount.getPercentage());
            dto.setDiscountType(discount.getDiscountType());
            dto.setDiscountActive(true);
        } else {
            dto.setDiscountedPrice(product.getPrice()); // No discount applied
            dto.setDiscountActive(false);
        }

        return dto;
    }

	public static Discount toEntity(ProductDiscountDTO dto) {
		if (dto == null) {
			return null;
		}

		Discount discount = new Discount();

		discount.setName(dto.getDiscountName());
		discount.setPercentage(dto.getPercentage());
		discount.setDiscountType(dto.getDiscountType());
		discount.setActive(dto.isDiscountActive());

		// If you want to store the price at the discount level (optional)
		discount.setPrice(dto.getOriginalPrice());

		// Note: Discount entity doesn't have a productId field as a foreign key,
		// you must handle the association in your service/repository layer separately.

		return discount;
	}

    private static BigDecimal calculateDiscount(BigDecimal price, BigDecimal percentage) {
        if (price == null || percentage == null) {
            return price;
        }
        return price.subtract(price.multiply(percentage).divide(new BigDecimal("100")));
    }
}
