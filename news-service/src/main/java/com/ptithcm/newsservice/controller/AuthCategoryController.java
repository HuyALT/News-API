package com.ptithcm.newsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.newsservice.dto.request.CategoryRequestDTO;
import com.ptithcm.newsservice.dto.response.CategoryResponseDTO;
import com.ptithcm.newsservice.service.CategoryService;

@RestController
@RequestMapping("/api/v1/admin/category")
public class AuthCategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("add")
	public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDTO request) {
		CategoryResponseDTO responseData = categoryService.addCategory(request);
		if (responseData==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD REQUEST");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
	}
	
	@PutMapping("/update/{categoryid}")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryRequestDTO request, @PathVariable Long categoryid){
		CategoryResponseDTO responseData = categoryService.updateCategory(request, categoryid);
		if (responseData==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD REQUEST");
		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}
	
	@DeleteMapping("{categoryid}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long categoryid){
		if (categoryService.deleteCategory(categoryid)) {
			return ResponseEntity.status(HttpStatus.OK).body("Delete success");
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Delete fail");
	}
}
