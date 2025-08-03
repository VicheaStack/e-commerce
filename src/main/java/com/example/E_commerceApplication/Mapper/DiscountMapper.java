package com.example.E_commerceApplication.Mapper;

import com.example.E_commerceApplication.Data.Discount;
import com.example.E_commerceApplication.Dto.DiscountDTO;

public class DiscountMapper {

    public static DiscountDTO toDTO(Discount discount) {
        if (discount == null) {
            return null;
        }

        DiscountDTO dto = new DiscountDTO();
		dto.setProductId(discount.getId()); // assuming your Discount entity has this field
		dto.setDiscountName(discount.getName());
        dto.setPrice(discount.getPrice());
        dto.setDiscountType(discount.getDiscountType());
        dto.setPercentage(discount.getPercentage());
        dto.setStartDate(discount.getStartDate());
        dto.setEndDate(discount.getEndDate());
        dto.setActive(discount.isActive());
        // discountedPrice usually not stored, so calculate if needed elsewhere

        return dto;
    }

    public static Discount toEntity(DiscountDTO dto) {
        if (dto == null) {
            return null;
        }

        Discount discount = new Discount();
		discount.setId(dto.getProductId()); // make sure Discount entity has this or handle association properly
		discount.setName(dto.getDiscountName());
        discount.setPrice(dto.getPrice());
        discount.setDiscountType(dto.getDiscountType());
        discount.setPercentage(dto.getPercentage());
        discount.setStartDate(dto.getStartDate());
        discount.setEndDate(dto.getEndDate());
        discount.setActive(dto.isActive());

        return discount;
    }
}
