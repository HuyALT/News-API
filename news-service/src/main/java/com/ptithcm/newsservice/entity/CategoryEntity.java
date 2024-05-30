package com.ptithcm.newsservice.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private String name;

	 private String sortName;

	 @CreationTimestamp
	 @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP")
	 private LocalDateTime createdAt;

	 @UpdateTimestamp
	 @Column(name = "update_at",columnDefinition = "TIMESTAMP")
	 private LocalDateTime updatedAt;

	 @OneToMany(mappedBy = "category")
	 private List<NewsEntity> news;

	public CategoryEntity() {
		super();
	}

	public CategoryEntity(Long id, String name, String sortName, LocalDateTime createdAt, LocalDateTime updatedAt,
			List<NewsEntity> news) {
		super();
		this.id = id;
		this.name = name;
		this.sortName = sortName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.news = news;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<NewsEntity> getNews() {
		return news;
	}

	public void setNews(List<NewsEntity> news) {
		this.news = news;
	}
}
