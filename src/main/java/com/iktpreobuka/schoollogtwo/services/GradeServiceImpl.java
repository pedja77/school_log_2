package com.iktpreobuka.schoollogtwo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.GradeEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.GradeDTO;
import com.iktpreobuka.schoollogtwo.repositories.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	
	public GradeDTO createGrade(GradeDTO newGrade) {
		GradeEntity grade = new GradeEntity();
		grade.setValue(newGrade.getValue());
		gradeRepository.save(grade);
		
		return newGrade;
	}

	@Override
	public GradeDTO updateGrade(Integer id, GradeDTO updatedGrade) {
		GradeEntity grade = gradeRepository.findById(id).orElseThrow();
		if (updatedGrade.getValue() != null && !grade.getValue().equals(updatedGrade.getValue()))
			grade.setValue(updatedGrade.getValue());
		gradeRepository.save(grade);
		return updatedGrade;
	}

	@Override
	public GradeEntity deleteGrade(Integer id) {
		GradeEntity grade = gradeRepository.findById(id).orElseThrow();
		gradeRepository.delete(grade);
		return grade;
	}

	@Override
	public List<GradeDTO> getAll() {
		List<GradeEntity> grades = (List<GradeEntity>) gradeRepository.findAll();
		List<GradeDTO> dtos = new ArrayList<>();
		for(GradeEntity grade : grades) {
			GradeDTO g = new GradeDTO();
			g.setId(grade.getId());
			g.setValue(grade.getValue());
			dtos.add(g);
		}
		return dtos;
	}
}
