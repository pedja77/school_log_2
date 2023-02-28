package com.iktpreobuka.schoollogtwo.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.schoollogtwo.entities.dto.ParentDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.StudentDTO;
import com.iktpreobuka.schoollogtwo.entities.dto.UserDTO;

@Component
public class UserCustomValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ParentDTO.class.equals(clazz) ||
			StudentDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDTO user = (UserDTO) target;
		if(!user.getPassword().equals(user.getConfirmedPassword())) {
			errors.reject("400", "Passwords must be the same.");
		}
	}

}
