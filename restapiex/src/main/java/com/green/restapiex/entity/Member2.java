package com.green.restapiex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Member2 {
	
	@Id
	private String username;
	private String password;
	
}
