package com.example.E_commerceApplication.Dto;
import java.math.BigDecimal;

import com.example.E_commerceApplication.Data.DiscountType;

import lombok.Data;

@Data
public class ProductDiscountDTO {
    private Long productId;
    private String productName;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private String discountName;
    private BigDecimal percentage;
    private DiscountType discountType;
    private boolean discountActive;
}
