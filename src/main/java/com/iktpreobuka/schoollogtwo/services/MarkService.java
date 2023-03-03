package com.iktpreobuka.schoollogtwo.services;

import java.util.Optional;

import com.iktpreobuka.schoollogtwo.entities.MarkEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.MarkDTO;

public interface MarkService {

	public Optional<MarkEntity> createMark(MarkDTO newMark);
	public MarkDTO createFinalMark(MarkDTO finalMark);
	public MarkDTO updateMark(Integer id, MarkDTO updatedMark);
	public MarkEntity deleteMark(Integer id);
}
