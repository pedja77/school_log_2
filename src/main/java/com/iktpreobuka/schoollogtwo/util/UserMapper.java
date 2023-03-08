package com.iktpreobuka.schoollogtwo.util;

import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.iktpreobuka.schoollogtwo.entities.AdminEntity;
import com.iktpreobuka.schoollogtwo.entities.GradeEntity;
import com.iktpreobuka.schoollogtwo.entities.ParentEntity;
import com.iktpreobuka.schoollogtwo.entities.ParentStudentEntity;
import com.iktpreobuka.schoollogtwo.entities.StudentEntity;
import com.iktpreobuka.schoollogtwo.entities.TeacherEntity;
import com.iktpreobuka.schoollogtwo.entities.UserEntity;
import com.iktpreobuka.schoollogtwo.entities.UserRoleEntity;
import com.iktpreobuka.schoollogtwo.entities.dto.AdminDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.ParentDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.StudentDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.TeacherDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;
import com.iktpreobuka.schoollogtwo.repositories.GradeRepository;
import com.iktpreobuka.schoollogtwo.repositories.ParentStudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.StudentRepository;
import com.iktpreobuka.schoollogtwo.repositories.UserRoleRepository;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {
	
	@Autowired
	protected StudentRepository studentRepository; 
	@Autowired
	protected ParentStudentRepository parentStudentRepository;
	@Autowired
	protected GradeRepository gradeRepository;
	@Autowired
	protected UserRoleRepository roleRepository;
	
	

	public abstract TeacherDTO teacherToDto(TeacherEntity teacher);
	public abstract ParentDTO parentToDto(ParentEntity parent);
	public abstract StudentDTO studentToDto(StudentEntity student);
	public abstract AdminDTO adminToDto(AdminEntity admin);

	public abstract TeacherEntity teacherToEntity(TeacherDTO dto);

	public abstract ParentEntity parentToEntity(ParentDTO dto);
	@Mapping(target = "dateOfBirth", source = "dto.dateOfBirth", dateFormat = "dd-MM-yyyy")
	public abstract StudentEntity studentToEntity(StudentDTO dto);
	
	public abstract AdminEntity adminToEntity(AdminDTO dto);
	
	Integer mapGradeToInt(GradeEntity grade) {
		return grade.getValue();
	}
	GradeEntity mapGradeToEntity(Integer value) {
		return gradeRepository.findByValue(value);
	}
	UserRoleEntity mapRoleToEntity(String role) {
		return roleRepository.findByRoleName(role);
	}
	String mapRoleToDto(UserRoleEntity role) {
		return role.getRoleName();
	}
//	String mapPasswordToEntity(String password) {
//		return Encryption.getPassEncoded(password);
//	}
	
	@BeforeMapping
	protected void encryptPassword(UserDTO dto) {
		dto.setPassword(Encryption.getPassEncoded(dto.getPassword()));
	}
	
	
	public UserDTO mapToUserDTO(UserEntity user) {
		if (user instanceof TeacherEntity)
			return teacherToDto((TeacherEntity)user);
		if (user instanceof ParentEntity)
			return parentToDto((ParentEntity) user);
		if (user instanceof StudentEntity)
			return studentToDto((StudentEntity) user);
		if (user instanceof AdminEntity)
			return adminToDto((AdminEntity) user);
		
		return null;
	}
	
	public UserEntity mapToUserEntity(UserDTO dto) {
		if (dto instanceof TeacherDTO)
			return teacherToEntity((TeacherDTO) dto);
		if (dto instanceof ParentDTO)
			return parentToEntity((ParentDTO) dto);
		if (dto instanceof StudentDTO)
			return studentToEntity((StudentDTO) dto);
		if (dto instanceof AdminDTO)
			return adminToEntity((AdminDTO) dto);
		
		return null;
	}
	
	public abstract UserEntity updateUserFromDto(UserDTO updatedUser, @MappingTarget UserEntity user);
}
