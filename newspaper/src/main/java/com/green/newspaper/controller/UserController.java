package com.green.newspaper.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.newspaper.entity.Member;
import com.green.newspaper.entity.Newspaper;
import com.green.newspaper.repository.MemberRepository;
import com.green.newspaper.repository.NewspaperRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private NewspaperRepository newspaperRepository;
	
	@RequestMapping({"/", "/index"})
	public String index() {
		return "/index";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "/loginForm";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Member member, Model model) {
		HttpSession session = request.getSession();
		Member mem = memberRepository.findByUsername(member.getUsername());
		System.out.println(mem);
		String loginMsg = "";
		if(mem != null && mem.getPassword().equals(member.getPassword())) {
			session.setAttribute("member", mem);
			session.setAttribute("username", mem.getUsername());
			session.setAttribute("name", mem.getName());
			session.setAttribute("role", mem.getRole());
			loginMsg = "로그인 성공";
		}else if(mem == null) {
			loginMsg = "존재하지 않는 아이디입니다. 회원가입을 해주세요.";
		}else {
			loginMsg = "로그인 실패, 아이디나 비밀번호가 일치하지 않습니다.";
		}
		
		model.addAttribute("loginMsg", loginMsg);
		
		return "/index";
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		return "/registForm";
	}
	
	@RequestMapping("/regist")
	public String regist(Member member, Model model) {
		Member mem = memberRepository.findByUsername(member.getUsername());
		String regMsg = "";
		if(mem != null) {
			regMsg = "가입오류(이미 있는 아이디입니다)";
		}else {
			regMsg = "가입이 완료되었습니다.";
			member.setRegDate(LocalDate.now());
			member.setRole("ROLE_MEMBER");
			memberRepository.save(member);
		}
		model.addAttribute("regMsg", regMsg);
		
		return "redirect:/index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "/index";
	}
	
	@RequestMapping("/error405")
	public String error405() {
		return "/error405";
	}
	
	@RequestMapping("/newsDetail")
	public String newsDetail(@RequestParam("nno") Long nno, Model model) {
		
		Newspaper news = newspaperRepository.findByNno(nno);
		System.out.println(news.getTitle());
		model.addAttribute("news", news);
		
		return "/newsDetail";
	}
	
	
	
}
