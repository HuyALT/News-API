package com.ptithcm.savedservice.IService;

import java.util.List;

import com.ptithcm.savedservice.dto.response.SavedResponeDTO;

public interface ISavedService {
	SavedResponeDTO savedNews(Long newsid, String token);
	List<SavedResponeDTO> findByUserId(String token);
	boolean deleteSaved(Long saveid, String token);
}
