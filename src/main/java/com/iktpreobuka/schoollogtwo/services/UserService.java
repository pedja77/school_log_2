package com.iktpreobuka.schoollogtwo.services;

import com.iktpreobuka.schoollogtwo.entities.UserEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO newUser);
	UserEntity updateUser(Integer id, UserDTO user);
	UserDTO deleteUser(Integer id);
	UserEntity getUser(Integer id);
}
