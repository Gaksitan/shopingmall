package com.green.shopingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mem")
public class Member_Controller {
	
	@RequestMapping("/MyInfo")
	public String loginForm() {
		
		return "thymeleaf/MyInfo";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
