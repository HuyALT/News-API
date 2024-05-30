package com.ptithcm.newsservice.Iservice;

import java.util.List;

import com.ptithcm.newsservice.dto.request.NewsRequestDTO;
import com.ptithcm.newsservice.dto.response.NewsResponeDTO;
import com.ptithcm.newsservice.dto.response.SNewsResponseDTO;

public interface INewservice {
	//Public Service
	List<SNewsResponseDTO> getLatestNews(int pageIndex, int pageSize, Integer type);
	int totalPageLatestNews(int pageSize, Integer type);
	List<SNewsResponseDTO> getLatestNewsCategory(int pageIndex, int pageSize, Long categoryid, Integer type);
	int totalPageLatestNewsCategory(int pageSize, Long categoryid, Integer type);
	List<SNewsResponseDTO> getLatestNewsSubCategory(int pageIndex ,int pageSize, Long subcategoryid, Integer type);
	int totalPageLatestNewsSubCategory(int pageSize, Long subcategoryid, Integer type);
	NewsResponeDTO findNewsById(Long id);
	List<SNewsResponseDTO> getSearchNews(String keyword, int pageIndex, int pageSize);
	int totalPageSerchNews(String keyword, int pageSize);
	
	//Auth Service
	List<SNewsResponseDTO> authgetLatestNews(int pageIndex, int pageSize, Integer type);
	int totalPageauthLatestNews(int pageSize, Integer type);
	List<SNewsResponseDTO> authgetLatestNewsCategory(int pageIndex, int pageSize, Long categoryid, Integer type);
	int totalPageauthLatestNewsCategory(int pageSize, Long categoryid, Integer type);
	List<SNewsResponseDTO> authgetLatestNewsSubCategory(int pageIndex ,int pageSize, Long subcategoryid, Integer type);
	int totalPageauthLatestNewsSubCategory(int pageSize, Long subcategoryid, Integer type);
	NewsResponeDTO authfindNewsById(Long id);
	
	NewsResponeDTO addNews(NewsRequestDTO request, String token);
	boolean changeActive(Long id);
	NewsResponeDTO updateNews(NewsRequestDTO request, Long id, String token);
}
