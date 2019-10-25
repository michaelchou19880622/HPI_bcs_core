package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonTypeName("video")
public class VideoMsg implements MessageObj {

	private String type = "video";
	
	@NonNull
	private String originalContentUrl;
	
	@NonNull
	private String previewImageUrl;
	
}
