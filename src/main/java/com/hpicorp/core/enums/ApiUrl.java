package com.hpicorp.core.enums;

public enum ApiUrl {

	FILE_DOWNLOAD("/filedownload/");
	
	private String value;
	
	private ApiUrl(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
