package com.iktpreobuka.schoollogtwo.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "admin")@SQLDelete(sql = "UPDATE parent SET deleted = true WHERE user_id=? AND version=?")
@Where(clause = "deleted = false")
public class AdminEntity extends UserEntity {

	public AdminEntity() {
		super();
	}
}
