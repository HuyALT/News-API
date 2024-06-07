package com.ptithcm.user_service.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ptithcm.user_service.Exception.PasswordException;
import com.ptithcm.user_service.IService.IUserService;
import com.ptithcm.user_service.dto.UserDTO;
import com.ptithcm.user_service.entity.UserEntity;
import com.ptithcm.user_service.repository.UserRepository;
import com.ptithcm.user_service.util.JwtUtil;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public boolean changePassword(String token, String oldPw, String newPw)
			throws PasswordException{
		if (newPw==null||oldPw==null) return false;
		if (newPw.isBlank()||oldPw.isBlank()) return false;
		Optional<UserEntity> userEntity = userRepository.findById(jwtUtil.extractUserId(token));
		if (userEntity.isPresent()) {
			if (userEntity.get().getId()==jwtUtil.extractUserId(token)) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				if (!encoder.matches(oldPw, userEntity.get().getPassword())) {
					throw new PasswordException("Invalid password");
				}
				userEntity.get().setPassword(encoder.encode(newPw));
				userRepository.save(userEntity.get());
				return true;
				
			}
			else {
				return false;
			}
		}
		return false;
	}

	@Override
	public UserDTO updateUserInfo(String token, UserDTO userinfo){
		Optional<UserEntity> userEntity = userRepository.findById(jwtUtil.extractUserId(token));
		if (userEntity.isPresent()) {
			if (userEntity.get().getId()==jwtUtil.extractUserId(token)) {
				if (userinfo.getUsername()!=null&&!userinfo.getUsername().isBlank())
					userEntity.get().setUsername(userinfo.getUsername());
				if (userinfo.getImage()!=null&&!userinfo.getImage().isBlank()) {
					userEntity.get().setImage(userinfo.getImage());
				}
				return entityToDto(userRepository.save(userEntity.get()));
			}
			return null;
		}
		return null;
	}

	@Override
	public UserDTO getInfo(String token) {
		Optional<UserEntity> userEntity = userRepository.findById(jwtUtil.extractUserId(token));
		if (userEntity.isPresent()) {
			return entityToDto(userEntity.get());
		}
		return null;
	}

	@Override
	public boolean isLockedOrActive(String token) {
		Optional<UserEntity> userEntity = userRepository.findById(jwtUtil.extractUserId(token));
		if (userEntity.isPresent()) {
			return userEntity.get().getLocked()==1&&userEntity.get().getActicve()==1?true:false;
		}
		return false;
	}
	
	
	
	@Override
	public UserDTO getuserInfoByid(Long id) {
		Optional<UserEntity> userEntity = userRepository.findById(id);
		if (userEntity.isPresent()) {
			return entityToDto(userEntity.get());
		}
		return null;
	}

	private UserDTO entityToDto(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setActive(entity.getActicve());
		dto.setEmail(entity.getEmail());
		dto.setImage(entity.getImage());
		dto.setLocked(entity.getLocked());
		dto.setRole(entity.getRole().getId());
		dto.setUsername(entity.getUsername());
		dto.setCreateAt(entity.getCreatedAt());
		dto.setUpdateAt(entity.getUpdatedAt());
		dto.setId(entity.getId());
		return dto;
	}
	
}
