package com.iktpreobuka.schoollogtwo.repositories;

import java.util.NoSuchElementException;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.StudentSubjectEntity;

public interface StudentSubjectRepository extends CrudRepository<StudentSubjectEntity, Integer> {

	public StudentSubjectEntity findByStudentIdAndSubjectId(
			Integer studentId, Integer subjectId
			);
}
