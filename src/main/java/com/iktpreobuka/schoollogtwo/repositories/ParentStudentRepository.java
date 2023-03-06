package com.iktpreobuka.schoollogtwo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.ParentStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentEntity;

public interface ParentStudentRepository extends CrudRepository<ParentStudentEntity, Integer> {

	public List<ParentStudentEntity> findByStudent(StudentEntity student);
	public List<ParentStudentEntity> findByParent(ParentEntity parent);
	public ParentStudentEntity findByParentUsernameAndStudentId(String username, Integer id);
	public boolean existsByParent(ParentEntity parent);
}
