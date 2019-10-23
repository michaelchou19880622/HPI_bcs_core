package com.hpicorp.core.config;

public enum APITypeConfig {
	
	BOT("BOT"), // Send Reply Message
	BC("BC");   // Send Push Message
	
	private String value;
	
	private APITypeConfig(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public static APITypeConfig checkConfig(String text) {
	    for (APITypeConfig config : APITypeConfig.values()) {
	      if (config.value.equalsIgnoreCase(text)) {
	        return config;
	      }
	    }
	    throw new IllegalArgumentException("No constant with text " + text + " found");
	  }
	
}

	
