package com.hpicorp.core.line.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

@Data
@JsonTypeName("buttons")
public class ButtonsTemplateObj implements TemplateObj {

	private String thumbnailImageUrl;

	private String imageAspectRatio;

    private String imageSize;

    private String imageBackgroundColor;

    private String title;

    private String text;

    private ActionObj defaultAction;

    private List<ActionObj> actions;
	
}
