package com.iktpreobuka.schoollogtwo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.UserRoleEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.ParentDTO;
import com.iktpreobuka.schoollogtwo.repositories.ParentRepository;
import com.iktpreobuka.schoollogtwo.repositories.UserRoleRepository;

@Service
public class ParentServiceImpl implements ParentService {
	
	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private UserRoleRepository roleRepository;

	@Override
	public ParentEntity createParent(ParentDTO newParent) {
		ParentEntity parent = new ParentEntity();
		parent.setFirstName(newParent.getFirstName());
		parent.setLastName(newParent.getLastName());
		parent.setUsername(newParent.getUsername());
		parent.setPassword(newParent.getPassword());
		parent.setEmail(newParent.getEmail());
		parent.setRole(roleRepository.findByRoleName(newParent.getRole()));
		
		return parentRepository.save(parent);	
	}

}
