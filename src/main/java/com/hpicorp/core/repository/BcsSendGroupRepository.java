package com.hpicorp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hpicorp.core.entities.BcsSendGroup;

public interface BcsSendGroupRepository extends JpaRepository<BcsSendGroup, Long> {

	@Transactional(readOnly = true, timeout = 30)
	@Query(value = "SELECT bsg.GROUP_TYPE FROM BCS_SEND_GROUP bsg WHERE GROUP_ID = :groupId", nativeQuery = true)
	public String findGroupTypeByGroupId(@Param("groupId") Long groupId);
}
