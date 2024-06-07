package com.green.restServer.dto;

import com.green.restServer.entity.Member;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BoardDtoForList {
	
	private Integer bno;
	private String title;
	private String content;
	private String member;
	// 파일 처리용 컬럼
	private String originName;
	private String newName;
	private String thumbnailName;
}
