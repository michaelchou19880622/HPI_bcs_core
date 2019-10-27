package com.hpicorp.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hpicorp.core.entities.MessageObjectTemplate;

@Repository
public interface MessageObjectTemplateRepository extends JpaRepository<MessageObjectTemplate, Long> {
	
	Page<MessageObjectTemplate> findByTypeIn(List<String> typeList, Pageable pageable);
}
