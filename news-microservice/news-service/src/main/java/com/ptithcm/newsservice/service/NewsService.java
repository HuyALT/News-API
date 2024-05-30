package com.ptithcm.newsservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ptithcm.newsservice.Iservice.INewservice;
import com.ptithcm.newsservice.dto.request.NewsRequestDTO;
import com.ptithcm.newsservice.dto.response.NewsResponeDTO;
import com.ptithcm.newsservice.dto.response.SNewsResponseDTO;
import com.ptithcm.newsservice.entity.CategoryEntity;
import com.ptithcm.newsservice.entity.NewsEntity;
import com.ptithcm.newsservice.entity.SubCategoryEntity;
import com.ptithcm.newsservice.repository.CategoryReponsitory;
import com.ptithcm.newsservice.repository.NewsRepository;
import com.ptithcm.newsservice.repository.SubCategoryRepository;
import com.ptithcm.newsservice.util.JwtUtil;

@Service
public class NewsService implements INewservice{
	
	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private CategoryReponsitory categoryReponsitory;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	//Public service
	@Override
	public List<SNewsResponseDTO> getLatestNews(int pageIndex, int pageSize, Integer type) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
		Page<NewsEntity> page = newsRepository.findAllActiveNews(pageable, type);
		
		if (page.hasContent()) {
			return page.getContent()
				.stream()
				.map(this::newsEntityToSnewsResponse)
				.collect(Collectors.toList());
		}
		return List.of();
	}
	
	@Override
	public int totalPageLatestNews(int pageSize, Integer type) {
		
		int totalRecords = newsRepository.countAllActiveNews(type);
		
		return (int) Math.ceil((double) totalRecords / pageSize);
		
	}

	@Override
	public List<SNewsResponseDTO> getLatestNewsCategory(int pageIndex, int pageSize, Long categoryid, Integer type) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
		Optional<CategoryEntity> categoryOptional = categoryReponsitory.findById(categoryid);
		if (categoryOptional.isEmpty()) return List.of();
		Page<NewsEntity> page = newsRepository.findAllActiveNewsByCategory(categoryOptional.get(), pageable, type);
		if (page.hasContent()) {
			return page.getContent()
					.stream()
					.map(this::newsEntityToSnewsResponse)
					.collect(Collectors.toList());
		}
		return List.of();
	}
	
	@Override
	public int totalPageLatestNewsCategory(int pageSize, Long categoryid, Integer type) {
		Optional<CategoryEntity> categoryOptional = categoryReponsitory.findById(categoryid);
		if (categoryOptional.isEmpty()) return 0;
		
		int totalRecords = newsRepository.countAllActiveNewsByCategory(categoryOptional.get(), type);
		
		return (int) Math.ceil((double) totalRecords / pageSize);
		
	}

	@Override
	public List<SNewsResponseDTO> getLatestNewsSubCategory(int pageIndex, int pageSize, Long subcategoryid, Integer type) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
		Optional<SubCategoryEntity> subCategoryOptional = subCategoryRepository.findById(subcategoryid);
		if (subCategoryOptional.isEmpty()) return List.of();
		Page<NewsEntity> page = newsRepository.findAllActiveNewsBySubCategory(subCategoryOptional.get(), pageable,type);
		if (page.hasContent()) {
			return page.getContent()
					.stream()
					.map(this::newsEntityToSnewsResponse)
					.collect(Collectors.toList());
		}
		return List.of();
	}
	
	@Override
	public int totalPageLatestNewsSubCategory(int pageSize, Long subcategoryid, Integer type) {
		Optional<SubCategoryEntity> subCategoryOptional = subCategoryRepository.findById(subcategoryid);
		if (subCategoryOptional.isEmpty()) return 0;
		
		int totalRecords = newsRepository.countAllActiveNewsBySubCategory(subCategoryOptional.get(), type);
		
		return (int) Math.ceil((double) totalRecords / pageSize);
		
	}

	@Override
	public NewsResponeDTO findNewsById(Long id) {
		Optional<NewsEntity> newsEntity = newsRepository.findById(id);
		if (newsEntity.isPresent()) {
			if (newsEntity.get().getActive()==1)
				return newsEntityTonewsRespone(newsEntity.get());
		}
		return null;
	}
	
	
	
	@Override
	public List<SNewsResponseDTO> getSearchNews(String keyword, int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
		
		Page<NewsEntity> page = newsRepository.searchByKeyword(keyword, pageable);
        
        if (page.hasContent()) {
        	return page.getContent().stream()
        			.map(this::newsEntityToSnewsResponse)
        			.collect(Collectors.toList());
        }
        
		return List.of();
	}

	@Override
	public int totalPageSerchNews(String keyword, int pageSize) {
		
		int totalRecords = newsRepository.countSearchByKeyword(keyword);
		
		return (int) Math.ceil((double) totalRecords / pageSize);
	}

	//Auth Service
	@Override
	public List<SNewsResponseDTO> authgetLatestNews(int pageIndex, int pageSize, Integer type) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
		Page<NewsEntity> page = newsRepository.findAll(pageable);
		
		if (page.hasContent()) {
			if (type==null)
				return page.getContent()
					.stream()
					.map(this::newsEntityToSnewsResponse)
					.collect(Collectors.toList());
			else
				return page.getContent().stream().filter(news->news.getType()==type)
						.map(this::newsEntityToSnewsResponse)
						.collect(Collectors.toList());
					
		}
		return List.of();
	}
	
	@Override
	public int totalPageauthLatestNews(int pageSize, Integer type) {
		
		List<NewsEntity> entities = newsRepository.findAll();
		
		int totalRecords = 0;
		
		if (type==null)
			totalRecords = entities.size();
		else
			totalRecords = entities.stream().filter(news->news.getType()==type).collect(Collectors.toList()).size();
		
		return (int) Math.ceil((double) totalRecords / pageSize);
		
	}

	@Override
	public List<SNewsResponseDTO> authgetLatestNewsCategory(int pageIndex, int pageSize, Long categoryid, Integer type) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
		Optional<CategoryEntity> categoryOptional = categoryReponsitory.findById(categoryid);
		if (categoryOptional.isEmpty()) return List.of();
		Page<NewsEntity> page = newsRepository.findNewsByCategory(categoryOptional.get(), pageable, type);
		if (page.hasContent()) {
			return page.getContent()
					.stream()
					.map(this::newsEntityToSnewsResponse)
					.collect(Collectors.toList());
		}
		return List.of();
	}
	
	@Override
	public int totalPageauthLatestNewsCategory(int pageSize, Long categoryid, Integer type) {
		Optional<CategoryEntity> categoryOptional = categoryReponsitory.findById(categoryid);
		if (categoryOptional.isEmpty()) return 0;
		
		int totalRecords = newsRepository.countNewsByCategory(categoryOptional.get(), type);
		
		return (int) Math.ceil((double) totalRecords / pageSize);
	}



	@Override
	public List<SNewsResponseDTO> authgetLatestNewsSubCategory(int pageIndex, int pageSize, Long subcategoryid, Integer type) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
		Optional<SubCategoryEntity> subcategoryOptional = subCategoryRepository.findById(subcategoryid);
		if (subcategoryOptional.isEmpty()) return List.of();
		Page<NewsEntity> page = newsRepository.findNewsBySubcategory(subcategoryOptional.get(), pageable, type);
		if (page.hasContent()) {
			return page.getContent()
					.stream()
					.map(this::newsEntityToSnewsResponse)
					.collect(Collectors.toList());
		}
		return List.of();
	}
	
	@Override
	public int totalPageauthLatestNewsSubCategory(int pageSize, Long subcategoryid, Integer type) {
		Optional<SubCategoryEntity> subCategoryOptional = subCategoryRepository.findById(subcategoryid);
		if (subCategoryOptional.isEmpty()) return 0;
		
		int totalRecords = newsRepository.countNewsBySubcategory(subCategoryOptional.get(), type);
		
		return (int) Math.ceil((double) totalRecords / pageSize);
	}

	@Override
	public NewsResponeDTO authfindNewsById(Long id) {
		Optional<NewsEntity> newsEntity = newsRepository.findById(id);
		if (newsEntity.isPresent()) {
			return newsEntityTonewsRespone(newsEntity.get());
		}
		return null;
	}
	
	@Override
	public NewsResponeDTO addNews(NewsRequestDTO request, String token) {
		NewsEntity requestData = newsRequestTonewsEntity(request, token);
		
		if (requestData==null)
			return null;
		NewsEntity saveData = newsRepository.save(requestData);
		return newsEntityTonewsRespone(saveData);
	}
	
	

	@Override
	public boolean changeActive(Long id) {
		Optional<NewsEntity> newsEntity = newsRepository.findById(id);
		if (newsEntity.isPresent()) {
			if (newsEntity.get().getActive()==1) {
				newsEntity.get().setActive(0);
			}
			else {
				newsEntity.get().setActive(1);
			}
			newsRepository.save(newsEntity.get());
			return true;
		}
		return false;
	}
	
	

	@Override
	public NewsResponeDTO updateNews(NewsRequestDTO request, Long id, String token) {
		Optional<NewsEntity> entityOptional = newsRepository.findById(id);
		if (entityOptional.isEmpty())
			return null;
		NewsEntity dataChange = entityOptional.get();
		
		if (request.getCategory_id()!=null) {
			Optional<CategoryEntity> categoryEntity = categoryReponsitory.findById(request.getCategory_id());
			if (categoryEntity.isPresent()) {
				dataChange.setCategory(categoryEntity.get());
			}
		}
		if (request.getSubcategory_id()!=null) {
			Optional<SubCategoryEntity> subCategoryEntity = subCategoryRepository.findById(request.getSubcategory_id());
			if (subCategoryEntity.isPresent()) {
				dataChange.setSubcategory(subCategoryEntity.get());
			}
		}
		if (request.getContent()!=null) {
			dataChange.setContent(request.getContent());
		}
		if (request.getImage()!=null) {
			dataChange.setImage(request.getImage());
		}
		if (request.getLink()!=null) {
			dataChange.setLink(request.getLink());
		}
		if (request.getSummary()!=null) {
			dataChange.setSummary(request.getSummary());
		}
		if (request.getSort_title()!=null) {
			dataChange.setSortTitle(request.getSort_title());
		}
		if (request.getType()!=null) {
			dataChange.setType(request.getType());
		}
		if (request.getTitle()!=null) {
			dataChange.setTitle(request.getTitle());
		}
		dataChange.setUserId(jwtUtil.extractUserId(token));
		
		return newsEntityTonewsRespone(newsRepository.save(dataChange));
	}

	//Convert Entity to ResponeDTO
	private NewsResponeDTO newsEntityTonewsRespone(NewsEntity entity) {
		NewsResponeDTO dto = new NewsResponeDTO();
		dto.setActive(entity.getActive());
		dto.setCategory_id(entity.getCategory().getId());
		dto.setContent(entity.getContent());
		dto.setCreate_at(entity.getCreatedAt());
		dto.setId(entity.getId());
		dto.setImage(entity.getImage());
		dto.setLink(entity.getLink());
		dto.setSort_title(entity.getSortTitle());
		dto.setSubcategory_id(entity.getSubcategory().getId());
		dto.setSummary(entity.getSummary());
		dto.setTitle(entity.getTitle());
		dto.setType(entity.getType());
		dto.setUpdate_at(entity.getUpdatedAt());
		dto.setCreate_at(entity.getCreatedAt());
		dto.setUserid(entity.getUserId());
		return dto;
	}
	
	private SNewsResponseDTO newsEntityToSnewsResponse(NewsEntity entity) {
		SNewsResponseDTO dto = new SNewsResponseDTO();
		dto.setActive(entity.getActive());
		dto.setCategory_id(entity.getCategory().getId());
		dto.setCreate_at(entity.getCreatedAt());
		dto.setId(entity.getId());
		dto.setImage(entity.getImage());
		dto.setLink(entity.getLink());
		dto.setSort_title(entity.getSortTitle());
		dto.setSubcategory_id(entity.getSubcategory().getId());
		dto.setSummary(entity.getSummary());
		dto.setTitle(entity.getTitle());
		dto.setType(entity.getType());
		dto.setUpdate_at(entity.getUpdatedAt());
		dto.setCreate_at(entity.getCreatedAt());
		dto.setUserid(entity.getUserId());
		return dto;
	}
	
	private NewsEntity newsRequestTonewsEntity(NewsRequestDTO request, String token) {
		NewsEntity entity = new NewsEntity();
		if (request.getCategory_id()==null||request.getSubcategory_id()==null) {
			return null;
		}
		Optional<CategoryEntity> categoryEntity = categoryReponsitory.findById(request.getCategory_id());
		Optional<SubCategoryEntity> subCategoryEntity = subCategoryRepository.findById(request.getSubcategory_id());
		
		if (categoryEntity.isEmpty()|| subCategoryEntity.isEmpty()) {
			return null;
		}
		entity.setActive(request.getActive());
		entity.setCategory(categoryEntity.get());
		entity.setContent(request.getContent());
		if (request.getLink()!=null)
			entity.setImage(request.getImage());
		else
			entity.setImage("https://ik.imagekit.io/dx1lgwjws/News/NewsDefault.png?updatedAt=1716264738596");
		entity.setLink(request.getLink());
		entity.setSortTitle(request.getSort_title());
		entity.setSubcategory(subCategoryEntity.get());
		entity.setSummary(request.getSummary());
		entity.setTitle(request.getTitle());
		entity.setType(request.getType());
		entity.setUserId(jwtUtil.extractUserId(token));
		return entity;
	}
	
}
