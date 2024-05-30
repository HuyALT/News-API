package com.ptithcm.newsservice.Iservice;

import java.util.List;

import com.ptithcm.newsservice.dto.request.SubCategoryRequestDTO;
import com.ptithcm.newsservice.dto.response.SubCategoryResponeDTO;

public interface ISubCategoryService {
	
	List<SubCategoryResponeDTO> getSubCategoryByCategory(Long categoryid);
	List<SubCategoryResponeDTO> getAllSubCategory();
	SubCategoryResponeDTO getSubCategoryByID(Long id);
	
	SubCategoryResponeDTO addSubCategory(SubCategoryRequestDTO request);
	SubCategoryResponeDTO updateSubCategory(SubCategoryRequestDTO request, Long id);
	boolean deleteSubCategory(Long id);
}
