package com.hpicorp.core.line.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.message.template.ImageCarouselTemplate;

@JsonSubTypes({
    @JsonSubTypes.Type(ButtonsTemplateObj.class),
    @JsonSubTypes.Type(CarouselTemplateObj.class),
    @JsonSubTypes.Type(ConfirmTemplateObj.class),
    @JsonSubTypes.Type(ImageCarouselTemplateObj.class),
})
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = As.PROPERTY,
    property = "type"
)
public interface TemplateObj {

}
