package com.hpicorp.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hpicorp.core.entities.MessageCarouselAction;

@Repository
public interface MessageCarouselActionRepository extends JpaRepository<MessageCarouselAction, Long> {

	@Query(value = "select distinct M from MessageCarouselAction M " 
			     + "where M.columnId = :templateId " 
			     + "and M.templateType = :templateType")
	public List<MessageCarouselAction> findByTemplateIdAndTemplateType(@Param("templateId") Long templateId, @Param("templateType") String templateType);
	
}
