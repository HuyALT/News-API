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

import com.ptithcm.newsservice.dto.response.NewsResponeDTO;
import com.ptithcm.newsservice.dto.response.SNewsResponseDTO;
import com.ptithcm.newsservice.service.NewsService;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping("latest-news")
	public ResponseEntity<?> getLatestNews(
			@RequestParam(defaultValue = "0")int pageIndex,
			@RequestParam(defaultValue = "100000")int pageSize,
			@RequestParam(required = false, defaultValue = "0")Long categoryid,
			@RequestParam(required = false, defaultValue = "0")Long subcategoryid,
			@RequestParam(required = false)Integer type) {
		List<SNewsResponseDTO> responseData = List.of();
		if (categoryid!=0&&subcategoryid!=0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Param invalid");
		}
		if (categoryid!=0) {
			responseData = newsService.getLatestNewsCategory(pageIndex, pageSize, categoryid, type);
		}
		else if (subcategoryid!=0) {
			responseData = newsService.getLatestNewsSubCategory(pageIndex, pageSize, subcategoryid, type);
		}
		else {
			responseData = newsService.getLatestNews(pageIndex, pageSize, type);
		}
		if (responseData.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}
	
	@GetMapping("latest-news/total-page")
	public ResponseEntity<?> getTotalPage(
			@RequestParam(defaultValue = "100000")int pageSize,
			@RequestParam(required = false, defaultValue = "0")Long categoryid,
			@RequestParam(required = false, defaultValue = "0")Long subcategoryid,
			@RequestParam(required = false)Integer type) {
		int responseData = 0;
		if (categoryid!=0&&subcategoryid!=0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Param invalid");
		}
		if (categoryid!=0) {
			responseData = newsService.totalPageLatestNewsCategory(pageSize, categoryid, type);
		}
		else if (subcategoryid!=0) {
			responseData = newsService.totalPageLatestNewsSubCategory(pageSize, subcategoryid, type);
		}
		else {
			responseData = newsService.totalPageLatestNews(pageSize, type);
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}
	
	@GetMapping("search")
	public ResponseEntity<?> searchRelsults(
			@RequestParam(defaultValue = "0")int pageIndex,
			@RequestParam(defaultValue = "100000")int pageSize,
			@RequestParam(required = true)String keyword) {
		List<SNewsResponseDTO> responseData = newsService.getSearchNews(keyword, pageIndex, pageSize);
		if (responseData.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}
	
	@GetMapping("search/total-page")
	public ResponseEntity<?> getTotalPageSearch(
			@RequestParam(defaultValue = "100000")int pageSize,
			@RequestParam(required = true)String keyword) {
		return ResponseEntity.status(HttpStatus.OK).body(newsService.totalPageSerchNews(keyword, pageSize));
	}
	
	
	@GetMapping("{newsid}")
	public ResponseEntity<?> getNewsById(@PathVariable Long newsid){
		NewsResponeDTO responseData = newsService.findNewsById(newsid);
		if (responseData!=null)
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data");
	}
	
	
}
