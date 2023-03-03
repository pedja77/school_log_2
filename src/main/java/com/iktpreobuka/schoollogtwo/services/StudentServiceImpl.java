package com.iktpreobuka.schoollogtwo.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.ParentStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentSubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.StudentDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.SubjectsCollectionDTO;
import com.iktpreobuka.schoollogtwo.repositories.GradeRepository;
import com.iktpreobuka.schoollogtwo.repositories.ParentRepository;
import com.iktpreobuka.schoollogtwo.repositories.ParentStudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.StudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.StudentSubjectRepository;
import com.iktpreobuka.schoollogtwo.repositories.SubjectRepository;
import com.iktpreobuka.schoollogtwo.repositories.UserRoleRepository;

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
	@Autowired
	private UserRoleRepository roleRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	StudentSubjectRepository studentSubjectRepository;
	
	@Override
	public StudentDTO createStudent(StudentDTO newStudent) {
		StudentEntity student = new StudentEntity();
		student.setFirstName(newStudent.getFirstName());
		student.setLastName(newStudent.getLastName());
		student.setUsername(newStudent.getUsername());
		student.setPassword(newStudent.getPassword());
		student.setDateOfBirth(LocalDate.parse(newStudent.getDateOfBirth(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		student.setRole(roleRepository.findByRoleName(newStudent.getRole()));
		student.setGrade(gradeRepository.findByValue(newStudent.getGrade()));
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
	// Poredjenje datuma- obratiti paznju na formate!!!!!1
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
			student.setDateOfBirth(LocalDate.parse(updatedStudent.getDateOfBirth(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		if (updatedStudent.getGrade() != null && !student.getGrade().getValue().equals(updatedStudent.getGrade()))
			student.setGrade(gradeRepository.findByValue(updatedStudent.getGrade()));
		
		studentRepository.save(student);
		return updatedStudent;
	}

	@Override
	public StudentEntity addSubjectsToStudent(Integer id, SubjectsCollectionDTO newSubjects) {
		StudentEntity student = studentRepository.findById(id).orElseThrow();
		List<StudentSubjectEntity> subjects = newSubjects.getSubjects().stream()
				.map(e -> {
					StudentSubjectEntity subject = new StudentSubjectEntity();
					subject.setStudent(student);
					subject.setSubject(subjectRepository.findById(e).orElseThrow());
					return subject;
				})
				// Isfiltriraj samo predmete koji se slusaju u razredu koji ucenik pohadja
				.filter(e -> e.getSubject().getGrade().getId() == student.getGrade().getId())
				.toList();
		studentSubjectRepository.saveAll(subjects);
		return student;
	}

}
