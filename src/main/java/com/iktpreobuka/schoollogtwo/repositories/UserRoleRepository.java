package com.iktpreobuka.schoollogtwo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.schoollogtwo.entities.UserRoleEntity;

public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Integer> {

	UserRoleEntity findByRoleName(String roleName);
}
