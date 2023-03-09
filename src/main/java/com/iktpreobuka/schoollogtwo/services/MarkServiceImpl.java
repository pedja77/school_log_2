package com.iktpreobuka.schoollogtwo.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.FinalMarkEntity;
import com.iktpreobuka.schoollogtwo.entities.MarkEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentSubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.SubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherSubjectEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.MarkDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.responses.MarkResDTO;
import com.iktpreobuka.schoollogtwo.model.EmailObject;
import com.iktpreobuka.schoollogtwo.repositories.MarkRepository;
import com.iktpreobuka.schoollogtwo.repositories.SemesterRepository;
import com.iktpreobuka.schoollogtwo.repositories.StudentSubjectRepository;
import com.iktpreobuka.schoollogtwo.repositories.TeacherRepository;
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
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private EmailService emailService;
	
	private boolean isTeachersStudent(TeacherEntity teacher, StudentEntity student) {
		return teacher.getStudents().stream()
				.map(e -> e.getStudent())
				.toList()
				.contains(student);
	}
	
	private boolean isTeachersSubject(TeacherEntity teacher, SubjectEntity subject) {
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
	
	private MarkResDTO markToDto(MarkEntity mark) {
		MarkResDTO dto = new MarkResDTO();
		dto.setTeacher(mark.getTeacher().getTeacher().getFullName());
		dto.setValue(mark.getValue());
		dto.setComment(mark.getComment());
		dto.setSchoolYear(mark.getSemester().getSchoolYear().getName());
		dto.setSemester(mark.getSemester().getSemester());
		dto.setGivenOn(mark.getMarkDate());
		dto.setChangedOn(mark.getUpdatedOn().toLocalDate());
		dto.setIsFinal(mark instanceof FinalMarkEntity);
		dto.setStudent(mark.getStudent().getStudent().getFullName());
		dto.setId(mark.getId());
		
		return dto;
	}
	
	private Boolean canBeFinal(MarkDTO mark) {
		return markRepository.existsByStudentIdAndSubjectId(mark.getStudentId(), mark.getSubjectId());
	}
	
	private String createEmailText(MarkEntity mark) {
		StringBuilder text = new StringBuilder();
		text.append("<html><body><table style='border:2px solid black'>");
		text.append("<th><td>Nastavnik</td><td>Predmet</td><td>Ocena</td><td>Datum</td></th>");
		text.append("<tr>" 
				+ "<td>" + mark.getTeacher().getTeacher().getFullName() + "</td>"
				+ "<td>" + mark.getSubject().getSubject().getSubjectName() + "</td>"
				+ "<td>" + mark.getValue() + "</td>"
				+ "<td>" + mark.getMarkDate().toString() + "</td>"
				+ "</tr>");
		text.append("</table></body></html>");
		return text.toString();
	}
	
	private void sendEmails(MarkEntity mark) throws Exception {
		List<String> emails = mark.getStudent().getStudent().getParents()
				.stream()
				.map(e -> e.getParent().getEmail())
				.toList();
		for (String e: emails) {
			EmailObject email = new EmailObject();
			email.setSubject("Nova ocena");
			email.setText(createEmailText(mark));
			email.setTo(e);
			emailService.sendTemplateMessage(email);
		}
	}
	
	@Override
	public Optional<MarkEntity> createMark(MarkDTO newMark, String username, Boolean isFinal) throws Exception {
		MarkEntity mark;
		// Da li je ocena poslata kao zakljucna i da li postoje prethodne ocene (bar jedna)
		if (isFinal) {
			if (!canBeFinal(newMark))
				return Optional.ofNullable(null); // Smislenija varijanta za dojavu da ocena ne moze biti zakljucna???!!!!!????
			mark = new FinalMarkEntity();
		} else {
			mark = new MarkEntity();
		}
		
		TeacherEntity t = teacherRepository.findByUsername(username);
		TeacherStudentEntity teacher = teacherStudentRepo.findByTeacherIdAndStudentId(
				t.getId(), newMark.getStudentId());
		StudentSubjectEntity student = studentSubjectRepo.findByStudentIdAndSubjectId(
				newMark.getStudentId(), newMark.getSubjectId());
		TeacherSubjectEntity subject = teacherSubjectRepo.findByTeacherIdAndSubjectId(
				t.getId(), newMark.getSubjectId());
		
		if (isTeachersStudent(teacher.getTeacher(), student.getStudent()) &&
				isTeachersSubject(teacher.getTeacher(), subject.getSubject()) &&
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
			
			sendEmails(mark);
			
			return Optional.of(mark);
		}
		
		return Optional.ofNullable(null);
	}


	@Override
	public Optional<MarkResDTO> updateMark(Integer id, MarkDTO updatedMark) {
		MarkEntity mark = markRepository.findById(id).orElseThrow();
		if (mark.getStudent().getStudent().getId() == updatedMark.getStudentId() &&
				mark.getSubject().getSubject().getId() == updatedMark.getSubjectId()) {
			if (updatedMark.getValue() != null && !mark.getValue().equals(updatedMark.getValue())) {
				mark.setValue(updatedMark.getValue());
			}
			if (updatedMark.getComment() != null && !mark.getComment().equals(updatedMark.getComment())) {
				mark.setComment(updatedMark.getComment());
			}
			
			return Optional.of(markToDto(markRepository.save(mark)));
		}
		return Optional.ofNullable(null);
	}

	@Override
	public MarkEntity deleteMark(Integer id) {
		MarkEntity mark = markRepository.findById(id).orElseThrow();
		markRepository.delete(mark);
		return mark;
	}

	@Override
	public MarkResDTO getMark(Integer id) {
		MarkEntity mark = markRepository.findById(id).orElseThrow();
		return markToDto(mark);
	}

}
