package com.hpicorp.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hpicorp.core.entities.MessageCarouselTemplate;

@Repository
public interface MessageCarouselTemplateRepository extends JpaRepository<MessageCarouselTemplate, Long> {

	public List<MessageCarouselTemplate> findByModifyUser(String modifyUser);
	
}
