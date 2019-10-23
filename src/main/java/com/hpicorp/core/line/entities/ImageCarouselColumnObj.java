package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageCarouselColumnObj {

	private String imageUrl;

    private ActionObj action;

    public ImageCarouselColumnObj(
            @JsonProperty("imageUrl") String imageUrl,
            @JsonProperty("action") ActionObj action) {
        this.imageUrl = imageUrl;
        this.action = action;
    }
	
}
