package com.ptithcm.newsservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.newsservice.Iservice.ICategoryService;
import com.ptithcm.newsservice.dto.request.CategoryRequestDTO;
import com.ptithcm.newsservice.dto.response.CategoryResponseDTO;
import com.ptithcm.newsservice.entity.CategoryEntity;
import com.ptithcm.newsservice.repository.CategoryReponsitory;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryReponsitory categoryReponsitory;

	@Override
	public CategoryResponseDTO getCategoryById(Long id) {
		Optional<CategoryEntity> categoryOptional = categoryReponsitory.findById(id);
		
		return categoryOptional.isPresent()?entityToRespone(categoryOptional.get()):null;
	}

	@Override
	public List<CategoryResponseDTO> getAllCategory() {		
		return categoryReponsitory.findAll().stream()
				.map(this::entityToRespone)
				.collect(Collectors.toList());
	}

	@Override
	public CategoryResponseDTO addCategory(CategoryRequestDTO request) {
		CategoryEntity entity = requestToEntity(request);
		return entityToRespone(categoryReponsitory.save(entity));
	}

	@Override
	public CategoryResponseDTO updateCategory(CategoryRequestDTO request, Long id) {
		Optional<CategoryEntity> categoryOptional = categoryReponsitory.findById(id);
		if (categoryOptional.isPresent()) {
			if (request.getName()!=null) categoryOptional.get().setName(request.getName());
			if (request.getSortName()!=null) categoryOptional.get().setSortName(request.getSortName());
			
			return entityToRespone(categoryReponsitory.save(categoryOptional.get()));
		}
		return null;
	}
	
	
	
	@Override
	public boolean deleteCategory(Long id) {
		Optional<CategoryEntity> categoryOptional = categoryReponsitory.findById(id);
		if (categoryOptional.isPresent()) {
			try {
				categoryReponsitory.delete(categoryOptional.get());
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	private CategoryResponseDTO entityToRespone(CategoryEntity entity) {
		CategoryResponseDTO dto = new CategoryResponseDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSort_name(entity.getSortName());
		return dto;
	}
	
	private CategoryEntity requestToEntity(CategoryRequestDTO request) {
		CategoryEntity entity = new CategoryEntity();
		entity.setName(request.getName());
		entity.setSortName(request.getSortName());
		return entity;
	}

}
