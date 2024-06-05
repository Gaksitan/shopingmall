package com.green.restapiex.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component // Bean 으로 등록된 클래스는 1개의 객체(인스턴스)만 생성(싱글톤 패턴) - 
@AllArgsConstructor
@NoArgsConstructor
public class Number1 {
	
	private int c1;
	private int c2;
	
}
