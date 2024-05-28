package com.green.shopingmall.entity;

import java.time.LocalDate;

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
@Table(name = "basket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Basket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	@ManyToOne
	@JoinColumn(name = "username")
	private Member username;
	@OneToOne
	@JoinColumn(name = "pno")
	private Product pno;
	private LocalDate bregdate;
	
	
	
}
