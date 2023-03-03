package com.iktpreobuka.schoollogtwo.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.MarkEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.MarkDTO;
import com.iktpreobuka.schoollogtwo.repositories.MarkRepository;
import com.iktpreobuka.schoollogtwo.repositories.SemesterRepository;
import com.iktpreobuka.schoollogtwo.repositories.StudentSubjectRepository;
import com.iktpreobuka.schoollogtwo.repositories.TeacherStudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.TeacherSubjectRepository;

@Service
public class MarkServiceImpl implements MarkService {

	@Autowired
	private TeacherStudentRepository teacherStudentRepo;
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepo;
	@Autowired
	private StudentSubjectRepository studentSubjectRepo;
	@Autowired
	private SemesterRepository semesterRepository;
	@Autowired
	private MarkRepository markRepository;
	
	@Override
	public MarkDTO createMark(MarkDTO newMark) {
		MarkEntity mark = new MarkEntity();
		mark.setValue(newMark.getValue());
		mark.setComment(newMark.getComment());
		
		mark.setStudent(studentSubjectRepo.findByStudentIdAndSubjectId(newMark.getStudentId(), newMark.getSubjectId()));
		mark.setTeacher(teacherStudentRepo.findByTeacherIdAndStudentId(newMark.getTeacherId(), newMark.getStudentId()));
		mark.setSubject(teacherSubjectRepo.findByTeacherIdAndSubjectId(newMark.getTeacherId(), newMark.getSubjectId()));
		LocalDate markDate = LocalDate.now();
		mark.setMarkDate(markDate);
		mark.setSemester(semesterRepository.findByMarkDate(markDate));
		
		markRepository.save(mark);
		return null;
	}

	@Override
	public MarkDTO createFinalMark(MarkDTO finalMark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarkDTO updateMark(Integer id, MarkDTO updatedMark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarkEntity deleteMark(Integer id) {
		MarkEntity mark = markRepository.findById(id).orElseThrow();
		markRepository.delete(mark);
		return mark;
	}

}
