package com.hpicorp.core.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "line_bot_channel_message_info", catalog = "")
public class LineBotChannelMessageInfo implements Serializable {

	private static final long serialVersionUID = -1927562397281739835L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Basic
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Basic
	@Column(name = "event_type", nullable = false, length = 50)
	private String eventType;

	@Basic
	@Column(name = "msg_type", nullable = false, length = 50)
	private String msgType;

	@Basic
	@Column(name = "group_id", nullable = true, length = 200)
	private Long groupId;

	@Basic
	@Column(name = "schedule_time", nullable = true)
	private Date scheduleTime;

	@Basic
	@Column(name = "schedule_cron", nullable = true)
	private String scheduleCron;

	@Basic
	@Column(name = "send_type", nullable = false, length = 50)
	private String sendType;

	@Basic
	@Column(name = "status", nullable = false)
	private Integer status;

	@Basic
	@Column(name = "msg_tag", nullable = true, length = 255)
	private String msgTag;

	@Basic
	@Column(name = "msg_object", nullable = false, columnDefinition = "text")
	private String msgObject;

	@Basic
	@Column(name = "creator_id", nullable = true)
	private Long creatorId;

	@Basic
	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Basic
	@Column(name = "updater_id", nullable = true)
	private Long updaterId;

	@Basic
	@UpdateTimestamp
	@Column(name = "update_time", nullable = false)
	private Date updateTime;
	
	@Transient
	private String creatorName;
	
	@Transient
	private String updatorName;
	
	@Transient
	private List<Object> messageList;

}
