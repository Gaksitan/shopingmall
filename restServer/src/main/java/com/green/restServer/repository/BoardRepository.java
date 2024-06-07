package com.green.restServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.restServer.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{ // JpaRepository 를 상속받으면 자동으로 Bean 에 등록이 된다.
	
	
	
}
