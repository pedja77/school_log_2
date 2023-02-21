package com.iktpreobuka.schoollogtwo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.ParentDTO;
import com.iktpreobuka.schoollogtwo.repositories.ParentRepository;

public class ParentServiceImpl implements ParentService {
	
	@Autowired
	private ParentRepository parentRepository;

	@Override
	public ParentEntity createParent(ParentDTO newParent) {
		ParentEntity parent = new ParentEntity();
		parent.setFirstName(newParent.getFirstName());
		parent.setLastName(newParent.getLastName());
		parent.setUsername(newParent.getUsername());
		parent.setPassword(newParent.getPassword());
		parent.setEmail(newParent.getEmail());
		
		return parentRepository.save(parent);	
	}

}
