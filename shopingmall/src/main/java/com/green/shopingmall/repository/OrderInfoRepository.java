package com.green.shopingmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.shopingmall.entity.Member;
import com.green.shopingmall.entity.OrderInfo;
import com.green.shopingmall.entity.Product;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long>{
	
	public List<OrderInfo> findByUsername(Member username);
	
	public List<OrderInfo> findByPno(Product pno);
}
