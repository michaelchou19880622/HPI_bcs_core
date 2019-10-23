package com.hpicorp.core.entities;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "line_bot_log_send_message")
public class LineBotSendMessageLog {

	@Id
	private String id;
	
	@Field("group_id")
	private String groupId;
	
	@Field("line_uid")
	private String lineUid;
	
	@Field("schedule_time")
    private Date scheduleTime;
    
	@Field("schedule_cron")
    private String scheduleCron;
	
	@Field("msg_id")
	private Long msgId;
	
	@Field("send_type")
	private String sendType;
	
	@Field("name")
	private String name;
	
	@Field("status")
	private Integer status;
	
	@Field("msg_object")
	private String msgObject;
	
	@Field("create_time")
	private Date createTime;

	@Field("update_time")
	private Date updateTime;
	
}
