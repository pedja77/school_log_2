package com.iktpreobuka.schoollogtwo.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "student")
@SQLDelete(sql = "UPDATE student SET deleted = true WHERE user_id=? AND version=?")
@Where(clause = "deleted = false")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class StudentEntity extends UserEntity {
	
	@NotNull(message = "Date of birth must be provided")
	@Past(message = "Students date of birth must be in the past.")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;

	//@JsonIgnore
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private List<ParentStudentEntity> parents;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private List<TeacherStudentEntity> teachers;
	
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private List<StudentSubjectEntity> subjects;
	
	@NotNull(message = "Students grade must be provided")
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "grade")
	private GradeEntity grade;

	public StudentEntity() {
		super();
	}

	public List<ParentStudentEntity> getParents() {
		return parents;
	}

	public void setParents(List<ParentStudentEntity> parents) {
		this.parents = parents;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<TeacherStudentEntity> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherStudentEntity> teachers) {
		this.teachers = teachers;
	}

	public List<StudentSubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<StudentSubjectEntity> subjects) {
		this.subjects = subjects;
	}

	public GradeEntity getGrade() {
		return grade;
	}

	public void setGrade(GradeEntity grade) {
		this.grade = grade;
	}
	
	
}
