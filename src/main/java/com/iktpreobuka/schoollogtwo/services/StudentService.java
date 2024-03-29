package com.iktpreobuka.schoollogtwo.services;

import java.util.List;
import java.util.Optional;

import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.StudentDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.SubjectsCollectionDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.responses.StudentsMarksResDTO;

public interface StudentService {

	public StudentDTO createStudent(StudentDTO newStudent);
	public StudentEntity addParentToStudent(Integer studentId, Integer parentId);
	public StudentEntity deleteStudent(Integer studentId);
	public StudentEntity deleteStudent(StudentEntity student);
	public StudentDTO updateStudent(Integer id, StudentDTO updatedStudent);
	public StudentEntity addSubjectsToStudent(Integer id, SubjectsCollectionDTO subjects);
	public StudentsMarksResDTO getStudentsMarks(String username);
	public Optional<StudentsMarksResDTO> getStudentsMarksBySubject(String username, Integer subjectId);
	public List<UserDTO> getStudentsByGrade(Integer grade);
	public List<UserDTO> getStudentsByTeacher(Integer teacherId);
	public List<UserDTO> getAllStudents();
}
