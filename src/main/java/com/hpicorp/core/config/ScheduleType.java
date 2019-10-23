package com.hpicorp.core.config;

public enum ScheduleType {

	// 發送訊息排程群組	
	SEND_MESSAGE_GROUP("SEND_MESSAGE_GROUP"),
	
	// 檢查 ChannelAccessToken 過期排程群組
	ISSUE_CHANNEL_ACCESS_TOKEN_GROUP("ISSUE_CHANNEL_ACCESS_TOKEN_GROUP"),
	
	// 檢查續訂排程群組
	RENEW_SUBSCRIBE_GROUP("RENEW_SUBSCRIBE_GROUP"),
	
	// 檢查活動成效排程群組
	PROCESS_FINISH_ACTINFO_ORDER("PROCESS_FINISH_ACTINFO_ORDER"),
	
	// 檢查續訂 & 活動成效排程群組
	RENEW_SUBSCRIBE_AND_PROCESS_FINISH_ACTINFO_ORDER("RENEW_SUBSCRIBE_AND_PROCESS_FINISH_ACTINFO_ORDER");

	private String value;
	
	private ScheduleType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
