package com.ptithcm.user_service.IService;


import com.ptithcm.user_service.Exception.PasswordException;
import com.ptithcm.user_service.dto.UserDTO;

public interface IUserService {
	boolean changePassword(String token, String oldPw, String newPw) throws PasswordException;
	UserDTO updateUserInfo(String token, UserDTO userinfo);
	UserDTO getInfo(String token);
	boolean isLockedOrActive(String token);
	
}
