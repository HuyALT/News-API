package com.ptithcm.newsservice.Iservice;

import java.util.List;

import com.ptithcm.newsservice.dto.request.CategoryRequestDTO;
import com.ptithcm.newsservice.dto.response.CategoryResponseDTO;

public interface ICategoryService {
	CategoryResponseDTO getCategoryById(Long id);
	List<CategoryResponseDTO> getAllCategory();
	
	CategoryResponseDTO addCategory(CategoryRequestDTO request);
	CategoryResponseDTO updateCategory(CategoryRequestDTO request, Long id);
	boolean deleteCategory(Long id);
}
