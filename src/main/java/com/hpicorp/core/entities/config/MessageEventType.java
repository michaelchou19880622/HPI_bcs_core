package com.hpicorp.core.entities.config;

public enum MessageEventType {

	SAVE_MESSAGE("save_message"),
	TEMP_MESSAGE("temp_message"),
	SEND_MESSAGE("send_message");
	
	private String value;
	
	private MessageEventType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
