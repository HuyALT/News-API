package com.ptithcm.newsservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.newsservice.Iservice.ISubCategoryService;
import com.ptithcm.newsservice.dto.request.SubCategoryRequestDTO;
import com.ptithcm.newsservice.dto.response.SubCategoryResponeDTO;
import com.ptithcm.newsservice.entity.CategoryEntity;
import com.ptithcm.newsservice.entity.SubCategoryEntity;
import com.ptithcm.newsservice.repository.CategoryReponsitory;
import com.ptithcm.newsservice.repository.SubCategoryRepository;

@Service
public class SubCategoryService implements ISubCategoryService {
	
	@Autowired
	private CategoryReponsitory categoryReponsitory;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public List<SubCategoryResponeDTO> getSubCategoryByCategory(Long categoryid) {
		Optional<CategoryEntity> categoryOptional = categoryReponsitory.findById(categoryid);
		if (categoryOptional.isEmpty()) return List.of();
		
		return subCategoryRepository.findByCategory(categoryOptional.get()).stream()
				.map(this::entityToRespone)
				.collect(Collectors.toList());
	}
	
	
	@Override
	public List<SubCategoryResponeDTO> getAllSubCategory() {
		List<SubCategoryEntity> subCategoryEntities = subCategoryRepository.findAll();
		return subCategoryEntities.stream()
				.map(this::entityToRespone)
				.collect(Collectors.toList());
	}
	
	@Override
	public SubCategoryResponeDTO getSubCategoryByID(Long id) {
		Optional<SubCategoryEntity> subCategoryOptional = subCategoryRepository.findById(id);
		if (subCategoryOptional.isPresent()) {
			return entityToRespone(subCategoryOptional.get());
		}
		return null;
	}


	@Override
	public boolean deleteSubCategory(Long id) {
		Optional<SubCategoryEntity> subCategoryOptional = subCategoryRepository.findById(id);
		if (subCategoryOptional.isPresent()) {
			try {
				subCategoryRepository.delete(subCategoryOptional.get());
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}


	@Override
	public SubCategoryResponeDTO addSubCategory(SubCategoryRequestDTO request) {
		SubCategoryEntity entity = requestToEntity(request);
		if (entity==null) return null;
		return entityToRespone(subCategoryRepository.save(entity));
	}



	@Override
	public SubCategoryResponeDTO updateSubCategory(SubCategoryRequestDTO request, Long id) {
		Optional<SubCategoryEntity> entity = subCategoryRepository.findById(id);
		if (entity.isEmpty()) return null;
		if (request.getCategoryid()!=null) {
			Optional<CategoryEntity> categoryOptional = categoryReponsitory.findById(request.getCategoryid());
			if (categoryOptional.isPresent()) {
				entity.get().setCategory(categoryOptional.get());
			}
		}
		
		if (request.getName()!=null) entity.get().setName(request.getName());
		if (request.getSortName()!=null) entity.get().setSortName(request.getSortName());
		return entityToRespone(subCategoryRepository.save(entity.get()));
		
	}



	private SubCategoryResponeDTO entityToRespone(SubCategoryEntity entity) {
		SubCategoryResponeDTO dto = new SubCategoryResponeDTO();
		dto.setCategory_id(entity.getCategory().getId());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSort_name(entity.getSortName());
		return dto;
	}
	
	private SubCategoryEntity requestToEntity(SubCategoryRequestDTO request) {
		Optional<CategoryEntity> categoryOptional = categoryReponsitory.findById(request.getCategoryid());
		if (categoryOptional.isEmpty()) return null;
		SubCategoryEntity entity = new SubCategoryEntity();
		entity.setName(request.getName());
		entity.setSortName(request.getSortName());
		entity.setCategory(categoryOptional.get());
		return entity;
	}

}
