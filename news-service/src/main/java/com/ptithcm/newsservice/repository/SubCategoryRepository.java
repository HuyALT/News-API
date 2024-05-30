package com.ptithcm.newsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptithcm.newsservice.entity.CategoryEntity;
import com.ptithcm.newsservice.entity.SubCategoryEntity;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
	List<SubCategoryEntity> findByCategory(CategoryEntity category);
}
