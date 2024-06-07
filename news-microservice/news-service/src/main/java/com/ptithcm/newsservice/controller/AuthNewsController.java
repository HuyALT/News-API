package com.ptithcm.newsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.newsservice.dto.request.NewsRequestDTO;
import com.ptithcm.newsservice.dto.response.NewsResponeDTO;
import com.ptithcm.newsservice.dto.response.SNewsResponseDTO;
import com.ptithcm.newsservice.service.NewsService;

@RestController
@RequestMapping("/api/v1/admin/news")
public class AuthNewsController {
	@Autowired
	private NewsService newsService;
	
	@GetMapping("latest-news")
	public ResponseEntity<?> getLatestNews(
			@RequestParam(defaultValue = "0")int pageindex,
			@RequestParam(defaultValue = "10000")int pageSize,
			@RequestParam(required = false, defaultValue = "0")Long categoryid,
			@RequestParam(required = false, defaultValue = "0")Long subcategoryid,
			@RequestParam(required = false)Integer type) {
		List<SNewsResponseDTO> responseData = List.of();
		if (categoryid!=0&&subcategoryid!=0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Param invalid");
		}
		if (categoryid!=0) {
			responseData = newsService.authgetLatestNewsCategory(pageindex, pageSize, categoryid, type);
		}
		else if (subcategoryid!=0) {
			responseData = newsService.authgetLatestNewsSubCategory(pageindex, pageSize, subcategoryid, type);
		}
		else {
			responseData = newsService.authgetLatestNews(pageindex, pageSize, type);
		}
		if (responseData.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}
	
	@GetMapping("latest-news/total-page")
	public ResponseEntity<?> getTotalPage(
			@RequestParam(defaultValue = "10")int pageSize,
			@RequestParam(required = false, defaultValue = "0")Long categoryid,
			@RequestParam(required = false, defaultValue = "0")Long subcategoryid,
			@RequestParam(required = false)Integer type) {
		int responseData = 0;
		if (categoryid!=0&&subcategoryid!=0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Param invalid");
		}
		if (categoryid!=0) {
			responseData = newsService.totalPageauthLatestNewsCategory(pageSize, categoryid, type);
		}
		else if (subcategoryid!=0) {
			responseData = newsService.totalPageauthLatestNewsSubCategory(pageSize, subcategoryid, type);
		}
		else {
			responseData = newsService.totalPageauthLatestNews(pageSize, type);
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}
	
	@GetMapping("{newsid}")
	public ResponseEntity<?> getNewsById(@PathVariable Long newsid){
		NewsResponeDTO responseData = newsService.authfindNewsById(newsid);
		if (responseData!=null)
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
	}
	
	@PostMapping("add")
	public ResponseEntity<?> addNews(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody NewsRequestDTO request) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            NewsResponeDTO responeData = newsService.addNews(request, token);
    		if (responeData==null)
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data invalid");
    		return ResponseEntity.status(HttpStatus.CREATED).body(responeData);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	}
	
	@PutMapping("changeActive/{newsid}")
	public ResponseEntity<?> changeActive(@PathVariable Long newsid) {
		if (newsService.changeActive(newsid))
			return ResponseEntity.status(HttpStatus.OK).body("Success");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Can not change active");
	}
	
	@PutMapping("update/{newsid}")
	public ResponseEntity<?> updateNews(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long newsid,
			@RequestBody NewsRequestDTO request) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            NewsResponeDTO responeData = newsService.updateNews(request, newsid, token);
    		if (responeData==null)
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data invalid");
    		return ResponseEntity.status(HttpStatus.OK).body(responeData);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	}
	
}
