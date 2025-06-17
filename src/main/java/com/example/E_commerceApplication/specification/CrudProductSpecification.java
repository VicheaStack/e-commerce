package com.example.E_commerceApplication.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.E_commerceApplication.Data.Product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CrudProductSpecification implements Specification<Product> {

	private String productName;

	public CrudProductSpecification(String productName) {
		this.productName = productName;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (productName == null && !productName.isEmpty()) {
			return criteriaBuilder.like(root.get("firstname"), "&" + productName + "&");
		}
		return criteriaBuilder.conjunction();
	}

}
