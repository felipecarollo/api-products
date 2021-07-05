package com.compasso.apiproducts.domain.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
 
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.compasso.apiproducts.domain.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Product> findByParameters(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		
		Root<Product> root = criteria.from(Product.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasText(q)) {
			predicates.add(builder.or(builder.like(builder.upper(root.get("description")) , "%"+q.toUpperCase()+"%"),
					 builder.like(builder.upper(root.get("name")), "%"+q.toUpperCase()+"%")));
		}
		
		if(minPrice != null) {
			predicates.add(builder
					.greaterThanOrEqualTo(root.get("price"), 
							minPrice));
		}
		
		if(maxPrice != null) {
			predicates.add(builder
					.lessThanOrEqualTo(root.get("price"), 
							maxPrice));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Product> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

}
