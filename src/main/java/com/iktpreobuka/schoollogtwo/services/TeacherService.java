package com.iktpreobuka.schoollogtwo.services;

import java.util.List;
import java.util.Optional;

import com.iktpreobuka.schoollogtwo.entities.TeacherEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.StudentsCollectionDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.SubjectDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.SubjectsCollectionDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.TeacherDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.TeachersCollectionDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.responses.TeacherResDTO;

public interface TeacherService {

	public List<UserDTO> getAllTeachers();
	public List<UserDTO> getAllTeachersFiltered(String q);
	public UserDTO getTeacherById(Integer id);
	public TeacherEntity createTeacher(TeacherDTO newTeacher);
	public TeacherDTO updateTeacher(Integer id, TeacherDTO updatedTeacher);
	public TeacherEntity deleteTeacher(Integer id);
	public TeacherEntity deleteTeacher(TeacherEntity teacher);
	public String bulkCreate(TeachersCollectionDTO teachers);
	public TeacherEntity addSubjectsToTeacher(Integer id,SubjectsCollectionDTO subjects);
	public Optional<TeacherEntity> addStudentsToTeacherBySubject(Integer teacherId, Integer subjectId, StudentsCollectionDTO students);
	public TeacherResDTO marksByStudentAndSubject(Integer studentId, Integer subjectId, String teachersUsername);
}
