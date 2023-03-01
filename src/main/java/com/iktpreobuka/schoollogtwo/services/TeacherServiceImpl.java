package com.iktpreobuka.schoollogtwo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.TeacherEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.TeacherDTO;
import com.iktpreobuka.schoollogtwo.repositories.TeacherRepository;
import com.iktpreobuka.schoollogtwo.repositories.UserRoleRepository;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private UserRoleRepository roleRepository;
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public TeacherEntity createTeacher(TeacherDTO updatedTeacher) {
		TeacherEntity teacher = new TeacherEntity();
		teacher.setFirstName(updatedTeacher.getFirstName());
		teacher.setLastName(updatedTeacher.getLastName());
		teacher.setUsername(updatedTeacher.getUsername());
		teacher.setPassword(updatedTeacher.getPassword());
		teacher.setWeeklyClasses(updatedTeacher.getWeeklyClasses());
		teacher.setRole(roleRepository.findByRoleName(updatedTeacher.getRole()));
		
		return teacherRepository.save(teacher);
	}

	@Override
	public TeacherEntity updateTeacher(Integer id, TeacherDTO updatedTeacher) {
		TeacherEntity teacher = teacherRepository.findById(id).orElseThrow();
		if (updatedTeacher.getFirstName() != null && !teacher.getFirstName().equals(updatedTeacher.getFirstName()))
			teacher.setFirstName(updatedTeacher.getFirstName());
		if (updatedTeacher.getLastName() != null && !teacher.getLastName().equals(updatedTeacher.getLastName()))
			teacher.setLastName(updatedTeacher.getLastName());
		if (updatedTeacher.getUsername() != null && !teacher.getUsername().equals(updatedTeacher.getUsername()))
			teacher.setUsername(updatedTeacher.getUsername());
		if (updatedTeacher.getPassword() != null && !teacher.getPassword().equals(updatedTeacher.getPassword()))
			teacher.setPassword(updatedTeacher.getPassword());
		if (updatedTeacher.getWeeklyClasses() != null && !teacher.getWeeklyClasses().equals(updatedTeacher.getWeeklyClasses()))
			teacher.setWeeklyClasses(updatedTeacher.getWeeklyClasses());
		if (updatedTeacher.getRole() != null && !teacher.getRole().equals(roleRepository.findByRoleName(updatedTeacher.getRole())))
			teacher.setRole(roleRepository.findByRoleName(updatedTeacher.getRole()));
		
		return teacherRepository.save(teacher);
	}

	@Override
	public TeacherEntity deleteTeacher(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
