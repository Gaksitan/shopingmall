package com.green.restapiex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.restapiex.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Integer>{
	
}
