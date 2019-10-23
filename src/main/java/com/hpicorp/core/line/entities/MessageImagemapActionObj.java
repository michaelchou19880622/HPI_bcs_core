package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

@Data
@JsonTypeName("message")
public class MessageImagemapActionObj implements ImagemapActionObj {

    private String text;

    private ImagemapAreaObj area;

    public MessageImagemapActionObj(@JsonProperty("text") String text, @JsonProperty("area") ImagemapAreaObj area) {
        this.text = text;
        this.area = area;
    }
	
}
