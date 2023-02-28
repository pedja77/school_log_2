package com.iktpreobuka.schoollogtwo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "student")
@SQLDelete(sql = "UPDATE student SET deleted = true WHERE user_id=? AND version=?")
@Where(clause = "deleted = false")
//@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class StudentEntity extends UserEntity {

	@JsonIgnore
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private List<ParentStudentEntity> parentStudent;

	public StudentEntity() {
		super();
	}

	public List<ParentStudentEntity> getParentStudent() {
		return parentStudent;
	}

	public void setParentStudent(List<ParentStudentEntity> parentStudent) {
		this.parentStudent = parentStudent;
	}
	
	
}
