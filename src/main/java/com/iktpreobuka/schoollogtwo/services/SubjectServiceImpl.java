package com.iktpreobuka.schoollogtwo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.SubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.SubjectDTO;
import com.iktpreobuka.schoollogtwo.repositories.GradeRepository;
import com.iktpreobuka.schoollogtwo.repositories.SubjectRepository;
import com.iktpreobuka.schoollogtwo.repositories.TeacherSubjectRepository;
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
	
	@Override
	public SubjectDTO createSubject(SubjectDTO newSubject) {
		SubjectEntity subject = new SubjectEntity();
		subject.setSubjectName(newSubject.getSubjectName());
		subject.setWeeklyFund(newSubject.getWeeklyFund());
		subject.setGrade(gradeRepository.findByValue(newSubject.getGrade()));
		
		subjectRepository.save(subject);
		
		return newSubject;
	}

	@Override
	public SubjectDTO updateSubject(Integer id, SubjectDTO updatedSubject) {
		SubjectEntity subject = subjectRepository.findById(id).orElseThrow();
		if (updatedSubject.getSubjectName() != null && !subject.getSubjectName().equals(updatedSubject.getSubjectName())) {
			subject.setSubjectName(updatedSubject.getSubjectName());
		}
		if (updatedSubject.getWeeklyFund() != null && !subject.getWeeklyFund().equals(updatedSubject.getWeeklyFund())) {
			subject.setWeeklyFund(updatedSubject.getWeeklyFund());
		}
		if (updatedSubject.getGrade() != null && !subject.getGrade().getValue().equals(updatedSubject.getGrade())) {
			subject.setGrade(gradeRepository.findByValue(updatedSubject.getGrade()));
		}
		subjectRepository.save(subject);
		return updatedSubject;
	}

	// Brisanje iz TeacherSubject i StudentSubject ostaje za kasnije kad se implementira ocena
	// Problem je sto bi ocene i dalje trebalo da postoje, a veza sa studentom i nastavnikom su navedene
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
		subject.setSubjectName(s.getSubjectName());
		subject.setGrade(s.getGrade().getValue());
		subject.setWeeklyFund(s.getWeeklyFund());
		subject.setTeachers(s.getTeachers().stream()
				.map(teacher -> mapper.mapToUserDTO(teacher.getTeacher())).toList());
		subject.setStudents(s.getStudents().stream()
				.map(student -> mapper.mapToUserDTO(student.getStudent())).toList());
		
		return subject;
	}

	@Override
	public List<SubjectDTO> getAllSubjectDTOs() {
		List<SubjectEntity> subjects = (List<SubjectEntity>) subjectRepository.findAll();
		return subjects.stream()
				.map(s -> subjectToDto(s)).toList();
	}

}
