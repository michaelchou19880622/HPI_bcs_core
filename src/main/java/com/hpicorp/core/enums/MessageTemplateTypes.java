package com.hpicorp.core.enums;

public enum MessageTemplateTypes {
	
	BUTTONS("buttons"),
	CONFIRM("confirm"),
	CAROUSEL("carousel"),
	IMAGE_CAROUSEL("image_carousel");

	private String value;
	
	private MessageTemplateTypes(String value) {
		this.value = value;
	}

	public String getString() {
		return value;
	}
	
}

	
