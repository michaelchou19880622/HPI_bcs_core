package com.hpicorp.core.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hpicorp.core.entities.LineUserGroup;

public interface LineUserGroupRepository extends JpaRepository<LineUserGroup, Long>{
	
	public Optional<LineUserGroup> findByName(String name);
	
	public Boolean existsByName(String name);
	
	public Page<LineUserGroup> findAll(Pageable pageable);
	
	@Transactional
	@Query(value = "select getusers from lineuser_group where id = :id ", nativeQuery = true)
	public String findgetUsersById(@Param("id") Long lineUserGroupId);
	
	@Modifying
	@Transactional
	@Query(value = "update lineuser_group set getusers = :getUsers where id = :id ", nativeQuery = true)
	public void updateGetUsers(@Param("id") Long groupId, @Param("getUsers") String getUsers);	
}
