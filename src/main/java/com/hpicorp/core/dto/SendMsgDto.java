package com.hpicorp.core.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hpicorp.core.config.SendPushMsgTypeConfig;
import com.linecorp.bot.model.message.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SendMsgDto extends RequestDto {
	
	private Long msgId;
	
	private String name;
	
	private Long groupId;
	
	private String sendType;
	
	private String scheduleCron;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date scheduleTime;
	
	private SendPushMsgTypeConfig sendPushMsgType;

	private List<String> toList;

	private List<Set<String>> toMulticastList;

	private List<Message> messages;

	public boolean checkValid() {
		if (super.getChannelId() == null || super.getChannelId().trim().isEmpty()) return false;
		if (super.getChannelAccessToken() == null || super.getChannelAccessToken().trim().isEmpty()) return false;
		if (this.sendPushMsgType == null) return false;
		if (this.sendPushMsgType.equals(SendPushMsgTypeConfig.PUSH) && (toList == null || toList.isEmpty())) return false;
		if (this.sendPushMsgType.equals(SendPushMsgTypeConfig.MULTICAST)
				&& (toMulticastList == null || toMulticastList.isEmpty())) return false;
		if (this.messages == null || this.messages.isEmpty()) return false;
		if (this.messages.size() > 5) return false;
		return true;
	}

}
