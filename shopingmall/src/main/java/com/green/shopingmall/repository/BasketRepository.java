package com.green.shopingmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.shopingmall.entity.Basket;
import com.green.shopingmall.entity.Member;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long>{
	
	public List<Basket> findByUsername(Member username);
	
}
