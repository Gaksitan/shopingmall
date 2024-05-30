package com.green.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.security.config.CustomUserDetails;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/")
	public String welcome(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		
		// Authentication authentication = 
		
		model.addAttribute("username", customUserDetails.getUsername());
		model.addAttribute("name", customUserDetails.getName());
		model.addAttribute("role", customUserDetails.getRole());
		
		return "admin/welcome";
	}
	
	
}
