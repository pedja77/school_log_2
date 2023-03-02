package com.iktpreobuka.schoollogtwo.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "semester")
@SQLDelete(sql = "UPDATE semester SET deleted = true WHERE id=? AND version=?")
@Where(clause = "deleted = false")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class SemesterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "semester")
	private Integer semester;
	
	@Column(name = "start_date")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate endDate;
	
	@Version
	protected Integer version;
	
	@Column(name = "created")
	@CreationTimestamp
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime createdOn;
	
	@Column(name = "updated")
	@UpdateTimestamp
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime updatedOn;
	
	@Column(name = "deleted")
	protected Boolean deleted;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "school_year")
	private SchoolYearEntity schoolYear;
	
	@JsonIgnore
	@OneToMany(mappedBy = "semester", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private List<MarkEntity> marks;

	public SemesterEntity() {
		super();
		this.deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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

	public SchoolYearEntity getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYearEntity schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	
}
