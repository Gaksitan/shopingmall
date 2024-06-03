package com.green.rest_api_project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.rest_api_project.dto.MemberDto;
import com.green.rest_api_project.entity.Member;
import com.green.rest_api_project.repository.MemberRepository;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {
	
	@Autowired
	private MemberRepository mRepository;
	
	
	// http://localhost:8091/api/v1/post-api/domain
	@PostMapping("/domain")
	// @RequestMapping(value = "/domain", method=RequestMethod.POST) // 위, 아래 같은 거
	public String postExample() {
		
		return "Hello Post Api~";
	}
	
	
	@PostMapping("/member0")
	public String postMember0(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("addr") String addr) {
		return name + ", " + email + ", " + addr;
	}
	
	
	// http://localhost:8091/api/v1/post-api/member?name=HongHaIn&email=hain@green.com&addr=Seoul
	@PostMapping("/member")
	public String postMember(@RequestBody Map<String, Object> postData) {
		StringBuilder sb = new StringBuilder(); // String 과 StringBuilder 의 차이 : String 은 new 할 때 마다 객체를 생성, StringBuilder 는 한번 생성된 객체에 덧붙힘 (속도가 빠름)
		postData.entrySet().forEach(map ->{
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	// http://localhost:8091/api/v1/post-api/member2
	@PostMapping("/member2") // @RequestBody 는 JSON 타입
	public String postMemberDto(@RequestBody MemberDto memberDto) {
		return memberDto.toString();
	}
	
	// http://localhost:8091/api/v1/post-api/member3
	@PostMapping("/member3")
	public MemberDto postMemberDto2(@RequestBody MemberDto memberDto) {
		return memberDto;
	}
	
	
	@PostMapping("/regist")
	public String postMember(@RequestBody Member member) {
		List<Member> list = mRepository.findAll();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getMid().equals(member.getMid())) {
				return "등록 실패(아이디 중복)";
			}
		}
		mRepository.save(member);
		return "등록 성공";
	}
	
	
}
