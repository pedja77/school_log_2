package com.iktpreobuka.schoollogtwo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.schoollogtwo.entities.AdminEntity;
import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.ParentStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherEntity;
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
	@Autowired
	private ParentService parentService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private AdminService adminService;
	
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
	public UserEntity updateUser(Integer id, UserDTO dto) {
		UserEntity user = userRepository.findById(id).orElseThrow();
		mapper.updateUserFromDto(dto, user);
		
		return userRepository.save(user);
	}

	@Override
	public UserDTO deleteUser(Integer id) {
		UserEntity user = userRepository.findById(id).orElseThrow();
		// kako smo vec dobavili korisnika iz baze, u servisima su dodate metode za brisanje po zadatom objektu
		if (user instanceof AdminEntity) {
			adminService.deleteAdmin((AdminEntity) user);
		}
		if (user instanceof ParentEntity) {
			parentService.deleteParent((ParentEntity) user);
		}
		if (user instanceof StudentEntity) {
			studentService.deleteStudent((StudentEntity) user);
		}
		if (user instanceof TeacherEntity) {
			teacherService.deleteTeacher((TeacherEntity) user);
		}
		return mapper.mapToUserDTO(user);
	}

	@Override
	public UserEntity getUser(Integer id) {
		
		return userRepository.findById(id).orElseThrow();
	}

	@Override
	public List<UserEntity> getUsers() {
		return (List<UserEntity>) userRepository.findAll();
	}

}
