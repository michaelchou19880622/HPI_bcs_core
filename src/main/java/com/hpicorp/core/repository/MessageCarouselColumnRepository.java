package com.hpicorp.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hpicorp.core.entities.MessageCarouselColumn;

@Repository
public interface MessageCarouselColumnRepository extends JpaRepository<MessageCarouselColumn, Long> {

	public List<MessageCarouselColumn> findAllByCarouselId(Long carouselId);
	
}
