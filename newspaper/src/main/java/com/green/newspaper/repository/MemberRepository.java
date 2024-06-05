package com.green.newspaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.newspaper.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	
	public Member findByUsername(String username);
	
}
