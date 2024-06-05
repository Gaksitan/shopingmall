package com.green.newspaper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News_nno_approval {
	
	private Long nno;
	private boolean approval;
}
