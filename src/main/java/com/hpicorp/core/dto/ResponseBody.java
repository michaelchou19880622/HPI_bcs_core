package com.hpicorp.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBody {

	private Integer status;
	
	private String data;
	
}
