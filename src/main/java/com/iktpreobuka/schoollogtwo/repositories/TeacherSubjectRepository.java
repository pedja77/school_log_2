package com.iktpreobuka.schoollogtwo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.TeacherSubjectEntity;

public interface TeacherSubjectRepository extends CrudRepository<TeacherSubjectEntity, Integer> {

	public TeacherSubjectEntity findByTeacherIdAndSubjectId(Integer teacherId, Integer subjectId); 
}
