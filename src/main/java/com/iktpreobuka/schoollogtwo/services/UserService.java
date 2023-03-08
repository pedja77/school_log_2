package com.iktpreobuka.schoollogtwo.services;

import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO newUser);
	UserDTO updateUser(UserDTO user);
	UserDTO deleteUser(UserDTO user);
	UserDTO getUser(Integer id);
}
