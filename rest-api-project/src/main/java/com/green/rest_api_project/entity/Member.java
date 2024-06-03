package com.green.rest_api_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	
	@Id
	private String mid;
	private String mpw;
	private String name;
	private String email;
	private String addr;
	
}
