package com.iktpreobuka.schoollogtwo.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentSubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.SubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherSubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.UserEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.SubjectDTO;
import com.iktpreobuka.schoollogtwo.repositories.GradeRepository;
import com.iktpreobuka.schoollogtwo.repositories.StudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.StudentSubjectRepository;
import com.iktpreobuka.schoollogtwo.repositories.SubjectRepository;
import com.iktpreobuka.schoollogtwo.repositories.TeacherRepository;
import com.iktpreobuka.schoollogtwo.repositories.TeacherSubjectRepository;
import com.iktpreobuka.schoollogtwo.repositories.UserRepository;
import com.iktpreobuka.schoollogtwo.util.UserMapper;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private TeacherSubjectRepository tsRepo;
	@Autowired
	private UserMapper mapper;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private StudentSubjectRepository ssRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public SubjectDTO createSubject(SubjectDTO newSubject) {
		SubjectEntity subject = new SubjectEntity();
		subject.setSubjectName(newSubject.getSubjectName());
		subject.setWeeklyFund(newSubject.getWeeklyFund());
		subject.setGrade(gradeRepository.findByValue(newSubject.getGrade()));
		List<TeacherSubjectEntity> teachers = new ArrayList<>(newSubject.getTeachers().stream()
				.map(t -> t.getId()).map(t -> teacherRepository.findById(t).orElseThrow()).map(u -> {
					TeacherSubjectEntity ts = new TeacherSubjectEntity();
					ts.setTeacher(u);
					ts.setSubject(subject);
					return ts;
				}).toList());
		List<StudentSubjectEntity> students = new ArrayList<>(newSubject.getStudents().stream()
				.map(s -> s.getId()).map(s -> studentRepository.findById(s).orElseThrow()).map(s -> {
					StudentSubjectEntity ss = new StudentSubjectEntity();
					ss.setStudent(s);
					ss.setSubject(subject);
					return ss;
				}).toList());
		subject.setTeachers(teachers);
		subject.setStudents(students);
		subjectRepository.save(subject);

		return newSubject;
	}

	@Transactional
	private void bulkDeleteTeacherSubject(List<Integer> tss) {
		for (Integer t : tss) {
			tsRepo.deleteById(t);
		}
	}

	@Transactional
	private void bulkSaveTeacherSubject(List<TeacherSubjectEntity> tss, List<Integer> ids) {
		tsRepo.saveAll(tss.stream().filter(s -> ids.contains(s.getId())).toList());
	}
	
	@Transactional
	private void bulkDeleteStudentSubject(List<Integer> tss) {
		for (Integer s : tss) {
			ssRepository.deleteById(s);
		}
	}

	@Transactional
	private void bulkSaveStudentSubject(List<StudentSubjectEntity> sss, List<Integer> ids) {
		ssRepository.saveAll(sss.stream().filter(s -> ids.contains(s.getId())).toList());
	}
	
	private List<Integer> diffItems(List<Integer> a, List<Integer> b) {
		Set<Integer> setA = new HashSet<>(a);
		Set<Integer> setB = new HashSet<>(b);
		setA.removeAll(setB);
		List<Integer> res = new ArrayList<Integer>(setA);
		return res;
	}

	@Override
	@Transactional
	public SubjectDTO updateSubject(Integer id, SubjectDTO updatedSubject) {
		System.out.println(updatedSubject.toString());
		SubjectEntity subject = subjectRepository.findById(id).orElseThrow();
		if (updatedSubject.getSubjectName() != null
				&& !subject.getSubjectName().equals(updatedSubject.getSubjectName())) {
			subject.setSubjectName(updatedSubject.getSubjectName());
		}
		if (updatedSubject.getWeeklyFund() != null && !subject.getWeeklyFund().equals(updatedSubject.getWeeklyFund())) {
			subject.setWeeklyFund(updatedSubject.getWeeklyFund());
		}
		if (updatedSubject.getGrade() != null && !subject.getGrade().getValue().equals(updatedSubject.getGrade())) {
			subject.setGrade(gradeRepository.findByValue(updatedSubject.getGrade()));
		}
		if (updatedSubject.getTeachers() != null && updatedSubject.getTeachers().size() > 0) {
			// Pravi TeacherSubjectEntity listu od teachera za dati subject
			List<TeacherSubjectEntity> teachers = new ArrayList<>(updatedSubject.getTeachers().stream()
					.map(t -> t.getId()).map(t -> teacherRepository.findById(t).orElseThrow()).map(u -> {
						TeacherSubjectEntity ts = new TeacherSubjectEntity();
						ts.setTeacher(u);
						ts.setSubject(subject);
						return ts;
					}).toList());
			
			// Zdravo neefikasno, ali ce da radi. Nadam se! Ne da je antiefikasno nego je 
			// i jaaaako lose, ali za potrebe demonstracije fronta...
			List<TeacherSubjectEntity> existingTeachers = tsRepo.findBySubjectId(subject.getId());
			List<Integer> teachersIds = teachers.stream().map(t -> t.getId()).toList();
			List<Integer> existingTeachersIds = existingTeachers.stream().map(et -> et.getId()).toList();
			bulkDeleteTeacherSubject(diffItems(existingTeachersIds, teachersIds));
			bulkSaveTeacherSubject(teachers, diffItems(teachersIds, existingTeachersIds));
			subject.setTeachers(teachers);
		}
		if (updatedSubject.getStudents() != null && updatedSubject.getStudents().size() > 0) {
			List<StudentSubjectEntity> students = new ArrayList<>(updatedSubject.getStudents().stream()
					.map(s -> s.getId()).map(s -> studentRepository.findById(s).orElseThrow()).map(s -> {
						StudentSubjectEntity ss = new StudentSubjectEntity();
						ss.setStudent(s);
						ss.setSubject(subject);
						return ss;
					}).toList());
			
			List<StudentSubjectEntity> existingStudents = ssRepository.findBySubjectId(subject.getId());
			List<Integer> studentsIds = students.stream().map(t -> t.getId()).toList();
			List<Integer> existingStudentsIds = existingStudents.stream().map(et -> et.getId()).toList();
			bulkDeleteStudentSubject(diffItems(existingStudentsIds, studentsIds));
			bulkSaveStudentSubject(students, diffItems(studentsIds, existingStudentsIds));
			subject.setStudents(students);
		}

		subjectRepository.save(subject);
		return updatedSubject;
	}

	// Brisanje iz TeacherSubject i StudentSubject ostaje za kasnije kad se
	// implementira ocena
	// Problem je sto bi ocene i dalje trebalo da postoje, a veza sa studentom i
	// nastavnikom su navedene
	// vezne tabele???????????
	@Override
	@Transactional
	public SubjectEntity deleteSubject(Integer id) {
		SubjectEntity subject = subjectRepository.findById(id).orElseThrow();
		tsRepo.deleteAll(subject.getTeachers());

		// TODO: Placeholder for StudentSubject and TeacherSubject

		subjectRepository.delete(subject);
		return null;
	}

	@Override
	public SubjectDTO getSubjectDTO(Integer id) {
		SubjectEntity s = subjectRepository.findById(id).orElseThrow();
		return subjectToDto(s);
	}

	private SubjectDTO subjectToDto(SubjectEntity s) {
		SubjectDTO subject = new SubjectDTO();
		subject.setId(s.getId());
		subject.setSubjectName(s.getSubjectName());
		subject.setGrade(s.getGrade().getValue());
		subject.setWeeklyFund(s.getWeeklyFund());
		subject.setTeachers(
				s.getTeachers().stream().map(teacher -> mapper.mapToUserDTO(teacher.getTeacher())).toList());
		subject.setStudents(
				s.getStudents().stream().map(student -> mapper.mapToUserDTO(student.getStudent())).toList());

		return subject;
	}

	@Override
	public List<SubjectDTO> getAllSubjectDTOs() {
		List<SubjectEntity> subjects = (List<SubjectEntity>) subjectRepository.findAll();
		return subjects.stream().map(s -> subjectToDto(s)).toList();
	}

	@Override
	public List<SubjectDTO> getAllSubjectDTOsFiltered(String query, Integer grade) {
		List<SubjectEntity> subjects = (List<SubjectEntity>) subjectRepository.findAll();
		return subjects.stream().filter(s -> s.getSubjectName().toLowerCase().contains(query.toLowerCase()))
//				.filter(s -> !grade.equals("") ? s.getGrade().getValue() == Integer.parseInt(grade) : true)
				.filter(s -> grade == 0 ? true : grade == s.getGrade().getValue()).map(s -> subjectToDto(s)).toList();
	}

	@Override
	public List<SubjectDTO> getSubjectsByTeacher(Integer id) {
		List<TeacherSubjectEntity> subjects = (List<TeacherSubjectEntity>) tsRepo.findByTeacherId(id);
		return subjects.stream()
				.map(s -> s.getSubject())
				.map(s -> subjectToDto(s)).toList();
	}

}
