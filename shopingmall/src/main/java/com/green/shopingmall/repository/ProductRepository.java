package com.green.shopingmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.shopingmall.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	public Product findByPno(Long pno);
	
	
}
