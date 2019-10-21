package com.hpicorp.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hpicorp.core.entities.SystemUserRole;

public interface SystemUserRoleRepository extends JpaRepository<SystemUserRole, String> {

	Optional<SystemUserRole> findByName(String name);

}
