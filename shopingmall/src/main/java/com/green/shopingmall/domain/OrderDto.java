package com.green.shopingmall.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
	private Long pno;
	private String pname;
	private Long price;
	private String ptype;
	private LocalDate porderDate;
	private String manufacturingCompany;
	private int amount;
}
