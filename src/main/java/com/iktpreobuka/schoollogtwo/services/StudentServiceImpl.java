package com.iktpreobuka.schoollogtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.ParentStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.StudentDTO;
import com.iktpreobuka.schoollogtwo.repositories.ParentRepository;
import com.iktpreobuka.schoollogtwo.repositories.ParentStudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private ParentStudentRepository parentStudentRepository;
	
	@Override
	public StudentEntity createStudent(StudentDTO newStudent) {
		StudentEntity student = new StudentEntity();
		student.setFirstName(newStudent.getFirstName());
		student.setLastName(newStudent.getLastName());
		student.setUsername(newStudent.getUsername());
		student.setPassword(newStudent.getPassword());
		
		return studentRepository.save(student);
	}

	@Override
	public StudentEntity addParentToStudent(Integer studentId, Integer parentId) {
		ParentEntity parent = parentRepository.findById(parentId).get();
		StudentEntity student = studentRepository.findById(studentId).get();
		ParentStudentEntity parentStudent = new ParentStudentEntity();

		parentStudent.setParent(parent);
		parentStudent.setStudent(student);
		
		parentStudentRepository.save(parentStudent);
		
		return student;
	}

	@Override
	@Transactional
	public StudentEntity deleteStudent(Integer studentId) {
		StudentEntity student = studentRepository.findById(studentId).get();
		List<ParentStudentEntity> parentStudents = parentStudentRepository.findByStudent(student);
		
		for (ParentStudentEntity ps: parentStudents) {
			parentStudentRepository.delete(ps);
			ps.getParent().getParentStudent().remove(ps);
			if (ps.getParent().getParentStudent().size() == 0)
				parentRepository.delete(ps.getParent());
		}
		
		studentRepository.delete(student);
		return student;
	}

}
