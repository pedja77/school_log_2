package com.iktpreobuka.schoollogtwo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;

public interface ParentRepository extends CrudRepository<ParentEntity, Integer> {

	public Iterable<ParentEntity> findByDeleted(Boolean isDeleted);
	public ParentEntity findByUsername(String username);
}
