package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("template")
public class TemplateMsg implements MessageObj {

	private String type = "template";

	private String altText;

	private TemplateObj template;

}
