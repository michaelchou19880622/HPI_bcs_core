package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(MessageImagemapActionObj.class),
        @JsonSubTypes.Type(URIImagemapActionObj.class),
})
public interface ImagemapActionObj {

}
