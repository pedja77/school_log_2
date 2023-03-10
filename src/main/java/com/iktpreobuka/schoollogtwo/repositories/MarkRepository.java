package com.iktpreobuka.schoollogtwo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.MarkEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentSubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherSubjectEntity;

public interface MarkRepository extends CrudRepository<MarkEntity, Integer> {

	public List<MarkEntity> findByStudent(StudentSubjectEntity student);
	public Boolean existsByStudentStudentIdAndSubjectSubjectId(Integer studentId, Integer subjectId);
//	public List<MarkEntity> findByStudentAndSubjectAndTeacherTeacherUsername(StudentSubjectEntity student,
//			TeacherSubjectEntity subject, String teacher);
	
}
