package com.green.restapiex.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.restapiex.dto.LoginDto;
import com.green.restapiex.dto.Number1;
import com.green.restapiex.entity.Member2;
import com.green.restapiex.repository.MemberRepository;

@RestController
@RequestMapping("/api")
public class AjaxController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	// ajax 실행 횟수 체크용
	@Autowired
	private Number1 number;
	//int c1 = 0;
	//int c2 = 0;
	
	@PostMapping("/submitForm")
	public LoginDto submitForm(@RequestParam Map<String, String> formData) {
		
		number.setC1(number.getC1() + 1);
		LoginDto loginDto = new LoginDto(formData.get("username"), formData.get("password"));
		
		return loginDto;
	}
	
	/*
	@PostMapping("/submitForm")
	public String submitForm(@RequestParam Map<String, String> formData) {
		// 폼 데이터 처리 로직
		String username = formData.get("username");
		String password = formData.get("password");
		return username + ", " + password;
	}
	*/
	@PostMapping("/submitJson")
	public LoginDto submitJson(@RequestBody Map<String, String> jsonData) {
		
		number.setC2(number.getC2() + 1);
		LoginDto loginDto = new LoginDto(jsonData.get("username"), jsonData.get("password"));
		
		return loginDto;
	}
	/*
	@PostMapping("/submitJson")
	public String submitJson(@RequestBody Map<String, String> jsonData) {
		// JSON 데이터 처리 로직
		String username = jsonData.get("username");
		String password = jsonData.get("password");
		
		return "JSON submitted successfully: " + username + ", " + password;
	}
	*/
	
	@PostMapping("/hello")
	public String hello() {
		List<String> list = new ArrayList<>(Arrays.asList("안녕", "니하오", "오하요", "Hello", "사와디캅", "올라"));
		
		
		return "";
	}
	
	
	@PostMapping("/idCheck")
	public String idCheck(@RequestBody Member2 member) {
		
		Member2 mem = memberRepository.findByUsername(member.getUsername());
		String msg = "";
		if(mem == null) {
			msg = "사용 가능한 아이디입니다.";
		}else {
			msg = "중복된 아이디입니다. 다른 아이디로 만들어주세요.";
		}
		return msg;
	}
	
	@GetMapping("/adminCheck")
	public Number1 adminCheck() {
		
		return number;
	}
	
	
}
