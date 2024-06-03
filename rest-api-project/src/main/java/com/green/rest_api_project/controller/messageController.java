package com.green.rest_api_project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/msg")
public class messageController {
	
	@GetMapping("test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/student")
	public String studentMsg() {
		return "성공했나 실패했나 알아보자<br>Ok";
	}
	
}
