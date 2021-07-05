package com.compasso.apiproducts.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.apiproducts.domain.model.Product;
import com.compasso.apiproducts.domain.repository.ProductRepository;
import com.compasso.apiproducts.domain.service.ProductService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> findAll () {
		return productRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product save (@Valid @RequestBody Product product) {
		return productService.save(product);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> find (@PathVariable Long id) {
		Optional<Product> product = productService.findOne(id);
		if(product.isPresent()) {
			return ResponseEntity.ok(product.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		if(!productRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		productRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/search")
	public List<Product> findByParameters(@RequestParam(required = false) BigDecimal min_price, 
			@RequestParam(required = false) BigDecimal max_price, 
			@RequestParam(required = false) String q) {
		
		return productService.findByParameters(q, min_price, max_price);
	}
	
	@PutMapping("/{id}")
	public Product update(@PathVariable Long id,@Valid @RequestBody Product product) {
		return productService.update(id, product);
	}
	
}
