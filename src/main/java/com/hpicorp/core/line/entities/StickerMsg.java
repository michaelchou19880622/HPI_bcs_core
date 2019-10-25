package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonTypeName("sticker")
public class StickerMsg implements MessageObj {

	private String type = "sticker";

	@NonNull
	private String packageId;

	@NonNull
	private String stickerId;

}
