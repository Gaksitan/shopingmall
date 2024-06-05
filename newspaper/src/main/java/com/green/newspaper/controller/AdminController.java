package com.green.newspaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.newspaper.entity.Member;
import com.green.newspaper.entity.Newspaper;
import com.green.newspaper.repository.MemberRepository;
import com.green.newspaper.repository.NewspaperRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private NewspaperRepository newspaperRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@RequestMapping("/adminMain")
	public String adminMain(HttpSession session ,Model model) {
		String username = (String) session.getAttribute("username");
		Member mem = memberRepository.findByUsername(username);
		if(mem.getRole().equals("ROLE_ADMIN")) {
			List<Newspaper> list = newspaperRepository.findAll();
			model.addAttribute("newsList", list);
		}else {
			return "/user/error405";
		}
		
		return "admin/adminMain";
	}
	
	
}
