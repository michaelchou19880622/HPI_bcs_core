package com.hpicorp.core.entities.config;

public enum ErrorMessage {

	ACTION_TYPE_NOT_MATCH("動作沒有對應的類別");

	private String value;

	private ErrorMessage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
