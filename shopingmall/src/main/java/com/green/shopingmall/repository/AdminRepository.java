package com.green.shopingmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.shopingmall.entity.Product;

@Repository
public interface AdminRepository extends JpaRepository<Product, Long>{
	
	public List<Product> findAll();
	
}
