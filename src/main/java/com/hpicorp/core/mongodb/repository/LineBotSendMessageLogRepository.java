package com.hpicorp.core.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hpicorp.core.entities.LineBotSendMessageLog;


public interface LineBotSendMessageLogRepository extends MongoRepository<LineBotSendMessageLog, String> {

	public List<LineBotSendMessageLog> findByLineBotChannelInfoId(Long lineBotChannelId);
	
}
