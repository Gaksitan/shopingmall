package com.green.newspaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.newspaper.dto.News_nno_approval;
import com.green.newspaper.entity.Member;
import com.green.newspaper.entity.Newspaper;
import com.green.newspaper.repository.MemberRepository;
import com.green.newspaper.repository.NewspaperRepository;

@RestController
@RequestMapping("/ajax")
public class AjaxController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private NewspaperRepository newspaperRepository;
	
	@PostMapping("/idCheck")
	public String idCheck(@RequestBody Member member) {
		
		Member mem = memberRepository.findByUsername(member.getUsername());
		String msg = "";
		if(mem == null) {
			msg = "사용가능한 아이디입니다.";
		}else {
			msg = "이미있는 아이디입니다.";
		}
		
		return msg;
	}
	
	@PostMapping("/approval")
	public String approval(@RequestBody News_nno_approval news) {
		String msg = "";
		Newspaper newspaper = newspaperRepository.findByNno(news.getNno());
		System.out.println(news);
		System.out.println("=================================");
		System.out.println(newspaper);
		if(news.isApproval() == true) {
			newspaper.setApproval(news.isApproval());
			msg = "승인되었습니다.(승인 O)";
		}else {
			newspaper.setApproval(news.isApproval());
			msg = "비승인 되었습니다.(승인 X)";
		}
		newspaperRepository.save(newspaper);
		
		return msg;
	}
	
	
}
