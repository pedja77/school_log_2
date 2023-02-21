package com.iktpreobuka.schoollogtwo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.ParentStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.StudentDTO;
import com.iktpreobuka.schoollogtwo.repositories.ParentRepository;
import com.iktpreobuka.schoollogtwo.repositories.ParentStudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.StudentRepository;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private ParentStudentRepository parentStudentRepository;
	
	@PostMapping
	public ResponseEntity<?> createStudent(@RequestBody StudentDTO newStudent) {
		StudentEntity student = new StudentEntity();
		student.setFirstName(newStudent.getFirstName());
		student.setLastName(newStudent.getLastName());
		student.setUsername(newStudent.getUsername());
		student.setPassword(newStudent.getPassword());
		
		studentRepository.save(student);
		
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{studentId}/parent/{parentId}")
	public ResponseEntity<?> addParentToStudent(@PathVariable Integer studentId, @PathVariable Integer parentId) {
		ParentEntity parent = parentRepository.findById(parentId).get();
		StudentEntity student = studentRepository.findById(studentId).get();
		ParentStudentEntity parentStudent = new ParentStudentEntity();
		parentStudent.setParent(parent);
		parentStudent.setStudent(student);
		student.getParentStudent().add(parentStudent);
		
		studentRepository.save(student);
		
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
		StudentEntity student = studentRepository.findById(id).get();
		List<ParentStudentEntity> parentStudents = parentStudentRepository.findByStudent(student);
		
		for (ParentStudentEntity ps: parentStudents) {

			parentStudentRepository.delete(ps);
			ps.getParent().getParentStudent().remove(ps);
			if (!parentStudentRepository.existsByParent(ps.getParent()))
				parentRepository.delete(ps.getParent());
			
		}
		
		studentRepository.delete(student);
		
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
}
