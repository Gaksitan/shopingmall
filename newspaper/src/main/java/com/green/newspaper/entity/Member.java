package com.green.newspaper.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	
	@Id
	private String username;  // 1
	private String password; // 4
	private String role;    // 6
	private String name;   // 3
	private String tel;   // 7
	private String email;// 2
	private LocalDate regDate;  // 5
	
}
