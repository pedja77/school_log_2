package com.iktpreobuka.schoollogtwo.services;

import com.iktpreobuka.schoollogtwo.entities.TeacherEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.TeacherDTO;

public interface TeacherService {

	public TeacherEntity createTeacher(TeacherDTO newTeacher);
	public TeacherEntity updateTeacher(Integer id, TeacherDTO updatedTeacher);
	public TeacherEntity deleteTeacher(Integer id);
}
