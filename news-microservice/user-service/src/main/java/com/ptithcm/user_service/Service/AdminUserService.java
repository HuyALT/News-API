package com.ptithcm.user_service.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptithcm.user_service.IService.IAdminUserService;
import com.ptithcm.user_service.dto.UserDTO;
import com.ptithcm.user_service.entity.RoleEntity;
import com.ptithcm.user_service.entity.UserEntity;
import com.ptithcm.user_service.repository.RoleRepository;
import com.ptithcm.user_service.repository.UserRepository;

@Service
public class AdminUserService implements IAdminUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<UserDTO> getAllUser() {
		RoleEntity roleEntity = roleRepository.findById(2L).get();
		return userRepository.findByRole(roleEntity).stream()
				.map(this::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public boolean lockedUser(Long userid) {
		Optional<UserEntity> userEntity = userRepository.findById(userid);
		if (userEntity.isPresent()&&userEntity.get().getRole().getId()!=1L) {
			if (userEntity.get().getLocked()==0)
				userEntity.get().setLocked(1);
			else
				userEntity.get().setLocked(0);
			userRepository.save(userEntity.get());
			return true;
		}
		return false;
	}

	@Override
	public boolean delteUser(Long userid) {
		Optional<UserEntity> userEntity = userRepository.findById(userid);
		if (userEntity.isPresent()&&userEntity.get().getRole().getId()!=1L&&userEntity.get().getActicve()==0){
			userRepository.delete(userEntity.get());
			return true;
		}
		return false;
	}
	
	private UserDTO entityToDto(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setActive(entity.getActicve());
		dto.setEmail(entity.getEmail());
		dto.setImage(entity.getImage());
		dto.setLocked(entity.getLocked());
		dto.setUsername(entity.getUsername());
		dto.setCreateAt(entity.getCreatedAt());
		dto.setUpdateAt(entity.getUpdatedAt());
		return dto;
	}
}
