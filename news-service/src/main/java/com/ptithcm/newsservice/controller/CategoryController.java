package com.ptithcm.newsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.newsservice.dto.response.CategoryResponseDTO;
import com.ptithcm.newsservice.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public ResponseEntity<?> getAllCategory(){
		List<CategoryResponseDTO> categories = categoryService.getAllCategory();
		return ResponseEntity.status(HttpStatus.OK).body(categories);
	}
	
	@GetMapping("{categoryid}")
	public ResponseEntity<?> getCategoryById(@PathVariable Long categoryid){
		CategoryResponseDTO responseData = categoryService.getCategoryById(categoryid);
		if (responseData!=null)
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data");
	}
}
