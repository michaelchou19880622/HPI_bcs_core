package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("audio")
public class AudioMsg implements MessageObj {

	private String type = "audio";
	
	private String originalContentUrl;
	
	private Integer duration;
	
}
