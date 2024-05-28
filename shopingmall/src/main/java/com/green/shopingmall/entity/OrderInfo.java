package com.green.shopingmall.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orderinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ono;
	@ManyToOne
	@JoinColumn(name = "username")
	private String username;
	@OneToOne
	@JoinColumn(name = "pno")
	private Long pno;
	private String oaddr1;
	private String oaddr2;
	
}
