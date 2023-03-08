package com.iktpreobuka.schoollogtwo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.ParentStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.UserEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.ParentDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.TeacherDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;
import com.iktpreobuka.schoollogtwo.repositories.StudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.UserRepository;
import com.iktpreobuka.schoollogtwo.util.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public UserDTO createUser(UserDTO newUser) {
		UserEntity user = mapper.mapToUserEntity(newUser);
		if (newUser instanceof ParentDTO) {
			List<ParentStudentEntity> students = new ArrayList<>();
			for(Integer i: ((ParentDTO)newUser).getStudentIds()) {
				ParentStudentEntity ps = new ParentStudentEntity();
				ps.setParent((ParentEntity)user);
				ps.setStudent(studentRepository.findById(i).orElseThrow());
				students.add(ps);
			}
			((ParentEntity)user).setStudents(students);
		}
		
		userRepository.save(user);
		return mapper.mapToUserDTO(user);
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO deleteUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
