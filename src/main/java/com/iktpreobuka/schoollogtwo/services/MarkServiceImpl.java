package com.iktpreobuka.schoollogtwo.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.MarkEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentSubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.SubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherSubjectEntity;
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
	
	private boolean isTeachersStudent(TeacherEntity teacher, StudentEntity student) {
		return teacher.getStudents().stream()
				.map(e -> e.getStudent())
				.toList()
				.contains(student);
	}
	
	private boolean isTeacherSubject(TeacherEntity teacher, SubjectEntity subject) {
		return teacher.getSubjects().stream()
				.map(e -> e.getSubject())
				.toList()
				.contains(subject);
	}
	
	private boolean isStudentsSubject(StudentEntity student, SubjectEntity subject) {
		return student.getSubjects().stream()
				.map(e -> e.getSubject())
				.toList()
				.contains(subject);
	}
	
	@Override
	public Optional<MarkEntity> createMark(MarkDTO newMark) {
		MarkEntity mark = new MarkEntity();
		TeacherStudentEntity teacher = teacherStudentRepo.findByTeacherIdAndStudentId(
				newMark.getTeacherId(), newMark.getStudentId());
		StudentSubjectEntity student = studentSubjectRepo.findByStudentIdAndSubjectId(
				newMark.getStudentId(), newMark.getSubjectId());
		TeacherSubjectEntity subject = teacherSubjectRepo.findByTeacherIdAndSubjectId(
				newMark.getTeacherId(), newMark.getSubjectId());
		
		if (isTeachersStudent(teacher.getTeacher(), student.getStudent()) &&
				isTeacherSubject(teacher.getTeacher(), subject.getSubject()) &&
				isStudentsSubject(student.getStudent(), subject.getSubject())) {
			
			mark.setValue(newMark.getValue());
			mark.setComment(newMark.getComment());
			
			mark.setStudent(student);
			mark.setTeacher(teacher);
			mark.setSubject(subject);
			LocalDate markDate = LocalDate.now();
			mark.setMarkDate(markDate);
			mark.setSemester(semesterRepository.findByMarkDate(markDate));
			
			markRepository.save(mark);
			return Optional.of(mark);
		}
		
		return Optional.ofNullable(null);
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
