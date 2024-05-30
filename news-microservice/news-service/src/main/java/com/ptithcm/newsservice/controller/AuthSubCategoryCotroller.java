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

import com.ptithcm.newsservice.dto.request.SubCategoryRequestDTO;
import com.ptithcm.newsservice.dto.response.SubCategoryResponeDTO;
import com.ptithcm.newsservice.service.SubCategoryService;

@RestController
@RequestMapping("/api/v1/admin/subcategory")
public class AuthSubCategoryCotroller {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@PostMapping("add")
	public ResponseEntity<?> addSubCategory(@RequestBody SubCategoryRequestDTO request ){
		SubCategoryResponeDTO responseData = subCategoryService.addSubCategory(request);
		if (responseData!=null)
			return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Param invalid");
	}
	
	@PutMapping("update/{subcategoryid}")
	public ResponseEntity<?> updateSubCategory(@RequestBody SubCategoryRequestDTO request, @PathVariable Long subcategoryid){
		SubCategoryResponeDTO responseData = subCategoryService.updateSubCategory(request, subcategoryid);
		if (responseData!=null)
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Param invalid");
	}
	
	@DeleteMapping("{subcategoryid}")
	public ResponseEntity<?> deleteSubCategory(@PathVariable Long subcategoryid){
		if (subCategoryService.deleteSubCategory(subcategoryid)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
	}
}
