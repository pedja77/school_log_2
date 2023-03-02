package com.iktpreobuka.schoollogtwo.services;

import com.iktpreobuka.schoollogtwo.entities.SubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.SubjectDTO;

public interface SubjectService {

	public SubjectDTO createSubject(SubjectDTO newSubject);
	public SubjectDTO updateSubject(Integer id,SubjectDTO updatedSubject);
	public SubjectEntity deleteSubject(Integer id);
	
}
