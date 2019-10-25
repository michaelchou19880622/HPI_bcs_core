package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("location")
public class LocationMsg implements MessageObj {

	private String type = "location";
	
	private String title;

    private String address;

    private double latitude;

    private double longitude;
	
}
