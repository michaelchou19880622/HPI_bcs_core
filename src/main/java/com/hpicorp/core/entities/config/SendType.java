package com.hpicorp.core.entities.config;

public enum SendType {

	NOW("now"),
	SCHEDULE_CRON("schedule_cron"),
	SCHEDULE_DATE("schedule_date");
	
	private String value;
	
	private SendType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
