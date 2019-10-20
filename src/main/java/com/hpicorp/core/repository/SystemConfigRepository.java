package com.hpicorp.core.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hpicorp.core.entities.SystemConfig;

public interface SystemConfigRepository extends JpaRepository<SystemConfig, Long> {

	public Optional<SystemConfig> findByConfigKey(String configKey);

	public SystemConfig findOneByConfigKeyAndModifyTime(String key, Date modifyTime);

	public SystemConfig findOneByConfigKey(String configKey);

	@Query(value = "select S.configValue from SystemConfig S where S.configKey = :key ")
	public Page<String> findValueByKey(@Param("key") String key, Pageable pageable);

}
