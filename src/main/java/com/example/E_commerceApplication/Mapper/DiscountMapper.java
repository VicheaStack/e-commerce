package com.example.E_commerceApplication.Mapper;

import com.example.E_commerceApplication.Data.Discount;
import com.example.E_commerceApplication.Data.DiscountType;
import com.example.E_commerceApplication.Dto.DiscountDTO;

public class DiscountMapper {

    public static DiscountDTO toDTO(Discount discount) {
		if (discount == null)
			return null;

        DiscountDTO dto = new DiscountDTO();
        dto.setId(discount.getId());
        dto.setName(discount.getName());
		dto.setPrice(discount.getPrice());
		dto.setDiscountType(DiscountType.PERCENTAGE);
        dto.setPercentage(discount.getPercentage());
        dto.setStartDate(discount.getStartDate());
        dto.setEndDate(discount.getEndDate());
        dto.setActive(discount.isActive());
        return dto;
    }

    public static Discount toEntity(DiscountDTO dto) {
		if (dto == null)
			return null;

        Discount discount = new Discount();
        discount.setId(dto.getId());
        discount.setName(dto.getName());
		discount.setPrice(dto.getPrice());
		discount.setDiscountType(DiscountType.PERCENTAGE);
        discount.setPercentage(dto.getPercentage());
        discount.setStartDate(dto.getStartDate());
        discount.setEndDate(dto.getEndDate());
        discount.setActive(dto.isActive());
        return discount;
    }
}
