package com.hpicorp.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hpicorp.core.entities.LineBotChannelMessageInfo;


public interface LineBotChannelMessageInfoRepository extends JpaRepository<LineBotChannelMessageInfo, Long> {

	Page<LineBotChannelMessageInfo> findByLineBotChannelInfoId(Pageable pageable, Long lineMessageInfoId);
	
	public Optional<LineBotChannelMessageInfo> findByIdAndLineBotChannelInfoId(Long id, Long lineMessageInfoId);
	
	@Query(value = "SELECT L1.* FROM LINE_BOT_CHANNEL_MESSAGE_INFO L1 "
			 	 + "WHERE L1.SEND_TYPE = 'schedule_date' "
			 	 + "AND L1.SEND_TYPE = 'schedule_cron' "
			 	 + "AND L1.SCHEDULE_TIME > now()", nativeQuery = true)
	public List<LineBotChannelMessageInfo> findUnSendScheduleMessage();

}
