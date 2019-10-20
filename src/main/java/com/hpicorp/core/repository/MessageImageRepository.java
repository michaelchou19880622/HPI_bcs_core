package com.hpicorp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hpicorp.core.entities.MessageImage;

@Repository
public interface MessageImageRepository extends JpaRepository<MessageImage, Long> {

	public boolean existsById(Long messageId);

}
