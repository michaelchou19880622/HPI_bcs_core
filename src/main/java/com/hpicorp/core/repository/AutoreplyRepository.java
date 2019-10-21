package com.hpicorp.core.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hpicorp.core.entities.Autoreply;

public interface AutoreplyRepository extends JpaRepository<Autoreply, Long> {
	
	public Autoreply findByKeywordAndType(String keyword, String type);
	
	public List<Autoreply> findByModifyUser(String modifyUser);
	
	@Transactional
	@Query(value = "select A from Autoreply A where A.type <> 'DEFAULT' and A.period = 'DAY' and A.datetimeBegin > :today and A.datetimeEnd > :today")
	public Page<Autoreply> getAutoreplyByInActivePeriod(Pageable pageable, @Param("today") Date today);

	@Transactional
	@Query(value = "select A from Autoreply A where A.type <> 'DEFAULT' and A.period = 'DAY' and A.datetimeBegin < :today and A.datetimeEnd < :today")
	public Page<Autoreply> getAutoreplyByExpiredPeriod(Pageable pageable, @Param("today") Date today);

	@Transactional
	@Query(value = "select A from Autoreply A where A.type <> 'DEFAULT' and ( A.period = 'FOREVER' ) or (A.period = 'DAY' and A.datetimeBegin <= :today and A.datetimeEnd >= :today )")
	public Page<Autoreply> getAutoreplyByActivePeriod(Pageable pageable, @Param("today") Date today);

	@Transactional
	@Query(value = "select A from Autoreply A where A.type <> 'DEFAULT' ")
	public Page<Autoreply> getAutoreplyListWithoutDefault(Pageable pageable);

	@Query(value = "select A.id, A.user_status " 
				 + "from autoreply_detail D " 
				 + "inner join autoreply A on A.id = D.autoreply_id " 
				 + "where D.keyword = :keyword " 
				 + "and A.status = 'ACTIVE' " 
				 + "and (A.period = 'FOREVER' or (A.period = 'DAY' and A.datetime_begin <= :date and A.datetime_end >= :date )) ", nativeQuery = true)
	public Map<String, Object> findIdAndStatus(@Param("keyword")String keyword, @Param("date") Date date);
	
}
