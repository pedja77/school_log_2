package com.iktpreobuka.schoollogtwo.services;

import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.StudentDTO;

public interface StudentService {

	public StudentEntity createStudent(StudentDTO newStudent);
	public StudentEntity addParentToStudent(Integer studentId, Integer parentId);
	public StudentEntity deleteStudent(Integer studentId);
}
