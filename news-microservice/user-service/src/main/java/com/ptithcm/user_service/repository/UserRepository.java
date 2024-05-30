package com.ptithcm.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptithcm.user_service.entity.UserEntity;
import java.util.List;
import com.ptithcm.user_service.entity.RoleEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long>{
	List<UserEntity> findByRole(RoleEntity role);
}
