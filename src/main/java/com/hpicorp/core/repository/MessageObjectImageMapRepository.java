package com.hpicorp.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hpicorp.core.entities.MessageObjectImageMap;

@Repository
public interface MessageObjectImageMapRepository extends JpaRepository<MessageObjectImageMap, Long> {
	

}
