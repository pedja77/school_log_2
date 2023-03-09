package com.iktpreobuka.schoollogtwo.services;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;

import com.iktpreobuka.schoollogtwo.entities.AdminEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;

public interface AdminService {

	public AdminEntity createAdmin(UserDTO user);
	public AdminEntity updateAdmin(Integer id, UserDTO user);
	public AdminEntity deleteAdmin(Integer id);
	public AdminEntity deleteAdmin(AdminEntity admin);
	public InputStreamResource getLogs() throws IOException, SecurityException;
}
