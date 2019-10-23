package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("uri")
public class URIImagemapActionObj implements ImagemapActionObj {

    private String linkUri;

    private ImagemapAreaObj area;

    public URIImagemapActionObj(@JsonProperty("linkUri") String linkUri, @JsonProperty("area") ImagemapAreaObj area) {
        this.linkUri = linkUri;
        this.area = area;
    }

}
