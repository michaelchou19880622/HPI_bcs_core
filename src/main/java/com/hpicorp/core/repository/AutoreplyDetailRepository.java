package com.hpicorp.core.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hpicorp.core.entities.AutoreplyDetail;

public interface AutoreplyDetailRepository extends JpaRepository<AutoreplyDetail, Long> {

	@Query(value = "select distinct D.autoreplyId from AutoreplyDetail D "
				 + "inner join Autoreply A on A.id = D.autoreplyId "
				 + "		and D.keyword = :keyword "
				 + "		and A.status = 'ACTIVE' and A.type = 'KEYWORD' "
				 + "		and (A.period = 'FOREVER' or (A.period = 'DAY' and A.datetimeBegin <= :date and A.datetimeEnd >= :date )) ")
	public Page<Long> findOneAutoreplyIDByKeyword(@Param("keyword")String keyword, @Param("date") Date date, Pageable pageable);
	
	@Modifying
	@Transactional
	@Query(value = "delete from AutoreplyDetail A where A.autoreplyId = :autoreply_id ")
	public void deleteByAutoreplyID(@Param("autoreply_id") Long autoreplyIid);
	
}
