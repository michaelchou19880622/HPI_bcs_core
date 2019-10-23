package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("message")
public class MessageActionObj implements ActionObj {
	
    private String label;

    private String text;

    public MessageActionObj (
            @JsonProperty("label") String label,
            @JsonProperty("text") String text) {
        this.label = label;
        this.text = text;
    }

}
