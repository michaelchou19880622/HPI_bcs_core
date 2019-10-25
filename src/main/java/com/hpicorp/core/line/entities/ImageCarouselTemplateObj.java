package com.hpicorp.core.line.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("image_carousel")
public class ImageCarouselTemplateObj implements TemplateObj {

	private List<ImageCarouselColumnObj> columns;

    public ImageCarouselTemplateObj(@JsonProperty("columns") List<ImageCarouselColumnObj> columns) {
        this.columns = columns;
    }
	
}