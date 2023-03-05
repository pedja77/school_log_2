package com.iktpreobuka.schoollogtwo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.ParentDTO;
import com.iktpreobuka.schoollogtwo.repositories.ParentRepository;
import com.iktpreobuka.schoollogtwo.repositories.ParentStudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.UserRoleRepository;
import com.iktpreobuka.schoollogtwo.util.Encryption;

@Service
public class ParentServiceImpl implements ParentService {
	
	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private UserRoleRepository roleRepository;
	@Autowired
	private ParentStudentRepository parentStudentRepository;

	@Override
	@Transactional
	public ParentEntity createParent(ParentDTO newParent) {
		ParentEntity parent = new ParentEntity();
		parent.setFirstName(newParent.getFirstName());
		parent.setLastName(newParent.getLastName());
		parent.setUsername(newParent.getUsername());
		parent.setPassword(Encryption.getPassEncoded(newParent.getPassword()));
		parent.setEmail(newParent.getEmail());
		parent.setRole(roleRepository.findByRoleName("ROLE_PARENT"));
		
		return parentRepository.save(parent);	
	}

	// Podlozno refaktorisanju: password????? i rola?????
	@Override
	public ParentEntity updateParent(Integer id, ParentDTO updatedParent) {
		ParentEntity parent = parentRepository.findById(id).get();
		if (updatedParent.getFirstName() != null && !parent.getFirstName().equals(updatedParent.getFirstName())) {
			parent.setFirstName(updatedParent.getFirstName());
		}
		if (updatedParent.getLastName() != null && !parent.getLastName().equals(updatedParent.getLastName())) {
			parent.setLastName(updatedParent.getLastName());
		}
		if (updatedParent.getUsername() != null && !parent.getUsername().equals(updatedParent.getUsername())) {
			parent.setUsername(updatedParent.getUsername());
		}
		if (updatedParent.getPassword() != null && !parent.getPassword().equals(updatedParent.getPassword())) {
			parent.setPassword(Encryption.getPassEncoded(updatedParent.getPassword()));
		}
		if (updatedParent.getEmail() != null && !parent.getEmail().equals(updatedParent.getEmail())) {
			parent.setEmail(updatedParent.getEmail());
		}
		
		return parentRepository.save(parent);
	}

	@Override
	@Transactional
	public ParentEntity deleteParent(Integer id) {
		ParentEntity parent = parentRepository.findById(id).get();
		
		parentStudentRepository.deleteAll(parent.getStudents());
//		List<ParentStudentEntity> parentStudents = parentStudentRepository.findByParent(parent);
//		// Delete all the references from parent to student on both sides
//		for (ParentStudentEntity ps: parentStudents) {
//			parentStudentRepository.delete(ps);
//			ps.getStudent().getParents().remove(ps);
//		}
		parentRepository.delete(parent);
		return parent;
	}

}
