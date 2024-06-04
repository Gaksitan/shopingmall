package com.green.restapiex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.restapiex.entity.Member2;

public interface MemberRepository extends JpaRepository<Member2, String>{
	
	public Member2 findByUsername(String username);
	
}
