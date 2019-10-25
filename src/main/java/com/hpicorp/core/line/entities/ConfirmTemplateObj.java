package com.hpicorp.core.line.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

@Data
@JsonTypeName("confirm")
public class ConfirmTemplateObj implements TemplateObj {
	
	private String text;
	
    private List<ActionObj> actions;

    public ConfirmTemplateObj(@JsonProperty("text") String text, @JsonProperty("actions") List<ActionObj> actions) {
        this.text = text;
        this.actions = actions;
    }

}
