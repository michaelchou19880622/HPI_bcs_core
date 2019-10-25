package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("image")
public class ImageMsg implements MessageObj {

	private String type = "image";

	private String originalContentUrl;

	private String previewImageUrl;

}
