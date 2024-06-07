package com.green.viewServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@GetMapping("/main")
	public String main() {
		return "member/main";
	}
	
	@GetMapping("/regBoard")
	public String regBoard() {
		return "member/regBoardForm";
	}
	
	@GetMapping("/getBoardList")
	public String getBoardList() {
		return "member/boardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard() {
		
		return "member/getBoard";
	}
		
	
	
	
}
