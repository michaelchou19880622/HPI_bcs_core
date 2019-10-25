package com.hpicorp.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hpicorp.core.entities.LineBotChannelMessageInfo;


public interface LineBotChannelMessageInfoRepository extends JpaRepository<LineBotChannelMessageInfo, Long> {

//	Page<LineBotChannelMessageInfo> findByLineBotChannelInfoId(Pageable pageable, Long lineMessageInfoId);
	
//	public Optional<LineBotChannelMessageInfo> findByIdAndLineBotChannelInfoId(Long id, Long lineMessageInfoId);
	
	@Query(value = "select L1.* from line_bot_channel_message_info L1 "
			 	 + "where L1.send_type = 'schedule_date' "
			 	 + "and L1.send_type = 'schedule_cron' "
			 	 + "and L1.schedule_time > now()", nativeQuery = true)
	public List<LineBotChannelMessageInfo> findUnSendScheduleMessage();

}
