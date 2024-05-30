package com.green.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.security.entity.Member;

@Repository // JpaRepository 를 상속받으면 자동으로 Bean에 등록되기때문에 @Repository를 안써줘도 된다.
public interface MemberRepository extends JpaRepository<Member, Integer>{

	public Member findByUsername(String username);
	
	
	
}
