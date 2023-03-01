package com.iktpreobuka.schoollogtwo.services;

import java.util.List;

import com.iktpreobuka.schoollogtwo.entities.TeacherEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.TeacherDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.TeachersCollectionDTO;

public interface TeacherService {

	public TeacherEntity createTeacher(TeacherDTO newTeacher);
	public TeacherEntity updateTeacher(Integer id, TeacherDTO updatedTeacher);
	public TeacherEntity deleteTeacher(Integer id);
	public String bulkCreate(TeachersCollectionDTO teachers);
}
