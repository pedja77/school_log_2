package com.iktpreobuka.schoollogtwo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity 
@DiscriminatorValue(value = "final_mark")
@SQLDelete(sql = "UPDATE mark SET deleted = true WHERE id=? AND version=?")
@Where(clause = "deleted = false")
public class FinalMarkEntity extends MarkEntity {

	public FinalMarkEntity() {
		super();
	}
}
