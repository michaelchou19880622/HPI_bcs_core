package com.hpicorp.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.hpicorp.core.entities.BcsSendGroupDetail;

public interface BcsSendGroupDetailRepository extends JpaRepository<BcsSendGroupDetail, Long> {

	@Transactional(readOnly = true, timeout = 30)
	public List<BcsSendGroupDetail> findBySendGroupGroupId(Long groupId);
}
