package com.iktpreobuka.schoollogtwo.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "mark")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "mark_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "mark")
@SQLDelete(sql = "UPDATE mark SET deleted = true WHERE id=? AND version=?")
@Where(clause = "deleted = false")
public class MarkEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	
	@Column(name = "value")
	protected Integer value;
	
	@Column(name = "coment")
	protected String comment;
	
	@Column(name = "mark_date")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	protected LocalDate markDate;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "semester")
	protected SemesterEntity semester;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	protected StudentSubjectEntity student;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	protected TeacherStudentEntity teacher;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	protected TeacherSubjectEntity subject;
	
	@Version
	protected Integer version;
	
	@Column(name = "created")
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	@Column(name = "updated")
	@UpdateTimestamp
	private LocalDateTime updatedOn;
	
	@Column(name = "deleted")
	protected Boolean deleted;

	public MarkEntity() {
		super();
		this.deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public SemesterEntity getSemester() {
		return semester;
	}

	public void setSemester(SemesterEntity semester) {
		this.semester = semester;
	}

	public StudentSubjectEntity getStudent() {
		return student;
	}

	public void setStudent(StudentSubjectEntity student) {
		this.student = student;
	}

	public TeacherStudentEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherStudentEntity teacher) {
		this.teacher = teacher;
	}

	public TeacherSubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(TeacherSubjectEntity subject) {
		this.subject = subject;
	}

	public LocalDate getMarkDate() {
		return markDate;
	}

	public void setMarkDate(LocalDate markDate) {
		this.markDate = markDate;
	}
	
	
}
