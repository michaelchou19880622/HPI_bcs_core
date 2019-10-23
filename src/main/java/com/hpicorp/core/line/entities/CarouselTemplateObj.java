package com.hpicorp.core.line.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("carousel")
public class CarouselTemplateObj implements TemplateObj {

	private List<CarouselColumnObj> columns;

    private String imageAspectRatio;

    private String imageSize;

    public CarouselTemplateObj(@JsonProperty("columns") List<CarouselColumnObj> columns) {
        this.columns = columns;
        this.imageAspectRatio = null;
        this.imageSize = null;
    }
	
}
