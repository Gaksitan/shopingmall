package com.green.shopingmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.shopingmall.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
	
	public List<Member> findAll();
	public Member findByUserName(String username);
	// 회원가입 
	
	
}
