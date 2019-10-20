package com.hpicorp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hpicorp.core.entities.MessageVideo;

@Repository
public interface MessageVideoRepository extends JpaRepository<MessageVideo, Long> {

	public boolean existsById(Long messageId);

}
