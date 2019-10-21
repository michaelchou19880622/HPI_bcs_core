package com.hpicorp.core.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hpicorp.core.entities.LineUser;

public interface LineUserRepository extends JpaRepository<LineUser, Long> {

	public List<LineUser> findByIdIn(List<Long> ids);
	
	public Optional<LineUser> findByLineUid(String lineUid);

	@Query(value = "select distinct L.id from LineUser L where L.lineUid = :uid ")
	public Long getIdByUid(@Param("uid") String uid);
	
	@Query(value = "select U.id as tarKey, U.lineUid as tarValue from LineUser U where U.id in :ids")
	public List<Map<String, Object>> findIdAndUidByIds(@Param("ids") List<Long> ids);

	@Query(value = "select distinct U.lineUid from LineUser U where U.id in :ids")
	public List<String> findUidByIdIn(@Param("ids") List<Long> ids);

	@Query(value = "select distinct U.lineUid from LineUser U where U.id = :id")
	public String findUidById(@Param("id") Long id);

	@Query(value = "select U.id from LineUser U where U.lineUid = :uid")
	public List<Long> findIdByUid(@Param("uid") String uid, Pageable pageable);

	@Query(value = "select U.status from LineUser U where U.lineUid = :uid")
	public String findStatusByUid(@Param("uid") String uid);
	
}
