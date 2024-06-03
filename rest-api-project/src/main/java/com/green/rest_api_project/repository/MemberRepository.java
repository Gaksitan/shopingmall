package com.green.rest_api_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.rest_api_project.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
	
	public Member findByMid(String mid);
	
	
}
