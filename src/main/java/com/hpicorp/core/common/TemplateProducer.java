package com.hpicorp.core.common;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.hpicorp.core.entities.MessageCarouselAction;
import com.hpicorp.core.entities.MessageCarouselColumn;
import com.hpicorp.core.entities.MessageCarouselTemplate;
import com.hpicorp.core.entities.MessageImageMap;
import com.hpicorp.core.entities.MessageImageMapAction;
import com.hpicorp.core.entities.MessageTemplate;
import com.hpicorp.core.entities.MessageTemplateAction;
import com.hpicorp.core.enums.MessageActionTypes;
import com.hpicorp.core.enums.MessageTemplateTypes;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.action.URIAction.AltUri;
import com.linecorp.bot.model.message.ImagemapMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.imagemap.ImagemapAction;
import com.linecorp.bot.model.message.imagemap.ImagemapArea;
import com.linecorp.bot.model.message.imagemap.ImagemapBaseSize;
import com.linecorp.bot.model.message.imagemap.URIImagemapAction;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.message.template.Template;

public class TemplateProducer {

	private TemplateProducer() {}
	
	public static Message imagemap(MessageImageMap m, List<ImagemapAction> actions) {
		ImagemapBaseSize baseSize = new ImagemapBaseSize(m.getBaseSizeHeight(), m.getBaseSizeWidth());
		return new ImagemapMessage(m.getBaseUrl(), m.getAltText(), baseSize, actions);
	}
	
	public static CarouselColumn carouselColumn(MessageCarouselColumn col, List<Action> actions) {
		return new CarouselColumn(col.getThumbnailImageUrl(), col.getTitle(), col.getText(), actions);
	}
	
	public static Message carouselTemplate(MessageCarouselTemplate c, List<CarouselColumn> columns) {
		CarouselTemplate template = new CarouselTemplate(columns);
		return new TemplateMessage(c.getAltText(), template);
	}
	
	public static Message template(MessageTemplate t, List<Action> actions) {
		Template template = null;
		String type = t.getType();
		if ( type.equals(MessageTemplateTypes.BUTTONS.toString()) ) {
			template = new ButtonsTemplate(t.getThumbnailImageUrl(), t.getTitle(), t.getText(), actions);
		} else if (type.equals(MessageTemplateTypes.CONFIRM.toString())) {
			template =  new ConfirmTemplate(t.getText(), actions);
		}
		if (template != null) {
			return new TemplateMessage(t.getAltText(), template);
		}
		return null;
	}
	public static List<Action> carouselAction(List<MessageCarouselAction> actions) {
		List<Action> resultActions = new ArrayList<>();
		for (MessageCarouselAction action : actions) {
			Action tempA = TemplateProducer.action(action);
			if (tempA != null) {
				resultActions.add(tempA);
			}
		}
		return resultActions;
	}
	
	public static List<Action> action(List<MessageTemplateAction> actions) {
		List<Action> resultActions = new ArrayList<>();
		for (MessageTemplateAction action : actions) {
			Action tempA = TemplateProducer.action(action);
			if (tempA != null) {
				resultActions.add(tempA);
			}
		}
		return resultActions;
	}
	public static Action action(MessageCarouselAction entity) {
		return getActionBy(entity.getType(), entity.getLabel(), entity.getText(), entity.getUri(), entity.getData());
	}
	
	public static Action action(MessageTemplateAction entity) {
		return getActionBy(entity.getType(), entity.getLabel(), entity.getText(), entity.getUri(), entity.getData());
	}
	
	public static Action getActionBy(String type, String label, String text, String uri, String data) {
		if (type.equals(MessageActionTypes.POSTBACK.toString())) {
			return new PostbackAction(label, data);
		} else if (type.equals(MessageActionTypes.MESSAGE.toString())) {
			return new MessageAction(label, text);
		} else if (type.equals(MessageActionTypes.URI.toString())) {
			AltUri alturi = null;
			try {
				alturi = new AltUri(new URI(uri));
			} catch (URISyntaxException e) {
				alturi = null;
			}
			return new URIAction(label, uri, alturi); 
		}
		return null;
	}
	
	public static List<ImagemapAction> imagemapAction(List<MessageImageMapAction> actions) {
		List<ImagemapAction> resultActions = new ArrayList<>();
		for (MessageImageMapAction action : actions) {
			ImagemapAction tempA = TemplateProducer.imagemapAction(action);
			if (tempA != null) {
				resultActions.add(tempA);
			}
		}
		return resultActions;
	}
	
	public static ImagemapAction imagemapAction(MessageImageMapAction entity) {
		try {
			String type = entity.getType();
			ImagemapArea area = null;
			area = new ImagemapArea(entity.getAreaX(), entity.getAreaY(), entity.getAreaWidth(), entity.getAreaHeight());
			if (type.equals(MessageActionTypes.MESSAGE.toString())) {
				return new com.linecorp.bot.model.message.imagemap.MessageImagemapAction(entity.getText(), area);
			} else if (type.equals(MessageActionTypes.URI.toString())) {
				return new URIImagemapAction(entity.getLinkUri(), area);
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
}
