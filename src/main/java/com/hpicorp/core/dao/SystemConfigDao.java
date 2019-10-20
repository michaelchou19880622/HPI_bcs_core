package com.hpicorp.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hpicorp.core.common.PageExtension;
import com.hpicorp.core.entities.SystemConfig;
import com.hpicorp.core.repository.SystemConfigRepository;

@Service
@Component
public class SystemConfigDao {

	@Autowired
	private SystemConfigRepository repository;
	
	public String getValueByKey(String key) {
		Pageable pageable = PageRequest.of(0, 1);
		Page<String> pages = this.repository.findValueByKey(key, pageable);
		return PageExtension.getFirstFromPage(pages);
	}
	
	public SystemConfig getByKey(String key) {
		return this.repository.findOneByConfigKey(key);
	}
	
	public void save(SystemConfig entity) {
		this.repository.save(entity);
	}
}
