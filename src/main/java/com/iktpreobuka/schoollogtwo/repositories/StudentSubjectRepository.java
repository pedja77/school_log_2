package com.iktpreobuka.schoollogtwo.repositories;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentSubjectEntity;

public interface StudentSubjectRepository extends CrudRepository<StudentSubjectEntity, Integer> {

	public StudentSubjectEntity findByStudentIdAndSubjectId(Integer studentId, Integer subjectId);
	public StudentSubjectEntity findByStudentUsernameAndSubjectId(String username, Integer subjectId);
	public List<StudentSubjectEntity> findByStudent(StudentEntity student);
}
