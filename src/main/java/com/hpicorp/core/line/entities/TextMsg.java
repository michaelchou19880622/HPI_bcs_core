package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonTypeName("text")
public class TextMsg implements MessageObj {

	private String type = "text";

	@NonNull
	private String text;

}
