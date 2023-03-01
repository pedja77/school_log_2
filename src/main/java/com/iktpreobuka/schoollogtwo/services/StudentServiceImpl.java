package com.iktpreobuka.schoollogtwo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.ParentStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.StudentDTO;
import com.iktpreobuka.schoollogtwo.repositories.GradeRepository;
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
	@Autowired
	private GradeRepository gradeRepository;
	
	@Override
	public StudentDTO createStudent(StudentDTO newStudent) {
		StudentEntity student = new StudentEntity();
		student.setFirstName(newStudent.getFirstName());
		student.setLastName(newStudent.getLastName());
		student.setUsername(newStudent.getUsername());
		student.setPassword(newStudent.getPassword());
		studentRepository.save(student);
		
		return newStudent;
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
		
		// Delete all the references from student to parent on both sides and also delete parent if he doesn't have any more
		// students related to him
		for (ParentStudentEntity ps: parentStudents) {
			parentStudentRepository.delete(ps);
			ps.getParent().getStudents().remove(ps);
			if (ps.getParent().getStudents().size() == 0)
				parentRepository.delete(ps.getParent());
		}
		
		studentRepository.delete(student);
		return student;
	}

	@Override
	// Doesn't update students role, what's the purpose?
	public StudentDTO updateStudent(Integer id, StudentDTO updatedStudent) {
		StudentEntity student = studentRepository.findById(id).orElseThrow();
		if (updatedStudent.getFirstName() != null && !student.getFirstName().equals(updatedStudent.getFirstName()))
			student.setFirstName(updatedStudent.getFirstName());
		if (updatedStudent.getLastName() != null && !student.getLastName().equals(updatedStudent.getLastName()))
			student.setLastName(updatedStudent.getLastName());
		if (updatedStudent.getUsername() != null && !student.getUsername().equals(updatedStudent.getUsername()))
			student.setUsername(updatedStudent.getUsername());
		if (updatedStudent.getPassword() != null && !student.getPassword().equals(updatedStudent.getPassword()))
			student.setPassword(updatedStudent.getPassword());
		if (updatedStudent.getDateOfBirth() != null && !student.getDateOfBirth().equals(updatedStudent.getDateOfBirth()))
			student.setDateOfBirth(updatedStudent.getDateOfBirth());
		if (updatedStudent.getGrade() != null && !student.getGrade().getValue().equals(updatedStudent.getGrade()))
			student.setGrade(gradeRepository.findByValue(updatedStudent.getGrade()));
		
		studentRepository.save(student);
		return updatedStudent;
	}

}
