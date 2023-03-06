package com.iktpreobuka.schoollogtwo.services;

import java.util.Optional;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.ParentDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.responses.ParentResDTO;

public interface ParentService {

	public ParentEntity createParent(ParentDTO newParent);
	public ParentEntity updateParent(Integer id, ParentDTO updatedParent);
	public ParentEntity deleteParent(Integer id);
	public ParentResDTO getAllMarks(String username);
	public Optional<ParentResDTO> getMarksByStudent(String username, Integer studentId);
	public Optional<ParentResDTO> getMarksByStudentAndSubject(String username, Integer studentId, Integer subjectId);
	
}
