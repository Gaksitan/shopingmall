package com.green.rest_api_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto { // DTO(Data Transfer Object) : 계층(레이어드 아키텍처)간 데이터 교환(전송)에 사용되는 객체, 비지니스로직이 있는 서비스를 비지니스레이어, 데이터
													// 프리젠테이션-비즈니스-데이터액세스
	private String name;
	private String email;
	private String addr;
}
