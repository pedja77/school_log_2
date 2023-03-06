package com.iktpreobuka.schoollogtwo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentSubjectEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

	public StudentEntity findByUsername(String username);
	public StudentSubjectEntity findByUsernameAndSubjectsId(String username, Integer subjectId);
}
