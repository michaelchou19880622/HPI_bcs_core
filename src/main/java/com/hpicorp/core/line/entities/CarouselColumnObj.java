package com.hpicorp.core.line.entities;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarouselColumnObj {

	private String thumbnailImageUrl;

    private String imageBackgroundColor;

    private String title;

    private String text;

    private ActionObj defaultAction;

    private List<ActionObj> actions;

    public CarouselColumnObj(
            @JsonProperty("thumbnailImageUrl") String thumbnailImageUrl,
            @JsonProperty("title") String title,
            @JsonProperty("text") String text,
            @JsonProperty("actions") List<ActionObj> actions) {
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.title = title;
        this.text = text;
        this.defaultAction = null;
        this.actions = actions != null ? actions : Collections.emptyList();
        this.imageBackgroundColor = null;
    }
	
}
