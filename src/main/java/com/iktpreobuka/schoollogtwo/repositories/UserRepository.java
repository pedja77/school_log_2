package com.iktpreobuka.schoollogtwo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	public UserEntity findByUsername(String username);
}
