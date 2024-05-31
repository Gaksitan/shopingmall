package com.green.shopingmall.entity;

import java.time.LocalDate;

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
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pno;
	private String pname;
	private Long price;
	private String pintro;
	private String pimgPath;
	private String ptype;
	private LocalDate pregDate;
	private Long porder;
	private Long pstock;
	private boolean pstate;
	private String manufacturingCompany;
	private String registrant;
}
