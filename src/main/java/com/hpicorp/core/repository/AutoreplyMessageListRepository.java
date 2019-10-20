package com.hpicorp.core.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hpicorp.core.entities.AutoreplyMessageList;

public interface AutoreplyMessageListRepository extends CrudRepository<AutoreplyMessageList, Long> {

	@Query(value = "select distinct L "
				 + "from AutoreplyMessageList L "
				 + "inner join Autoreply a on a.id = L.autoreplyId "
				 + "where a.status = 'ACTIVE' "
				 + "and a.userStatus = 'ALL' "
				 + "and (a.period = 'FOREVER' or (a.period = 'DAY' and a.datetimeBegin <= :date and a.datetimeEnd >= :date )) "
				 + "and a.keyword = :keyword "
				 + "and a.type = :type "
				 + "order by L.orderNum")
	public List<AutoreplyMessageList> findListByKeyWordAndType(@Param("keyword") String keyword, @Param("type") String type, @Param("date") Date date, Pageable pageable);

	@Query(value = "select distinct L "
				 + "from AutoreplyMessageList L "
				 + "inner join Autoreply a on a.id = L.autoreplyId "
				 + "and a.status = 'ACTIVE' "
				 + "and a.userStatus = 'ALL' "
				 + "and (a.period = 'FOREVER' or (a.period = 'DAY' and a.datetimeBegin <= :date and a.datetimeEnd >= :date )) "
				 + "inner join AutoreplyDetail d on d.autoreplyId = L.autoreplyId "
				 + "and d.keyword = :keyword "
				 + "order by L.orderNum")
	public Page<AutoreplyMessageList> getListByKeyWord(@Param("keyword") String keyword, @Param("date") Date date, Pageable pageable);

	@Query(value = "select distinct L "
				 + "from AutoreplyMessageList L "
				 + "inner join Autoreply a on a.id = L.autoreplyId "
				 + "inner join AutoreplyDetail d on d.autoreplyId = L.autoreplyId "
				 + "where d.keyword = :keyword "
				 + "and a.status = 'ACTIVE' "
				 + "and a.userStatus in :status "
				 + "and (a.period = 'FOREVER' or (a.period = 'DAY' and a.datetimeBegin <= :date and a.datetimeEnd >= :date )) "
				 + "order by L.orderNum")
	public Page<AutoreplyMessageList> getListByKeyWordAndDateAndStatus(@Param("keyword") String keyword, @Param("date") Date date, @Param("status") List<String> status, Pageable pageable);
}
