package com.green.shopingmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.shopingmall.entity.Basket;
import com.green.shopingmall.entity.Member;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long>{
	
	public List<Basket> findByUsername(Member username);
	
	@Query(value = "SELECT * FROM basket WHERE username = :username AND pno = :pno", nativeQuery = true)
	public Basket findByUsernameAndPno(@Param("username") String username, @Param("pno") Long pno);
	
}
