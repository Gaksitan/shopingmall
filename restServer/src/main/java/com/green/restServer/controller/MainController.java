package com.green.restServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.restServer.entity.Member;
import com.green.restServer.repository.MemberRepository;

@CrossOrigin("*")
@RestController
public class MainController {
	
	
	@Autowired
	private MemberRepository memberRepository;
	
	@RequestMapping("/joinProc")
	public String joinProc(Member member) { //()안에 파라미터 설정
		member.setRole("ROLE_MEMBER");
		
		// DB에 저장
		Member result = memberRepository.save(member);
		
		if(result != null) {
			return "등록 성공";
		}else {
			return "등록 실패";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
