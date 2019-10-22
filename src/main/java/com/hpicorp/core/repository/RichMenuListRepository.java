package com.hpicorp.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hpicorp.core.entities.RichMenuList;

public interface RichMenuListRepository extends JpaRepository<RichMenuList, Long> {

	public Optional<RichMenuList> findByRichMenuId(String richMenuId);
	
}
