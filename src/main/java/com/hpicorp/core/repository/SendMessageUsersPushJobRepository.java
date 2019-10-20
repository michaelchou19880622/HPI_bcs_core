package com.hpicorp.core.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hpicorp.core.entities.SendMessageUsersPushJob;

public interface SendMessageUsersPushJobRepository extends JpaRepository<SendMessageUsersPushJob, Long> {

	public SendMessageUsersPushJob findBySendId(Long sendId);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query(value = "select P from SendMessageUsersPushJob P where P.sendId = :sendId")
	public SendMessageUsersPushJob getBySendId(@Param("sendId") Long sendId);
	
}
