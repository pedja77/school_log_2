package com.iktpreobuka.schoollogtwo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.TeacherStudentEntity;

public interface TeacherStudentRepository extends CrudRepository<TeacherStudentEntity, Integer> {

	public TeacherStudentEntity findByTeacherIdAndStudentId(Integer teacherId, Integer studentId);
	public List<TeacherStudentEntity> findByTeacherId(Integer teacherId);
}
