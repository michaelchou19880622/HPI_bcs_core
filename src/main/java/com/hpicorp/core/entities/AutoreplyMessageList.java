package com.hpicorp.core.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "autoreply")
@Table(name = "autoreply_message_list")
public class AutoreplyMessageList implements Serializable {

	private static final long serialVersionUID = -3060314740953162771L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "autoreply_id", insertable = false, updatable = false)
	private Long autoreplyId;

	@Column(name = "message_id", nullable = false)
	private Long messageId;

	@Column(name = "message_type", nullable = false)
	private String messageType;

	@Column(name = "order_num", nullable = false)
	private Integer orderNum;
	
	@ManyToOne(targetEntity = Autoreply.class)
	@JoinColumn(name = "autoreply_id")
	@JsonIgnore
	private Autoreply autoreply;
	
	@Transient
	private List<MessageText> messageTextList;

	@Transient
	private List<MessageImage> messageImageList;

	@Transient
	private List<MessageVideo> messageVideoList;

	@Transient
	private List<MessageAudio> messageAudioList;

	@Transient
	private List<MessageSticker> messageStickerList;

	@Transient
	private List<MessageImageMap> messageImageMapList;

	@Transient
	private List<MessageTemplate> messageTemplateList;

}
