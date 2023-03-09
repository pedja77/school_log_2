package com.iktpreobuka.schoollogtwo.services;

import java.util.Optional;

import com.iktpreobuka.schoollogtwo.entities.MarkEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.MarkDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.responses.MarkResDTO;

public interface MarkService {

	public Optional<MarkEntity> createMark(MarkDTO newMark, String teacher, Boolean isFinal);
	public Optional<MarkResDTO> updateMark(Integer id, MarkDTO updatedMark);
	public MarkEntity deleteMark(Integer id);
	public MarkResDTO getMark(Integer id);
}
