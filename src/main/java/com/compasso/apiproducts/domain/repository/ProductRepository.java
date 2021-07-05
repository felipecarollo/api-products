package com.compasso.apiproducts.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compasso.apiproducts.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	
	
}
