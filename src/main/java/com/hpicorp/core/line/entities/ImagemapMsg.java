package com.hpicorp.core.line.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("imagemap")
public class ImagemapMsg implements MessageObj {

	private String type = "imagemap";
	
	private String baseUrl;
	
	private String altText;
	
	private ImagemapBaseSizeObj baseSize;
	
	private ImagemapVideoObj video;
	
	private List<ImagemapActionObj> actions;
	
}
