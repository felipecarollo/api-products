package com.compasso.apiproducts.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.apiproducts.api.exceptionhandler.EntityNotFoundException;
import com.compasso.apiproducts.domain.model.Product;
import com.compasso.apiproducts.domain.repository.ProductRepository;
import com.compasso.apiproducts.domain.repository.ProductRepositoryImpl;


@Service
public class ProductService {

	private static final String MSG_NOT_FOUND=
			"Product id=%d not found!";
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductRepositoryImpl productRepositoryImpl;
	
	public Product save(@Valid Product product) {
		return productRepository.save(product);
	}
	
	public Optional<Product> findOne(Long id) {
		return productRepository.findById(id);
	}
	
	public List<Product> findByParameters(String q, BigDecimal minValue, BigDecimal maxValue) {
		return productRepositoryImpl.findByParameters(q, minValue, maxValue);
	}
	
	public Product update (Long id, Product product) {
		if(!productRepository.existsById(id)) {
			throw new EntityNotFoundException(String.format(MSG_NOT_FOUND, id));
		}
		Optional<Product> currentProduct = productRepository.findById(id);
		BeanUtils.copyProperties(product, currentProduct.get(), "id");
		
		return productRepository.save(currentProduct.get());
	}
}
