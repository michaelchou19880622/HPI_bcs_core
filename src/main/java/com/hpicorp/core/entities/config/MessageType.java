package com.hpicorp.core.entities.config;

public enum MessageType {

	IMAGE_MAP("image_map"),
	TEMPLATE("template"),
	SEND_MESSAGE("send_message");
	
	private String value;
	
	private MessageType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
