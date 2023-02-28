package com.iktpreobuka.schoollogtwo.services;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.ParentDTO;

public interface ParentService {

	public ParentEntity createParent(ParentDTO newParent);
	public ParentEntity updateParent(Integer id, ParentDTO updatedParent);
	public ParentEntity deleteParent(Integer id);
	
}
