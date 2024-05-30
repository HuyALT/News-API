package com.ptithcm.savedservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptithcm.savedservice.entity.SavedEntity;
import java.util.List;
import java.util.Optional;


public interface SavedRepository extends JpaRepository<SavedEntity, Long> {
	List<SavedEntity> findByUserIdOrderByCreatedAtDesc(Long userId);
	Optional<SavedEntity> findByUserIdAndNewsId(Long userId, Long newsId);
	
}
