package com.green.newspaper.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.newspaper.entity.Member;
import com.green.newspaper.entity.Newspaper;
import com.green.newspaper.repository.MemberRepository;
import com.green.newspaper.repository.NewspaperRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/journal")
public class JournalistController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private NewspaperRepository newspaperRepository;
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "/journal/writeForm";
	}
	
	@RequestMapping("/writeNews")
	public String writeNews(@RequestParam("title") String title, @RequestParam("content") String content, HttpSession session) {
		Member mem = memberRepository.findByUsername((String) session.getAttribute("username"));
		Newspaper news = new Newspaper(null, title, content, mem, LocalDate.now(), null, false, 0);
		
		newspaperRepository.save(news);
		return "index";
	}
	
	@RequestMapping("/myNews")
	public String myNews(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		Member member = memberRepository.findByUsername(username);
		List<Newspaper> list = newspaperRepository.findByUsername(member);
		model.addAttribute("myNewsList", list);
		
		return "/journal/myNews";
	}
	
	
	
	
	
}
