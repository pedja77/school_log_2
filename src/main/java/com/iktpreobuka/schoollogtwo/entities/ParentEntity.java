package com.iktpreobuka.schoollogtwo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "parent")
@SQLDelete(sql = "UPDATE parent SET deleted = true WHERE user_id=? AND version=?")
@Where(clause = "deleted = false")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ParentEntity extends UserEntity{
	
	@Column(name = "email")
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	List<ParentStudentEntity> parentStudent;

	public ParentEntity() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ParentStudentEntity> getParentStudent() {
		return parentStudent;
	}

	public void setParentStudent(List<ParentStudentEntity> parentStudent) {
		this.parentStudent = parentStudent;
	}
	
	

}
