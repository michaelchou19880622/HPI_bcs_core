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
        @JsonSubTypes.Type(MessageActionObj.class),
        @JsonSubTypes.Type(PostbackActionObj.class),
        @JsonSubTypes.Type(URIActionObj.class),
})
public interface ActionObj {
	
}
