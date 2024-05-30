package com.green.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	
	private Integer mno;
	private String username;
	private String password;
	private String name;
	private String role;
	
}
