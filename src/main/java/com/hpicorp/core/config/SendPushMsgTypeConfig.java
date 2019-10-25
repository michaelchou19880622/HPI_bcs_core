package com.hpicorp.core.config;

public enum SendPushMsgTypeConfig {
	
	PUSH("PUSH"),
	MULTICAST("MULTICAST"),
	BROADCAST("BROADCAST");
	
	private String value;
	
	private SendPushMsgTypeConfig(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public static SendPushMsgTypeConfig checkConfig(String text) {
	    for (SendPushMsgTypeConfig config : SendPushMsgTypeConfig.values()) {
	      if (config.value.equalsIgnoreCase(text)) {
	        return config;
	      }
	    }
	    throw new IllegalArgumentException("No constant with text " + text + " found");
	  }
	
}

	
