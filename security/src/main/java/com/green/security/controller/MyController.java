package com.green.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.security.dto.MemberDto;
import com.green.security.entity.Member;
import com.green.security.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/")
	public String root() {
		log.info("root..........");
		return "/index";
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		return "/registForm";
	}
	
	@RequestMapping("/registProc")
	public @ResponseBody String regist(MemberDto memberDto) {
		log.info("regist....." + memberDto);
		
		Member member = new Member();
		member.setUsername(memberDto.getUsername());
		String newPw = bCryptPasswordEncoder.encode(memberDto.getPassword()); // pw 암호화하기 - Security 에는 암호화 방법도 정해둠. 그걸 사용하기위해(Security 설정파일 만들기)
		member.setPassword(newPw); // 암호화된 pw 받기
		
		member.setName(memberDto.getName());
		member.setRole("ROLE_MEMBER");
		
		memberRepository.save(member);
		
		return "회원가입 완료";
	}
	
	/*
	@RequestMapping("/regist")
	public @ResponseBody String regist(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String role = "ROLE_MEMBER";
		log.info("회원가입 완료......");
		Member member = new Member(null, username, password, name, role);
		return "회원가입 완료";
	}
	*/
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
