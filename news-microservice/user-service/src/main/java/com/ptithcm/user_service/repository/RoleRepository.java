package com.ptithcm.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptithcm.user_service.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
