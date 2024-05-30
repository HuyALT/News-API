package com.ptithcm.newsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.newsservice.dto.response.SubCategoryResponeDTO;
import com.ptithcm.newsservice.service.SubCategoryService;

@RestController
@RequestMapping("/api/v1/subcategory")
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@GetMapping("/category")
	public ResponseEntity<?> getByCategoryid(@RequestParam Long categoryid) {
		List<SubCategoryResponeDTO> responseData = subCategoryService.getSubCategoryByCategory(categoryid);
		if (responseData.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data");
		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllCategory(){
		List<SubCategoryResponeDTO> categories = subCategoryService.getAllSubCategory();
		return ResponseEntity.status(HttpStatus.OK).body(categories);
	}
	
	@GetMapping("{subcategoryid}")
	public ResponseEntity<?> getCategoryById(@PathVariable Long subcategoryid){
		SubCategoryResponeDTO responseData = subCategoryService.getSubCategoryByID(subcategoryid);
		if (responseData!=null)
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data");
	}
}
	
