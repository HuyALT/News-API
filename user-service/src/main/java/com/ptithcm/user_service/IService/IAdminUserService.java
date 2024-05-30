package com.ptithcm.user_service.IService;

import java.util.List;

import com.ptithcm.user_service.dto.UserDTO;

public interface IAdminUserService {
	List<UserDTO> getAllUser();
	boolean lockedUser(Long userid);
	boolean delteUser(Long userid);
}
