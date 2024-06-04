package com.green.restapiex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
	
	String[] msgs = {"hello", "hi", "안녕?", "니하오", "곰방와"};
	int i = 0;
	
	@RequestMapping({"/"})
	public String root() {
		return "redirect:/ajaxex";
	}
	
	@RequestMapping("/ajaxex")
	public String ajaxex(Model model) {
		if(i > 3) {
			i = 0;
		}
		model.addAttribute("greet", msgs[i++]);
		return "ajaxex";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "/index";
	}
	
	@RequestMapping("/index2")
	public String index2() {
		return "/index2";
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		return "/registForm";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "/admin";
	}
	
	
	
	
}
