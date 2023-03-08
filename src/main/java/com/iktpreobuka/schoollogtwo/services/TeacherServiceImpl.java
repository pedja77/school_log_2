package com.iktpreobuka.schoollogtwo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.SubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherSubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.StudentsCollectionDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.SubjectsCollectionDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.TeacherDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.TeachersCollectionDTO;
import com.iktpreobuka.schoollogtwo.repositories.StudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.SubjectRepository;
import com.iktpreobuka.schoollogtwo.repositories.TeacherRepository;
import com.iktpreobuka.schoollogtwo.repositories.TeacherStudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.TeacherSubjectRepository;
import com.iktpreobuka.schoollogtwo.repositories.UserRoleRepository;
import com.iktpreobuka.schoollogtwo.util.Encryption;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private UserRoleRepository roleRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private TeacherStudentRepository teacherStudentRepository;
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	private boolean isStudentsSubject(StudentEntity student, SubjectEntity subject) {
		return student.getSubjects().stream()
				.map(e -> e.getSubject())
				.toList()
				.contains(subject);
	}
	
	private boolean isTeachersSubject(TeacherEntity teacher, SubjectEntity subject) {
		return teacher.getSubjects().stream()
				.map(e -> e.getSubject())
				.toList()
				.contains(subject);
	}

	@Override
	public TeacherEntity createTeacher(TeacherDTO newTeacher) {
		TeacherEntity teacher = new TeacherEntity();
		teacher.setFirstName(newTeacher.getFirstName());
		teacher.setLastName(newTeacher.getLastName());
		teacher.setUsername(newTeacher.getUsername());
		teacher.setPassword(Encryption.getPassEncoded(newTeacher.getPassword()));
		teacher.setWeeklyClasses(newTeacher.getWeeklyClasses());
		teacher.setRole(roleRepository.findByRoleName("ROLE_TEACHER"));
		
		return teacherRepository.save(teacher);
	}

	@Override
	public TeacherEntity updateTeacher(Integer id, TeacherDTO updatedTeacher) {
		TeacherEntity teacher = teacherRepository.findById(id).orElseThrow();
		if (updatedTeacher.getFirstName() != null && !teacher.getFirstName().equals(updatedTeacher.getFirstName()))
			teacher.setFirstName(updatedTeacher.getFirstName());
		if (updatedTeacher.getLastName() != null && !teacher.getLastName().equals(updatedTeacher.getLastName()))
			teacher.setLastName(updatedTeacher.getLastName());
		if (updatedTeacher.getUsername() != null && !teacher.getUsername().equals(updatedTeacher.getUsername()))
			teacher.setUsername(updatedTeacher.getUsername());
		if (updatedTeacher.getPassword() != null && !teacher.getPassword().equals(updatedTeacher.getPassword()))
			teacher.setPassword(Encryption.getPassEncoded(updatedTeacher.getPassword()));
		if (updatedTeacher.getWeeklyClasses() != null && !teacher.getWeeklyClasses().equals(updatedTeacher.getWeeklyClasses()))
			teacher.setWeeklyClasses(updatedTeacher.getWeeklyClasses());
//		if (updatedTeacher.getRole() != null && !teacher.getRole().equals(roleRepository.findByRoleName(updatedTeacher.getRole())))
//			teacher.setRole(roleRepository.findByRoleName(updatedTeacher.getRole()));
		
		return teacherRepository.save(teacher);
	}

	@Override
	@Transactional
	public TeacherEntity deleteTeacher(Integer id) {
		TeacherEntity teacher = teacherRepository.findById(id).orElseThrow();
		
		return deleteTeacher(teacher);
	}
	
	@Override
	public TeacherEntity deleteTeacher(TeacherEntity teacher) {
		teacherStudentRepository.deleteAll(teacher.getStudents());
		teacherSubjectRepository.deleteAll(teacher.getSubjects());
		
		teacherRepository.delete(teacher);
		
		return teacher;
	}

	@Override
	@Transactional
	public String bulkCreate(TeachersCollectionDTO teachers) {
		
		for (TeacherDTO t: teachers.getTeachers()) {
			createTeacher(t);
		}
		return "Alles gut";
	}

	@Override
	public TeacherEntity addSubjectsToTeacher(Integer id, SubjectsCollectionDTO newSubjects) {
		TeacherEntity teacher = teacherRepository.findById(id).orElseThrow();
		List<TeacherSubjectEntity> subjects = newSubjects.getSubjects().stream()
				.map((e) -> {
					TeacherSubjectEntity subject = new TeacherSubjectEntity();
					subject.setTeacher(teacher);
					subject.setSubject(subjectRepository.findById(e).orElseThrow());
					return subject;
				}).toList();
		teacherSubjectRepository.saveAll(subjects);
//		teacher.getSubjects().addAll(subjects);
//		teacherRepository.save(teacher);
		return teacher;
	}

	@Override
	public Optional<TeacherEntity> addStudentsToTeacherBySubject(Integer teacherId, Integer subjectId,  StudentsCollectionDTO newStudents) {
		TeacherEntity teacher = teacherRepository.findById(teacherId).orElseThrow();
		SubjectEntity subject = subjectRepository.findById(subjectId).orElseThrow();
		
		// Provera da li nastavnik predaje predmet za koji mu se dodaju ucenici
		// ako ne, bacamo u kontroleru IllegalArgumentException
		if (isTeachersSubject(teacher, subject)) {
			List<TeacherStudentEntity> students = newStudents.getStudents().stream()
					.map(e -> {
						TeacherStudentEntity student = new TeacherStudentEntity();
						student.setTeacher(teacher);
						student.setStudent(studentRepository.findById(e).orElseThrow());
						return student;
					})
					// Provera da li ucenik slusa predmet za koji se dodaje nastavniku
					// ako ne, ignorisemo ga
					.filter(e -> isStudentsSubject(e.getStudent(), subject)) 
					.toList();
			teacherStudentRepository.saveAll(students);
			return Optional.of(teacher);
		}
		
		return Optional.ofNullable(null);
	}

	
}
