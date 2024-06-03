package com.green.rest_api_project.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {
	
	// http://localhost:8091/api/v1/delete-api/123456
	@DeleteMapping("/{variable}")
	public String deleteVariable(@PathVariable("variable") String variable) {
		return variable;
	}
	
	// http://localhost:8091/api/v1/delete-api/request1?email=hain@green.com
	@DeleteMapping("/request1")
	public String deleteRequestParam(@RequestParam("email") String email) {
		return "email : " + email;
	}
	
	
	
	
}
