package com.ptithcm.newsservice.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ptithcm.newsservice.entity.CategoryEntity;
import com.ptithcm.newsservice.entity.NewsEntity;
import com.ptithcm.newsservice.entity.SubCategoryEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	
	@Query("SELECT n FROM NewsEntity n WHERE n.category = :category AND (:type IS NULL OR n.type = :type)")
	Page<NewsEntity> findNewsByCategory(CategoryEntity category, Pageable pageable, Integer type);
	@Query("SELECT COUNT(n) FROM NewsEntity n WHERE n.category = :category AND (:type IS NULL OR n.type = :type)")
	int countNewsByCategory(CategoryEntity category,  Integer type);
	
	@Query("SELECT n FROM NewsEntity n WHERE n.subcategory = :subcategory AND (:type IS NULL OR n.type = :type)")
	Page<NewsEntity> findNewsBySubcategory(SubCategoryEntity subcategory, Pageable pageable, Integer type);
	@Query("SELECT COUNT(n) FROM NewsEntity n WHERE n.subcategory = :subcategory AND (:type IS NULL OR n.type = :type)")
	int countNewsBySubcategory(SubCategoryEntity subcategory, Integer type);
	
	@Query("SELECT n FROM NewsEntity n WHERE n.active = 1 AND n.category = :category AND (:type IS NULL OR n.type = :type)")
	Page<NewsEntity> findAllActiveNewsByCategory(CategoryEntity category, Pageable pageable, Integer type);
	@Query("SELECT COUNT(n) FROM NewsEntity n WHERE n.active = 1 AND n.category = :category AND (:type IS NULL OR n.type = :type)")
	int countAllActiveNewsByCategory(CategoryEntity category, Integer type);
	 
	@Query("SELECT n FROM NewsEntity n WHERE n.active = 1 AND n.subcategory = :subcategory AND (:type IS NULL OR n.type = :type)")
	Page<NewsEntity> findAllActiveNewsBySubCategory(SubCategoryEntity subcategory, Pageable pageable, Integer type);
	@Query("SELECT COUNT(n) FROM NewsEntity n WHERE n.active = 1 AND n.subcategory = :subcategory AND (:type IS NULL OR n.type = :type)")
	int countAllActiveNewsBySubCategory(SubCategoryEntity subcategory, Integer type);
	 
	@Query("SELECT n FROM NewsEntity n WHERE n.active = 1 AND (:type IS NULL OR n.type = :type)")
	Page<NewsEntity> findAllActiveNews(Pageable pageable, Integer type);
	@Query("SELECT COUNT(n) FROM NewsEntity n WHERE n.active = 1 AND (:type IS NULL OR n.type = :type)")
	int countAllActiveNews(Integer type);
	
	@Query("SELECT n FROM NewsEntity n WHERE n.active = 1 AND " +
	         "(LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	         "LOWER(n.summary) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	Page<NewsEntity> searchByKeyword(String keyword, Pageable pageable);
	
    @Query("SELECT COUNT(n) FROM NewsEntity n WHERE n.active = 1 AND " +
            "(LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(n.summary) LIKE LOWER(CONCAT('%', :keyword, '%')))")
     int countSearchByKeyword(String keyword);
}
