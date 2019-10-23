package com.hpicorp.core.line.entities;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("uri")
public class URIActionObj implements ActionObj {

	private String label;

	private String uri;

	private AltUri altUri;

	public URIActionObj(
			@JsonProperty("label") String label, 
			@JsonProperty("uri") String uri,
			@JsonProperty("altUri") AltUri altUri) {
		this.label = label;
		this.uri = uri;
		this.altUri = altUri;
	}

	@Data
	public static class AltUri {

		URI desktop;

		public AltUri(@JsonProperty("desktop") URI desktop) {
			this.desktop = desktop;
		}
		
	}

}
