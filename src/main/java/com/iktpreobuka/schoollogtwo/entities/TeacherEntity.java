package com.iktpreobuka.schoollogtwo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "teacher")
@SQLDelete(sql = "UPDATE teacher SET deleted = true WHERE user_id=? AND version=?")
@Where(clause = "deleted = false")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class TeacherEntity extends UserEntity {

	private Integer weeklyClasses;
	
	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private List<TeacherStudentEntity> students;
	
	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private List<TeacherSubjectEntity> subjects;

	public TeacherEntity() {
		super();
	}

	public Integer getWeeklyClasses() {
		return weeklyClasses;
	}

	public void setWeeklyClasses(Integer weeklyClasses) {
		this.weeklyClasses = weeklyClasses;
	}

	public List<TeacherStudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<TeacherStudentEntity> students) {
		this.students = students;
	}

	public List<TeacherSubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<TeacherSubjectEntity> subjects) {
		this.subjects = subjects;
	}
	
	
}
