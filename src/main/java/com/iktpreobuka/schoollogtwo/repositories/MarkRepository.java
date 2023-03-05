package com.iktpreobuka.schoollogtwo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.MarkEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentSubjectEntity;

public interface MarkRepository extends CrudRepository<MarkEntity, Integer> {

	public List<MarkEntity> findByStudent(StudentSubjectEntity student);
}
