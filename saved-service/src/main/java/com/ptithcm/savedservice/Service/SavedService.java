package com.ptithcm.savedservice.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ptithcm.savedservice.IService.ISavedService;
import com.ptithcm.savedservice.dto.response.SavedResponeDTO;
import com.ptithcm.savedservice.entity.SavedEntity;
import com.ptithcm.savedservice.repository.SavedRepository;
import com.ptithcm.savedservice.util.JwtUtil;

@Service
public class SavedService implements ISavedService {
	
	@Autowired
	private SavedRepository savedRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JwtUtil jwtUtil;
	
    private final String newsServiceUrl = "http://News-news-service/api/v1/news";

	@Override
	public SavedResponeDTO savedNews(Long newsid, String token) {
		if (!tokenValidate(token)) {
			throw new IllegalArgumentException("Token invalid");
		}
		if (!isNewsExist(newsid)) {
            throw new IllegalArgumentException("News ID does not exist");
        }
		SavedEntity entity = requestToEntity(newsid, token);
		
		if (isSaved(entity)) {
			throw new IllegalArgumentException("Saved is exist");
		}
		
		return entityToRespone(savedRepository.save(entity));
	}

	@Override
	public List<SavedResponeDTO> findByUserId(String token) {
		if (!tokenValidate(token)) {
			throw new IllegalArgumentException("Token invalid");
		}
		return  savedRepository.findByUserIdOrderByCreatedAtDesc(jwtUtil.extractUserId(token))
				.stream()
				.map(this::entityToRespone)
				.collect(Collectors.toList());
	}

	@Override
	public boolean deleteSaved(Long saveid, String token) {
		if (!tokenValidate(token)) {
			throw new IllegalArgumentException("Token invalid");
		}
		Optional<SavedEntity> savedEntity = savedRepository.findById(saveid);
		if (savedEntity.isPresent()) {
			if (savedEntity.get().getUserId()==jwtUtil.extractUserId(token)) {
				savedRepository.delete(savedEntity.get());
				return true;
			}
			else {
				throw new RuntimeException("Not authorized");
			}
			
		}
		return false;
	}
	
	private SavedResponeDTO entityToRespone(SavedEntity entity) {
		SavedResponeDTO dto = new SavedResponeDTO();
		dto.setNewsid(entity.getNewsId());
		dto.setId(entity.getId());
		dto.setCreateAt(entity.getCreatedAt());
		
		return dto;
	}
	
	private SavedEntity requestToEntity(Long request, String token) {
		if (!tokenValidate(token)) {
			throw new IllegalArgumentException("Token invalid");
		}
		SavedEntity entity = new SavedEntity();
		entity.setNewsId(request);
		entity.setUserId(jwtUtil.extractUserId(token));
		
		return entity;
	}
	
	private boolean isNewsExist(Long newsId) {
        try {
            String url = newsServiceUrl + "/" + newsId;
            ResponseEntity<?> response = restTemplate.getForEntity(url, Object.class);
            HttpStatusCode statusCode = response.getStatusCode();
            if (statusCode==HttpStatus.OK) {
            	return true;
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
        return false;
    }
	
	private boolean isSaved(SavedEntity entity) {
		Optional<SavedEntity> savedentity = savedRepository.findByUserIdAndNewsId(entity.getUserId(), entity.getNewsId());
		return savedentity.isPresent();
	}
	
	private boolean tokenValidate(String token) {
		try {
			jwtUtil.validateToken(token);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

}
