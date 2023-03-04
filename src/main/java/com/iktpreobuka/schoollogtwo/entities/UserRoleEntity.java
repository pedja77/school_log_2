package com.iktpreobuka.schoollogtwo.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "role")
@SQLDelete(sql = "UPDATE role SET deleted = true WHERE role_id=?")
@Where(clause = "deleted = false")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class UserRoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Integer id;
	
	@Column(name = "role_name")
	private String roleName;
	
	@JsonIgnore
	@Column(name = "deleted")
	private Boolean deleted;
	
	@JsonIgnore
	@Version
	private Integer version;
	
	@JsonIgnore
	@Column(name = "created")
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	@JsonIgnore
	@Column(name = "updated")
	@UpdateTimestamp
	private LocalDateTime updatedOn;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH}, mappedBy = "role")
	private List<UserEntity> users = new ArrayList<>();

	public UserRoleEntity() {
		super();
		this.deleted = false;
	}

	public UserRoleEntity(Integer id, String roleName, List<UserEntity> users) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.users = users;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
	
}
