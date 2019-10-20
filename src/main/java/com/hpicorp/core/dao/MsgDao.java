package com.hpicorp.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hpicorp.core.common.MessageToJsonUtil;
import com.hpicorp.core.common.OptionalExtension;
import com.hpicorp.core.common.TemplateProducer;
import com.hpicorp.core.entities.MessageAudio;
import com.hpicorp.core.entities.MessageCarouselAction;
import com.hpicorp.core.entities.MessageCarouselColumn;
import com.hpicorp.core.entities.MessageCarouselTemplate;
import com.hpicorp.core.entities.MessageImage;
import com.hpicorp.core.entities.MessageImageMap;
import com.hpicorp.core.entities.MessageImageMapAction;
import com.hpicorp.core.entities.MessageLocation;
import com.hpicorp.core.entities.MessageSticker;
import com.hpicorp.core.entities.MessageTemplate;
import com.hpicorp.core.entities.MessageTemplateAction;
import com.hpicorp.core.entities.MessageText;
import com.hpicorp.core.entities.MessageVideo;
import com.hpicorp.core.enums.MessageTypes;
import com.hpicorp.core.exception.DaoException;
import com.hpicorp.core.repository.MessageAudioRepository;
import com.hpicorp.core.repository.MessageCarouselActionRepository;
import com.hpicorp.core.repository.MessageCarouselColumnRepository;
import com.hpicorp.core.repository.MessageCarouselTemplateRepository;
import com.hpicorp.core.repository.MessageImageMapRepository;
import com.hpicorp.core.repository.MessageImageRepository;
import com.hpicorp.core.repository.MessageImagemapActionRepository;
import com.hpicorp.core.repository.MessageLocationRepository;
import com.hpicorp.core.repository.MessageStickerRepository;
import com.hpicorp.core.repository.MessageTemplateActionRepository;
import com.hpicorp.core.repository.MessageTemplateRepository;
import com.hpicorp.core.repository.MessageTextRepository;
import com.hpicorp.core.repository.MessageVideoRepository;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.message.AudioMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.VideoMessage;
import com.linecorp.bot.model.message.imagemap.ImagemapAction;
import com.linecorp.bot.model.message.template.CarouselColumn;

@Repository
public class MsgDao {
	
	@Autowired
	private MessageToJsonUtil jsonMsgConverterService;
	
	@Autowired
	private MessageTextRepository messageTextRepository;
	
	@Autowired
	private MessageStickerRepository messageStickerRepository;
	
	@Autowired
	private MessageImageRepository messageImageRepository;
	
	@Autowired
	private MessageVideoRepository messageVideoRepository;
	
	@Autowired
	private MessageAudioRepository messageAudioRepository;
	
	@Autowired
	private MessageLocationRepository messageLocationRepository;
	
	@Autowired
	private MessageTemplateRepository messageTemplateRepository;
	
	@Autowired
	private MessageTemplateActionRepository messageTemplateActionRepository;
	
	@Autowired
	private MessageCarouselTemplateRepository messageCarouselTemplateRepository;
	
	@Autowired
	private MessageCarouselColumnRepository messageCarouselColumnRepository;
	
	@Autowired
	private MessageCarouselActionRepository messageCarouselActionRepository;
	
	@Autowired
	private MessageImageMapRepository messageImagemapRepository;
	
	@Autowired
	private MessageImagemapActionRepository messageImagemapActionRepository;

	public Message getMessageByTypeAndID(String type, Long id) throws DaoException {
		try {
			Message msg = null;
			if (type.equals(MessageTypes.TEXT.toString())) {
				MessageText text = OptionalExtension.get(this.messageTextRepository.findById(id));
				msg = new TextMessage(text.getText()); 
			} else if (type.equals(MessageTypes.IMAGE.toString())) {
				MessageImage image = OptionalExtension.get(this.messageImageRepository.findById(id));
				msg = new ImageMessage(image.getOriginalContentUrl(), image.getPreviewImageUrl());
			} else if (type.equals(MessageTypes.AUDIO.toString())) {
				MessageAudio audio = OptionalExtension.get(this.messageAudioRepository.findById(id));
				msg = new AudioMessage(audio.getOriginalContentUrl(), audio.getDuration());
			} else if (type.equals(MessageTypes.VIDEO.toString())) {
				MessageVideo video = OptionalExtension.get(this.messageVideoRepository.findById(id));
				msg = new VideoMessage(video.getOriginalContentUrl(), video.getPreviewImageUrl());
			} else if (type.equals(MessageTypes.STICKER.toString())) {
				MessageSticker sticker = OptionalExtension.get(this.messageStickerRepository.findById(id));
				msg = new StickerMessage(sticker.getPackageId(), sticker.getStickerId());
			} else if (type.equals(MessageTypes.LOCATION.toString())) {
				MessageLocation location = OptionalExtension.get(this.messageLocationRepository.findById(id));
				msg = new LocationMessage(location.getTitle(), location.getAddress(), location.getLatitude(), location.getLongitude());
			} else if (type.equals(MessageTypes.TEMPLATE.toString())) {
				MessageTemplate content = OptionalExtension.get(this.messageTemplateRepository.findById(id));
				msg = this.getTemplateByMsg(content);
			} else if (type.equals(MessageTypes.CAROUSEL.toString())) {
				MessageCarouselTemplate content = OptionalExtension.get(this.messageCarouselTemplateRepository.findById(id));
				msg = this.getCarouselByMsg(content);
			} else if (type.equals(MessageTypes.IMAGEMAP.toString())) {
				MessageImageMap content = OptionalExtension.get(this.messageImagemapRepository.findById(id));
				msg = this.getImagemapMsg(content);
			}
			return OptionalExtension.get(this.jsonMsgConverterService.convertBy(msg));
		} catch (NoSuchElementException elementException) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	private Message getImagemapMsg(MessageImageMap m) {
		if (m == null) {
			return null;
		}
		List<MessageImageMapAction> actions = this.messageImagemapActionRepository.findAllByMessageId(m.getId());
		List<ImagemapAction> resAction = TemplateProducer.imagemapAction(actions);
		return TemplateProducer.imagemap(m, resAction);
	}

	private Message getCarouselByMsg(MessageCarouselTemplate t) {
		if (t == null) {
			return null;
		}
		List<CarouselColumn> resultColumn = new ArrayList<>();
		List<MessageCarouselColumn> columns = this.messageCarouselColumnRepository.findAllByCarouselId(t.getId());
		for (MessageCarouselColumn col : columns) {
			List<MessageCarouselAction> messageCarouselActions = this.messageCarouselActionRepository.findByTemplateIdAndTemplateType(col.getId(), col.getType());
			List<Action> actions = TemplateProducer.carouselAction(messageCarouselActions);
			CarouselColumn tempC = TemplateProducer.carouselColumn(col, actions);
			if (tempC != null) {
				resultColumn.add(tempC);
			}
		}
		return TemplateProducer.carouselTemplate(t, resultColumn);
	}

	private Message getTemplateByMsg(MessageTemplate t) {
		if (t == null) {
			return null;
		}
		List<MessageTemplateAction> msgActions = this.messageTemplateActionRepository.findByTemplateIdAndTemplateType(t.getId(), t.getType());
		List<Action> actions = TemplateProducer.action(msgActions);
		return TemplateProducer.template(t, actions);
	}
	
}
