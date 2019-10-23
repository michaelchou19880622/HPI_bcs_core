package com.hpicorp.core.dto;

import com.hpicorp.core.config.APITypeConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class RequestDto {

	private String channelId;
	
	private String channelAccessToken;
	
	private Long channelInfoId;
	
	private APITypeConfig apiType;
	
}
