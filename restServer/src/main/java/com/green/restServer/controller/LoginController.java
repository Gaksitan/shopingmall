package com.green.restServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.restServer.entity.Member;
import com.green.restServer.repository.MemberRepository;

@CrossOrigin("*")
@RestController
public class LoginController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@PostMapping("/loginProc")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		Member member = memberRepository.findByUsernameAndPassword(username, password);
		// DB에서 아이디, 패스워드 있는지 확인
		String msg= "";
		if(member != null) {
			msg = "로그인 성공 [" + member;
		}else {
			msg = "로그인 실패";
		}
		
		return msg;
	}
	
	
	
}
