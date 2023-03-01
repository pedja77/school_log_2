package com.iktpreobuka.schoollogtwo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "teacher_student")
@SQLDelete(sql = "UPDATE teacher_student SET deleted = true WHERE id=? AND version=?")
@Where(clause = "deleted = false")
public class TeacherStudentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "deleted")
	private Boolean deleted;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "teacher")
	private TeacherEntity teacher;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "student")
	private StudentEntity student;
}