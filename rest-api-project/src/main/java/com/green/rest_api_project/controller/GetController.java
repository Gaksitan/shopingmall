package com.green.rest_api_project.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.rest_api_project.dto.MemberDto;
import com.green.rest_api_project.entity.Member;
import com.green.rest_api_project.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {
	
	@Autowired
	private MemberRepository mRepository;
	
	// http://localhost:8091/api/v1/get-api/hello
	@GetMapping("/hello")
	public String getHello() {
		
		return "Hello RestApi~";
	}
	
	// http://localhost:8091/api/v1/get-api/name
	@GetMapping("/name")
	public String getName() {
		
		return "HongGilDong";
	}
	
	// http://localhost:8091/api/v1/get-api/variable1/{variable}
	@GetMapping("/variable1/{variable}") // @PathVariable 쓰면 path, 즉 경로에 있는 값을 매개변수에 쓴다. @PathVariable뒤에 ("")안에 명시를 해줘야 작동
	public String getVariable1(@PathVariable("variable") String variable) { 
		
		return variable;
	}
	
	/* @GetMapping("/variable/{variable}")
	 * public String getVariable3(@PathVariable("variable") String variable){
	 * 
	 *		return variable;
	 * }
	 */
	
	
	@GetMapping("/variable2/{variable}") 
	public String getVariable2(@PathVariable("variable") String var) { 
		
		return var;
	}
	
	// http://localhost:8091/api/v1/get-api/request?name=aaa&email=bbb&addr=ccc
	@GetMapping("/request")
	public String getRequestParam1(
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("addr") String addr
			) {
		return name + ", " + email + ", " + addr;
	}
	
	// http://localhost:8091/api/v1/get-api/request2?name=James&email=James@green.com&addr=Seoul&nickname=aaaaa
	@GetMapping("/request2")
	public String getRequestParam2(@RequestParam Map<String, String> param) { // @RequestParam 뒤에 ("") 가 필요없음. 반대로 있으면 오류
		
		StringBuilder sb = new StringBuilder();
		param.entrySet().forEach(map ->{
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	// http://localhost:8091/api/v1/get-api/request3?name=James&email=James@green.com&addr=Seoul
	@GetMapping("/request3")
	public String getRequestParam3(MemberDto memberDto) { // 커맨드 객체(파라미터를 처리하는 객체, Model 를 쓰지않아도 파라미터를 뷰까지 전달)
		
		return memberDto.toString();
	}
	
	
	@GetMapping("/myInfo")
	public Member myInfo(HttpSession session) {
		Member member = mRepository.findByMid((String) session.getAttribute("mid"));
		return member;
	}
	
	
	
	
}
