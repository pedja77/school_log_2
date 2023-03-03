package com.iktpreobuka.schoollogtwo.services;

import com.iktpreobuka.schoollogtwo.entities.MarkEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.MarkDTO;

public interface MarkService {

	public MarkDTO createMark(MarkDTO newMark);
	public MarkDTO createFinalMark(MarkDTO finalMark);
	public MarkDTO updateMark(Integer id, MarkDTO updatedMark);
	public MarkEntity deleteMark(Integer id);
}
