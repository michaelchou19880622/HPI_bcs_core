package com.hpicorp.core.mongodb.entities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hpicorp.core.entities.LineBotSendMessageLog;
import com.hpicorp.core.mongodb.repository.LineBotSendMessageLogRepository;



@Component
public class LineBotSendMessageLogDao {

	@Autowired
	private LineBotSendMessageLogRepository lineBotSendMessageLogRepository;
	
	public List<LineBotSendMessageLog> findByLineBotChannelInfoId(Long lineBotChannelId) {
		return this.lineBotSendMessageLogRepository.findByLineBotChannelInfoId(lineBotChannelId);
	}
	
	public void save(LineBotSendMessageLog lineBotSendMessageLogList) {
		this.lineBotSendMessageLogRepository.save(lineBotSendMessageLogList);
	}
	
	public void saveAll(List<LineBotSendMessageLog> lineBotSendMessageLogList) {
		this.lineBotSendMessageLogRepository.saveAll(lineBotSendMessageLogList);
	}
	
}
