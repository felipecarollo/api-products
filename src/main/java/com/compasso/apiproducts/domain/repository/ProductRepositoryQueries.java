package com.compasso.apiproducts.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.compasso.apiproducts.domain.model.Product;

public interface ProductRepositoryQueries {

	List<Product> findByParameters(String q, BigDecimal minPrice, BigDecimal maxPrice);
	
}
