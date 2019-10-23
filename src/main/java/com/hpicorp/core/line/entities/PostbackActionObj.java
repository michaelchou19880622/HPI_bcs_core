package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("postback")
public class PostbackActionObj implements ActionObj {
	
	private String label;

    private String data;

    private String displayText;

    private String text;

    public PostbackActionObj(
            @JsonProperty("label") String label,
            @JsonProperty("data") String data,
            @JsonProperty("displayText") String displayText,
            @JsonProperty("text") String text) {
        this.label = label;
        this.data = data;
        this.displayText = displayText;
        this.text = text;
    }

}
