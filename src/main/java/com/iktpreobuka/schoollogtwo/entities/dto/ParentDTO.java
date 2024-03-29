package com.iktpreobuka.schoollogtwo.entities.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParentDTO extends UserDTO {
	
	@NotNull(message = "Email must be provided")
	@Email(message = "Email is not valid.")
	@JsonProperty(value = "email")
	private String email;
	
	@NotNull
	@Size(min = 1, message = "Must have at least one child attending this school.")
	private List<Integer> studentIds;
	
	public ParentDTO() {
		super();
	}

//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<Integer> studentIds) {
		this.studentIds = studentIds;
	}

//	public String getRepeatedPassword() {
//		return repeatedPassword;
//	}
//
//	public void setRepeatedPassword(String repeatedPassword) {
//		this.repeatedPassword = repeatedPassword;
//	}
	
	
}
