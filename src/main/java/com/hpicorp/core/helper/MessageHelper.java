package com.hpicorp.core.helper;

import java.util.ArrayList;
import java.util.List;

import com.hpicorp.core.entities.config.ErrorMessage;
import com.hpicorp.core.exception.AppException;
import com.hpicorp.core.line.entities.ActionObj;
import com.hpicorp.core.line.entities.AudioMsg;
import com.hpicorp.core.line.entities.ButtonsTemplateObj;
import com.hpicorp.core.line.entities.CarouselTemplateObj;
import com.hpicorp.core.line.entities.ConfirmTemplateObj;
import com.hpicorp.core.line.entities.ImageCarouselTemplateObj;
import com.hpicorp.core.line.entities.ImageMsg;
import com.hpicorp.core.line.entities.ImagemapActionObj;
import com.hpicorp.core.line.entities.ImagemapAreaObj;
import com.hpicorp.core.line.entities.ImagemapMsg;
import com.hpicorp.core.line.entities.LocationMsg;
import com.hpicorp.core.line.entities.MessageActionObj;
import com.hpicorp.core.line.entities.MessageImagemapActionObj;
import com.hpicorp.core.line.entities.MessageObj;
import com.hpicorp.core.line.entities.PostbackActionObj;
import com.hpicorp.core.line.entities.StickerMsg;
import com.hpicorp.core.line.entities.TemplateMsg;
import com.hpicorp.core.line.entities.TemplateObj;
import com.hpicorp.core.line.entities.TextMsg;
import com.hpicorp.core.line.entities.URIActionObj;
import com.hpicorp.core.line.entities.URIImagemapActionObj;
import com.hpicorp.core.line.entities.VideoMsg;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.AudioMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.ImagemapMessage;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.VideoMessage;
import com.linecorp.bot.model.message.imagemap.ImagemapAction;
import com.linecorp.bot.model.message.imagemap.ImagemapArea;
import com.linecorp.bot.model.message.imagemap.ImagemapBaseSize;
import com.linecorp.bot.model.message.imagemap.MessageImagemapAction;
import com.linecorp.bot.model.message.imagemap.URIImagemapAction;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.message.template.ImageCarouselColumn;
import com.linecorp.bot.model.message.template.ImageCarouselTemplate;
import com.linecorp.bot.model.message.template.Template;

public class MessageHelper {
	
	private MessageHelper() {
	}
	
	public static Message switchMessage(MessageObj obj) {
		Message message = null;
		if (obj instanceof TextMsg) {
			message = convertTextToMessage((TextMsg)obj);
		} else if (obj instanceof StickerMsg) {
			message = convertStickerToMessage((StickerMsg)obj);
		} else if (obj instanceof ImageMsg) {
			message = convertImageToMessage((ImageMsg)obj);
		} else if (obj instanceof LocationMsg) {
			message = convertLocationToMessage((LocationMsg)obj);
		} else if (obj instanceof VideoMsg) {
			message = convertVideoToMessage((VideoMsg)obj);
		} else if (obj instanceof AudioMsg) {
			message = convertAudioToMessage((AudioMsg)obj);
		} else if (obj instanceof ImagemapMsg) {
			message = convertImagemapToMessage((ImagemapMsg)obj);
		} else if (obj instanceof TemplateMsg) {
			message = convertTemplateToMessage((TemplateMsg)obj);
		}
		return message;
	}

	private static Message convertTextToMessage(TextMsg textMsg) {
		return new TextMessage(textMsg.getText());
	}
	
	private static Message convertStickerToMessage(StickerMsg stickerMsg) {
		return new StickerMessage(stickerMsg.getPackageId(), stickerMsg.getStickerId());
	}
	
	private static Message convertImageToMessage(ImageMsg imageMsg) {
		return new ImageMessage(imageMsg.getOriginalContentUrl(), imageMsg.getPreviewImageUrl());
	}
	
	private static Message convertLocationToMessage(LocationMsg locationMsg) {
		return new LocationMessage(locationMsg.getTitle(), locationMsg.getAddress(), locationMsg.getLatitude(), locationMsg.getLongitude());
	}
	
	private static Message convertVideoToMessage(VideoMsg videoMsg) {
		return new VideoMessage(videoMsg.getOriginalContentUrl(), videoMsg.getPreviewImageUrl());
	}
	
	private static Message convertAudioToMessage(AudioMsg audioMsg) {
		return new AudioMessage(audioMsg.getOriginalContentUrl(), audioMsg.getDuration());
	}
	
	private static Message convertImagemapToMessage(ImagemapMsg imagemapMsg) {
		List<ImagemapAction> actions = MessageHelper.convertImagemapActionList(imagemapMsg.getActions());
		ImagemapBaseSize baseSize = new ImagemapBaseSize(imagemapMsg.getBaseSize().getHeight(), imagemapMsg.getBaseSize().getWidth());
		return new ImagemapMessage(imagemapMsg.getBaseUrl(), imagemapMsg.getAltText(), baseSize, actions);
	}
	
	private static Message convertTemplateToMessage(TemplateMsg templateMsg) {
		TemplateObj templateObj = templateMsg.getTemplate();
		List<Action> actions = null;
		Template template = null;
		if (templateObj instanceof ButtonsTemplateObj) {
			ButtonsTemplateObj button = (ButtonsTemplateObj)templateObj;
			List<Action> actionList = MessageHelper.convertActionList(button.getActions());
			template = new ButtonsTemplate(button.getThumbnailImageUrl(), button.getTitle(), button.getText(), actionList);
		} else if (templateObj instanceof CarouselTemplateObj) {
			CarouselTemplateObj carousel = (CarouselTemplateObj)templateObj;
			List<CarouselColumn> columnsList = new ArrayList<>();
			carousel.getColumns().forEach(i -> {
				List<Action> actionList = MessageHelper.convertActionList(i.getActions());
				CarouselColumn carouselColumn = new CarouselColumn(i.getThumbnailImageUrl(), i.getTitle(), i.getText(), actionList);
				columnsList.add(carouselColumn);
			});
			template = new CarouselTemplate(columnsList);
		} else if (templateObj instanceof ConfirmTemplateObj) {
			ConfirmTemplateObj confirm = (ConfirmTemplateObj)templateObj;
			actions = MessageHelper.convertActionList(confirm.getActions());
			template = new ConfirmTemplate(confirm.getText(), actions);
		} else if (templateObj instanceof ImageCarouselTemplateObj) {
			ImageCarouselTemplateObj imageCarousel = (ImageCarouselTemplateObj)templateObj;
			List<ImageCarouselColumn> columnList = new ArrayList<>();
			imageCarousel.getColumns().forEach(i -> {
				Action action = MessageHelper.convertAction(i.getAction());
				ImageCarouselColumn column = new ImageCarouselColumn(i.getImageUrl(), action);
				columnList.add(column);
			});
			template = new ImageCarouselTemplate(columnList);
		}
		return new TemplateMessage(templateMsg.getAltText(), template);
	}
	
	private static List<ImagemapAction> convertImagemapActionList(List<ImagemapActionObj> actionList) {
		List<ImagemapAction> actions = new ArrayList<>();
		for (ImagemapActionObj actionObj : actionList) {
			ImagemapAction action = MessageHelper.convertImagemapAction(actionObj);
			if (null == action) throw new AppException(ErrorMessage.ACTION_TYPE_NOT_MATCH.getValue());
			actions.add(action);
		}
		return actions;
	}
	
	private static ImagemapAction convertImagemapAction(ImagemapActionObj action) {
		if (action instanceof MessageImagemapActionObj) {
			MessageImagemapActionObj messageAction = (MessageImagemapActionObj)action;
			ImagemapAreaObj areaObj = messageAction.getArea();
			ImagemapArea area = new ImagemapArea(areaObj.getX(), areaObj.getY(), areaObj.getWidth(), areaObj.getHeight());
			return new MessageImagemapAction(messageAction.getText(), area);
		} else if (action instanceof URIImagemapActionObj) {
			URIImagemapActionObj uriAction = (URIImagemapActionObj)action;
			ImagemapAreaObj areaObj = uriAction.getArea();
			ImagemapArea area = new ImagemapArea(areaObj.getX(), areaObj.getY(), areaObj.getWidth(), areaObj.getHeight());
			return new URIImagemapAction(uriAction.getLinkUri(), area);
		}
		return null;
	}
	
	private static List<Action> convertActionList(List<ActionObj> actionList) {
		List<Action> actions = new ArrayList<>();
		for (ActionObj hpiAction : actionList) {
			Action action = MessageHelper.convertAction(hpiAction);
			if (null == action) throw new AppException(ErrorMessage.ACTION_TYPE_NOT_MATCH.getValue());
			actions.add(action);
		}
		return actions;
	}
	
	private static Action convertAction(ActionObj action) {
		if (action instanceof MessageActionObj) {
			MessageActionObj message = (MessageActionObj)action;
			return new MessageAction(message.getLabel(), message.getText());
		} else if (action instanceof PostbackActionObj) {
			PostbackActionObj postback = (PostbackActionObj)action;
			return new PostbackAction(postback.getLabel(), postback.getData(), postback.getText());
		} else if (action instanceof URIActionObj) {
			URIActionObj uri = (URIActionObj)action;
			return new URIAction(uri.getLabel(), uri.getUri(), null);
		} else {
			return null;
		}
	}
	
}
