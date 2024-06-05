package com.green.newspaper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.newspaper.entity.Member;
import com.green.newspaper.entity.Newspaper;

public interface NewspaperRepository extends JpaRepository<Newspaper, Long>{
	
	public List<Newspaper> findByUsername(Member username);

	public Newspaper findByNno(Long nno);
	
	
	
}
