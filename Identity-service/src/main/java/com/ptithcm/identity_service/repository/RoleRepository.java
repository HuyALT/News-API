package com.ptithcm.identity_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptithcm.identity_service.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
