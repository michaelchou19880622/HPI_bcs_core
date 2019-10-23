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
        @JsonSubTypes.Type(TextMsg.class),
        @JsonSubTypes.Type(ImageMsg.class),
        @JsonSubTypes.Type(StickerMsg.class),
        @JsonSubTypes.Type(LocationMsg.class),
        @JsonSubTypes.Type(AudioMsg.class),
        @JsonSubTypes.Type(VideoMsg.class),
        @JsonSubTypes.Type(ImagemapMsg.class),
        @JsonSubTypes.Type(TemplateMsg.class),
})
public interface MessageObj {

}
