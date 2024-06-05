package com.green.newspaper.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Newspaper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nno;
	private String title;
	private String content;
	@ManyToOne
	@JoinColumn(name = "writer")
	private Member username;
	private LocalDate regDate;
	private LocalDate modDate;
	private boolean approval;
	private int important;
	
}
