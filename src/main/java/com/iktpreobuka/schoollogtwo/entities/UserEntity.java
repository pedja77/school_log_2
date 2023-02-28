package com.iktpreobuka.schoollogtwo.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE user_id=? AND version=?")
@Where(clause = "deleted = false")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	protected Integer id;
	
	@Column(name = "first_name")
	@NotNull(message = "First name must be provided.")
	protected String firstName;
	
	@Column(name = "last_name")
	@NotNull(message = "Last name must be provided.")
	protected String lastName;
	
	@Column(name = "username")
	@NotNull(message = "Username must be provided.")
	protected String username;
	
	@JsonIgnore
	@Column(name = "password")
	@NotNull(message = "Password must be provided.")
	protected String password;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "role")
	@NotNull(message = "Role does not exist.")
	protected UserRoleEntity role;

	public UserEntity() {
		super();
		this.deleted = false;
	}

	public UserEntity(Integer id, String firstName, String lastName, String username, String password,
			UserRoleEntity role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRoleEntity getRole() {
		return role;
	}

	public void setRole(UserRoleEntity role) {
		this.role = role;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
