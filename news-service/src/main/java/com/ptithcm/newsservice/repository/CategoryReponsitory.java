package com.ptithcm.newsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptithcm.newsservice.entity.CategoryEntity;

public interface CategoryReponsitory extends JpaRepository<CategoryEntity, Long> {

}
