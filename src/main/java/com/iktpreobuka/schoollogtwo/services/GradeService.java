package com.iktpreobuka.schoollogtwo.services;

import java.util.List;

import com.iktpreobuka.schoollogtwo.entities.GradeEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.GradeDTO;

public interface GradeService {
	
	public GradeDTO createGrade(GradeDTO newGrade);
	public GradeDTO updateGrade(Integer id, GradeDTO updatedGrade);
	public GradeEntity deleteGrade(Integer id);
	public List<GradeDTO> getAll();
	
}
