package com.iktpreobuka.schoollogtwo.services;

import java.security.Principal;
import java.util.Optional;

import com.iktpreobuka.schoollogtwo.entities.MarkEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.MarkDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.responses.MarkResDTO;

public interface MarkService {

	public Optional<MarkEntity> createMark(MarkDTO newMark, String teacher, Boolean isFinal) throws Exception;
	public Optional<MarkResDTO> updateMark(Integer id, MarkDTO updatedMark, Principal p);
	public MarkResDTO deleteMark(Integer id, Principal p);
	public MarkResDTO getMark(Integer id);
}
