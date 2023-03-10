package com.iktpreobuka.schoollogtwo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {

	Boolean existsByUsername(String username);
}
