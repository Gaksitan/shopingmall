package com.green.shopingmall.entity;

import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	
	@Id
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userTel1;
	private String userTel2;
	private LocalDate userBirthDate;
	private String name;
	private String zipCode;
	private String userAddr1;
	private String userAddr2;
	private String detailAddr;
	private LocalDate userRegDate;
	private String userRole;
	private String userGender;
	
	
}
