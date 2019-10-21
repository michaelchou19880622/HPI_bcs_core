package com.hpicorp.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBody {

	private String status;
	
	private String data;
	
}
