package com.hpicorp.core.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hpicorp.core.entities.SendMessageUsers;

@Repository
public interface SendMessageUsersRepository extends JpaRepository<SendMessageUsers, Long> {

	@Modifying
	@Transactional
	@Query(value = "delete from send_message_users where send_id = :sendId ", nativeQuery = true)
	public void deleteBySendID(@Param("sendId") long sendId);

	@Query(value = " :sqlcmd ", nativeQuery = true)
	public void insertSendMessageUsers(@Param("sqlcmd") String sqlcmd);

	@Query(value = "select U.id from SendMessageUsers U where U.sendId = :sendId order by U.id desc")
	public List<Long> findLastIndexBySendIdOrderByIdDesc(@Param("sendId") Long sendId, Pageable pageable);

	@Query(value = "select U from SendMessageUsers U WHERE U.id > :id AND U.sendId = :sendId")
	public List<SendMessageUsers> findUserIdBySendIdAndStatus(@Param("id") Long id, @Param("sendId") Long sendId, Pageable pageable);

	@Query(value = "select distinct U from SendMessageUsers U where U.sendId = :sendId")
	public Page<SendMessageUsers> findBySendId(@Param("sendId") Long sendId, Pageable pageable);

	@Query(value = "select U.lineUserId from SendMessageUsers U where U.sendId = :sendId")
	public List<Long> findUserIdBySendId(@Param("sendId") Long sendId, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "update SendMessageUsers U set U.status = :status, U.responseCode = :responseCode, U.sendTime = :time where U.id in :ids")
	public void setStatusAndResponseCodeByIds(@Param("ids") List<Long> ids, 
											 @Param("status") Integer status, 
											 @Param("responseCode") Integer responseCode, 
											 @Param("time") ZonedDateTime time);

	@Modifying
	@Query(value = "update SendMessageUsers U set U.status = :status, U.responseCode = :responseCode, U.sendTime = :time where U IN (:entities)")
	public void setStatusAndResponseCodeBy(@Param("entities") List<SendMessageUsers> entities,
										  @Param("status") Integer status, 
										  @Param("responseCode") Integer responseCode,
										  @Param("time") ZonedDateTime time);

	@Modifying
	@Query(value = "update SendMessageUsers U set U.status = :status, U.responseCode = :responseCode, U.sendTime = :time where U.id >= :min AND U.id <= :max")
	public void setStatusAndResponseCodeById(@Param("min") Long min, 
											@Param("max") Long max,
											@Param("status") Integer status, 
											@Param("responseCode") Integer responseCode,
											@Param("time") ZonedDateTime time);

}
