package com.green.shopingmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.shopingmall.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	public Product findByPno(Long pno);
	public List<Product> findAll();
	public List<Product> findByPnameLike(String pname);
	
	public List<Product> findByRegistrant(String registrant);
}
