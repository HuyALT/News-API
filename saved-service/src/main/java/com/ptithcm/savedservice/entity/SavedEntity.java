package com.ptithcm.savedservice.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Saved")
public class SavedEntity {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @Column(name = "user_id", nullable = false)
	 private Long userId;

	 @Column(name = "news_id", nullable = false)
	 private Long newsId;
	 
	 @CreationTimestamp
	 @Column(name = "created_at", nullable = false)
	 private LocalDateTime createdAt;


	 public SavedEntity() {}


	public SavedEntity(Long id, Long userId, Long newsId, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.newsId = newsId;
		this.createdAt = createdAt;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getNewsId() {
		return newsId;
	}


	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	 
	 
	 
	 
}
