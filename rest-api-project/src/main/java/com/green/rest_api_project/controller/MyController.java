package com.green.rest_api_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.rest_api_project.entity.Member;
import com.green.rest_api_project.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	
	@Autowired
	private MemberRepository mRepository;
	
	
	
	@RequestMapping({"/", "index"})
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("/postData")
	public String postData() {
		return "postData";
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		return "registForm";
	}
	/*
	@RequestMapping("/regist")
	public String regist() {
		return "redirect:/";
	}
	*/
	
	@RequestMapping("/loginForm")
	public String loginForm(HttpServletRequest request) {
		return "loginForm";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		Member member = mRepository.findByMid(request.getParameter("mid"));
		if(member.getMid().equals(request.getParameter("mid")) && member.getMpw().equals(request.getParameter("mpw"))) {
			HttpSession session = request.getSession();
			session.setAttribute("mid", member.getMid());
			session.setAttribute("member", member);
			model.addAttribute("msg", "로그인 성공");
		}else {
			model.addAttribute("msg", "로그인 실패");
			return "loginForm";
		}
		return "index";
	}
	
	
	@RequestMapping("/myPage")
	public String myPage(HttpSession session) {
		String mid = (String) session.getAttribute("mid");
		Member member = mRepository.findByMid(mid);
		return "myPage";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	
	
	
}
