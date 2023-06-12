package com.iktpreobuka.schoollogtwo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.iktpreobuka.schoollogtwo.entities.SubjectEntity;

public interface SubjectRepository extends CrudRepository<SubjectEntity, Integer> {
	
}
