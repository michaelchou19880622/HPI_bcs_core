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
@Table(name = "LINE_BOT_CHANNEL_MESSAGE_INFO", catalog = "")
public class LineBotChannelMessageInfo implements Serializable {

	private static final long serialVersionUID = -1927562397281739835L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Basic
	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Basic
	@Column(name = "EVENT_TYPE", nullable = false, length = 50)
	private String eventType;

	@Basic
	@Column(name = "MSG_TYPE", nullable = false, length = 50)
	private String msgType;

	@Basic
	@Column(name = "GROUP_ID", nullable = true, length = 200)
	private Long groupId;

	@Basic
	@Column(name = "SCHEDULE_TIME", nullable = true)
	private Date scheduleTime;

	@Basic
	@Column(name = "SCHEDULE_CRON", nullable = true)
	private String scheduleCron;

	@Basic
	@Column(name = "SEND_TYPE", nullable = false, length = 50)
	private String sendType;

	@Basic
	@Column(name = "STATUS", nullable = false)
	private Integer status;

	@Basic
	@Column(name = "MSG_TAG", nullable = true, length = 255)
	private String msgTag;

	@Basic
	@Column(name = "MSG_OBJECT", nullable = false, columnDefinition = "text")
	private String msgObject;

	@Basic
	@Column(name = "CREATOR_ID", nullable = true)
	private Long creatorId;

	@Basic
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	@Basic
	@Column(name = "UPDATER_ID", nullable = true)
	private Long updaterId;

	@Basic
	@UpdateTimestamp
	@Column(name = "UPDATE_TIME", nullable = false)
	private Date updateTime;
	
	@Transient
	private String creatorName;
	
	@Transient
	private String updatorName;
	
	@Transient
	private List<Object> messageList;

}
