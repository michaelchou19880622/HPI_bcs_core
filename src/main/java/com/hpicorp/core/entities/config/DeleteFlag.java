package com.hpicorp.core.entities.config;

public enum DeleteFlag {

	TRUE(1),
	FALSE(0);

	private Integer value;
	
	private DeleteFlag(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
}
