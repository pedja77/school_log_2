package com.iktpreobuka.schoollogtwo.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.AdminEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;
import com.iktpreobuka.schoollogtwo.repositories.AdminRepository;
import com.iktpreobuka.schoollogtwo.repositories.UserRoleRepository;
import com.iktpreobuka.schoollogtwo.util.Encryption;

@Service
public class AdminServiceImpl implements AdminService {
	
//	private static final String DOWNLOAD_FILE = "logs/spring-boot-logging.log";

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	UserRoleRepository roleRepository;
	
	@Value("${logging.file.name}")
	private String DOWNLOAD_FILE;
	
	@Override
	public AdminEntity createAdmin(UserDTO user) {
		AdminEntity admin = new AdminEntity();
		admin.setFirstName(user.getFirstName());
		admin.setLastName(user.getLastName());
		admin.setUsername(user.getUsername());
		admin.setPassword(Encryption.getPassEncoded(user.getPassword()));
		admin.setRole(roleRepository.findByRoleName("ROLE_ADMIN"));
		
		return adminRepository.save(admin);
	}

	@Override
	public AdminEntity updateAdmin(Integer id, UserDTO user) {
		AdminEntity admin = adminRepository.findById(id).orElseThrow();
		if (!admin.getFirstName().equals(user.getFirstName())) {
			admin.setFirstName(user.getFirstName());
		}
		if (!admin.getLastName().equals(user.getLastName())) {
			admin.setLastName(user.getLastName());
		}
		if (!admin.getUsername().equals(user.getUsername())) {
			admin.setUsername(user.getUsername());
		}
		if (!Encryption.validatePassword(user.getPassword(), admin.getPassword())) {
			admin.setPassword(Encryption.getPassEncoded(user.getPassword()));
		}
		return admin;
	}

	@Override
	public AdminEntity deleteAdmin(Integer id) {
		AdminEntity admin = adminRepository.findById(id).orElseThrow();
		adminRepository.delete(admin);
		return admin;
	}

	@Override
	public AdminEntity deleteAdmin(AdminEntity admin) {
		adminRepository.delete(admin);
		return admin;
	}

	@Override
	public InputStreamResource getLogs() throws IOException, SecurityException {
		String logsFile = DOWNLOAD_FILE;
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(logsFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new InputStreamResource(inStream);
	}

	
}
