package com.hpicorp.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hpicorp.core.entities.BcsSystemConfig;

public interface BcsSystemConfigRepository extends JpaRepository<BcsSystemConfig, Long> {

	public Optional<BcsSystemConfig> findByConfigId(String configId);
}
