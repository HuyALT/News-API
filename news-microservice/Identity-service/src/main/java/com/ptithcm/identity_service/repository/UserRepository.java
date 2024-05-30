package com.ptithcm.identity_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptithcm.identity_service.entity.UserEntity;
import java.util.Optional;



public interface UserRepository extends JpaRepository<UserEntity, Long>  {
	Optional<UserEntity> findByUsername(String username);
	Optional<UserEntity> findByEmail(String email);
}
