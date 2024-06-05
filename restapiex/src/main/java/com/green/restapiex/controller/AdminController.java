package com.green.restapiex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.restapiex.dto.Dash;

@Controller
public class AdminController {
	
	@Autowired
	private Dash dash;
	
	@RequestMapping("/admin2")
	public String admin2(Model model) {
		model.addAttribute("num", dash);
		return "admin2";
	}
	
	@RequestMapping("/ajaxex2")
	public String ajaxex2() {
		
		return "ajaxex2";
	}
	
}
