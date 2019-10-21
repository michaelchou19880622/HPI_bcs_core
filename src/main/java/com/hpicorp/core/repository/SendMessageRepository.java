package com.hpicorp.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hpicorp.core.entities.SendMessage;

@Repository
public interface SendMessageRepository extends PagingAndSortingRepository<SendMessage, Long> {

	public List<SendMessage> findAllByStatus(Integer status);

	public SendMessage findOneByIdAndStatus(Long id, Integer status);

	public SendMessage findOneById(Long id);

	@Transactional
	@Modifying
	@Query(value = "update SendMessage s set s.status = :status where s.id = :id")
	public void setStatusById(@Param("id") Long id, @Param("status") Integer status);

	@Transactional
	@Query(value = "select * from send_message where mode in :mode ", nativeQuery = true)
	public Page<SendMessage> getSendMessageByMode(Pageable pageable, @Param("mode") List<String> mode);

	@Transactional
	@Query(value = "select * from send_message where status = :status ", nativeQuery = true)
	public Page<SendMessage> getSendMessageByStatus(Pageable pageable, @Param("status") int status);

}
