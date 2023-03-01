package com.iktpreobuka.schoollogtwo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.GradeEntity;

public interface GradeRepository extends CrudRepository<GradeEntity, Integer> {
	
	GradeEntity findByValue(Integer value);

}
