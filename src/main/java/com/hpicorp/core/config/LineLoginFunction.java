package com.hpicorp.core.config;

public enum LineLoginFunction {

	LINK("LINK");
	
	private String value;
	
	private LineLoginFunction(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
