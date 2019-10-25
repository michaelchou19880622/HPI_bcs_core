package com.hpicorp.core.line.entities;

import java.util.List;

import lombok.Data;

@Data
public class TemplateContent {

	private String type;

	private String thumbnailImageUrl;

	private String imageAspectRatio;

	private String imageSize;

	private String imageBackgroundColor;

	private String title;

	private String text;

	private ActionObj defaultAction;

	private List<ActionObj> actions;

	private List<TemplateContent> columns;

}
