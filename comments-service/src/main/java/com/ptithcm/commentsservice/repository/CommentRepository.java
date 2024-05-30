package com.ptithcm.commentsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptithcm.commentsservice.entity.CommentsEntity;
import java.util.List;


public interface CommentRepository extends JpaRepository<CommentsEntity, Long>{
	List<CommentsEntity> findByNewsId(Long newsId);
	List<CommentsEntity> findByUserIdAndNewsId(Long userId, Long newsId);
	List<CommentsEntity> findByUserId(Long userId);
}
